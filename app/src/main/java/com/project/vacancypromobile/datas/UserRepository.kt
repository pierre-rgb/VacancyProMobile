package com.project.vacancypromobile.datas

import android.util.Log
import com.project.vacancypromobile.models.User
import com.project.vacancypromobile.services.ApiService
import com.project.vacancypromobile.services.requests.LoginRequest
import com.project.vacancypromobile.utils.TokenManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

class UserRepository @Inject constructor(val tokenManager: TokenManager, val apiService: ApiService) : Serializable {
    private val users = arrayListOf<User>()
    private var currentUser: User? = null

    fun getUsers() = users
    fun getCurrentUser() = currentUser


    fun loadUsers() {
        users.clear()
        GlobalScope.launch {
            val response = apiService.List()
            if (response.isSuccessful) {
                response.body()?.let {
                    for (user in it) users.add(user)
                }
            }
        }
    }

    suspend fun loadUser() {
        val token = tokenManager.getToken().first()
        if (token != null) {
            val response = apiService.fetchUser("Bearer $token")
            if (response.isSuccessful) {
                currentUser = response.body()
            }
        }
    }

    suspend fun signIn(request: LoginRequest) {
        val response = apiService.signIn(request)
        if(response.isSuccessful) {
            currentUser = response.body()
            Log.d("USER", response.body().toString())
            tokenManager.saveToken(currentUser!!.token)

        }
    }

    suspend fun signUp(lastname:String, firstname: String, email:String, password:String) {
        val response = apiService.signUp(lastname, firstname, email, password)
    }
}