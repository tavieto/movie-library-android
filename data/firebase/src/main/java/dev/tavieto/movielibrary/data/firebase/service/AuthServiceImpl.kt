package dev.tavieto.movielibrary.data.firebase.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dev.tavieto.movielibrary.core.commons.base.Either

import dev.tavieto.movielibrary.data.remote.model.UserResponse
import dev.tavieto.movielibrary.data.remote.service.AuthService
import java.net.URI
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
                    if (it.user == null) return@addOnSuccessListener
                    firestore.collection("user")
                        .document(it.user!!.uid)
                        .get()
                        .addOnSuccessListener { user ->
                            val userResponse = UserResponse(
                                id = user.getString("uid") ?: "",
                                name = user.getString("name") ?: "",
                                email = user.getString("email") ?: "",
                                imageURL = user.getString("imageURL") ?: "",
                                favoriteMoviesID = user.get("favoriteMoviesID") as? List<String> ?: emptyList()
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
            awaitClose {  }
        }
    }

    override suspend fun signUp(name: String, email: String, password: String, uri: URI) {
        TODO("Not yet implemented")
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