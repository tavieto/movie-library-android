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
                    firestore.collection("user")
                        .document(it.user!!.uid)
                        .get()
                        .addOnSuccessListener { user ->
                            val userResponse = UserResponse(
                                id = user.getString("uid") ?: "",
                                name = user.getString("name") ?: "",
                                email = user.getString("email") ?: "",
                                tmdbSessionId = user.getString("tmdb_session_id") ?: ""
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
                        "id" to firebaseUser.uid,
                        "name" to name,
                        "email" to email,
                        "tmdb_session_id" to ""
                    )
                    val userDocument = firestore
                        .collection("user")
                        .document(firebaseUser.uid)

                    userDocument.set(data)
                        .addOnSuccessListener {
                            userDocument
                                .get()
                                .addOnSuccessListener { user ->
                                    val userResponse = UserResponse(
                                        id = user.getString("uid") ?: "",
                                        name = user.getString("name") ?: "",
                                        email = user.getString("email") ?: "",
                                        tmdbSessionId = user.getString("tmdb_session_id") ?: ""
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
}