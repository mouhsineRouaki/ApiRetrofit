package com.example.retrofitapii



class PostRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getPosts(): List<Post>? {
        return try {
            apiService.getPosts()
        } catch (e: Exception) {
            null // Handle errors, e.g., no internet connection
        }
    }

    suspend fun getPostById(postId: Int): Post? {
        return try {
            apiService.getPostById(postId)
        } catch (e: Exception) {
            null
        }
    }
}