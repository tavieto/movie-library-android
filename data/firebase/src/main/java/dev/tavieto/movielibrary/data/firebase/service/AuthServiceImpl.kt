package dev.tavieto.movielibrary.data.firebase.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import dev.tavieto.movielibrary.core.commons.base.Either
import dev.tavieto.movielibrary.core.commons.exception.DuplicatedEmailException
import dev.tavieto.movielibrary.core.commons.exception.UnknownUserException
import dev.tavieto.movielibrary.data.remote.model.UserResponse
import dev.tavieto.movielibrary.data.remote.service.AuthService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

internal class AuthServiceImpl : AuthService {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun signIn(email: String, password: String): Flow<Either<UserResponse>> {
        return callbackFlow {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    if (it.user == null) {
                        trySend(Either.Failure(UnknownUserException()))
                        return@addOnSuccessListener
                    }
                    firestore.collection(USER_COLLECTION)
                        .document(it.user!!.uid)
                        .get()
                        .addOnSuccessListener { user ->
                            val userResponse = UserResponse(
                                id = user.getString(ID_PARAM) ?: "",
                                name = user.getString(NAME_PARAM) ?: "",
                                email = user.getString(EMAIL_PARAM) ?: "",
                                tmdbSessionId = user.getString(TMDB_SESSION_ID) ?: "",
                                tmdbAccountId = user.getLong(TMDB_ACCOUNT_ID)?.toInt() ?: -1
                            )
                            trySend(Either.Success(userResponse))
                        }
                        .addOnFailureListener { error ->
                            trySend(Either.Failure(error))
                        }
                }
                .addOnFailureListener { error ->
                    trySend(Either.Failure(error))
                }
            awaitClose()
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Flow<Either<UserResponse>> {
        return callbackFlow {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { result ->
                    val firebaseUser = result.user ?: return@addOnSuccessListener
                    val data = hashMapOf<String, Any>(
                        ID_PARAM to firebaseUser.uid,
                        NAME_PARAM to name,
                        EMAIL_PARAM to email,
                        TMDB_SESSION_ID to ""
                    )
                    val userDocument = firestore
                        .collection(USER_COLLECTION)
                        .document(firebaseUser.uid)

                    userDocument.set(data)
                        .addOnSuccessListener {
                            userDocument
                                .get()
                                .addOnSuccessListener { user ->
                                    val userResponse = UserResponse(
                                        id = user.getString(ID_PARAM) ?: "",
                                        name = user.getString(NAME_PARAM) ?: "",
                                        email = user.getString(EMAIL_PARAM) ?: "",
                                        tmdbSessionId = user.getString(TMDB_SESSION_ID) ?: "",
                                        tmdbAccountId = user.getLong(TMDB_ACCOUNT_ID)?.toInt() ?: -1
                                    )
                                    trySend(Either.Success(userResponse))
                                }
                                .addOnFailureListener {
                                    firebaseUser.delete()
                                    trySend(Either.Failure(it))
                                }
                        }
                        .addOnFailureListener {
                            firebaseUser.delete()
                            trySend(Either.Failure(it))
                        }
                }
                .addOnFailureListener {
                    val exception = when (it) {
                        is FirebaseAuthUserCollisionException -> DuplicatedEmailException()
                        else -> it
                    }
                    trySend(Either.Failure(exception))
                }
            awaitClose()
        }
    }

    override suspend fun signOut(): Flow<Either<Unit>> = flow {
        emit(
            try {
                Either.Success(auth.signOut())
            } catch (error: Throwable) {
                Either.Failure(error)
            }
        )
    }

    override suspend fun saveSessionId(sessionId: String): Flow<Either<Unit>> = callbackFlow {
        val user = auth.currentUser

        if (user != null) {
            val doc = firestore.collection(USER_COLLECTION).document(user.uid)
            doc.get()
                .addOnSuccessListener {
                    it.data?.let { result ->
                        val data = HashMap<String, Any>(result)
                        data[TMDB_SESSION_ID] = sessionId
                        doc.set(data)
                            .addOnSuccessListener {
                                trySend(Either.Success(Unit))
                            }
                            .addOnFailureListener { error ->
                                trySend(Either.Failure(error))
                            }
                    } ?: trySend(Either.Failure(Throwable("Empty user")))
                }
                .addOnFailureListener {
                    trySend(Either.Failure(it))
                }

        } else {
            trySend(Either.Failure(Throwable("There's no user")))
        }
        awaitClose()
    }

    override suspend fun saveAccountId(accountId: Int): Flow<Either<Unit>> {
        return callbackFlow {
            val user = auth.currentUser

            if (user != null) {
                val doc = firestore.collection(USER_COLLECTION).document(user.uid)
                doc.get()
                    .addOnSuccessListener {
                        it.data?.let { result ->
                            val data = HashMap<String, Any>(result)
                            data[TMDB_ACCOUNT_ID] = accountId
                            doc.set(data)
                                .addOnSuccessListener {
                                    trySend(Either.Success(Unit))
                                }
                                .addOnFailureListener { error ->
                                    trySend(Either.Failure(error))
                                }
                        } ?: trySend(Either.Failure(Throwable("Empty user")))
                    }
                    .addOnFailureListener {
                        trySend(Either.Failure(it))
                    }

            } else {
                trySend(Either.Failure(Throwable("There's no user")))
            }
            awaitClose()
        }
    }

    private companion object {
        const val USER_COLLECTION = "user"
        const val ID_PARAM = "id"
        const val NAME_PARAM = "name"
        const val EMAIL_PARAM = "email"
        const val TMDB_SESSION_ID = "tmdb_session_id"
        const val TMDB_ACCOUNT_ID = "tmdb_account_id"
    }
}