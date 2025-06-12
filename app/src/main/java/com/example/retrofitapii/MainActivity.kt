package com.example.retrofitapii

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()
    private lateinit var etPostId: EditText
    private lateinit var btnSearch: Button
    private lateinit var tvPostDetails: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnFetch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPostId = findViewById(R.id.etPostId)
        btnSearch = findViewById(R.id.btnSearch)
        tvPostDetails = findViewById(R.id.tvPostDetails)
        recyclerView = findViewById(R.id.recyclerView)
        btnFetch = findViewById(R.id.btnFetch)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch all posts when btnFetch is clicked
        btnFetch.setOnClickListener {
            viewModel.fetchPosts()
        }

        // Search for a specific post when btnSearch is clicked
        btnSearch.setOnClickListener {
            val postId = etPostId.text.toString().toIntOrNull()
            if (postId != null) {
                viewModel.fetchPostById(postId)
            } else {
                tvPostDetails.text = "Veuillez entrer un ID valide."
            }
        }

        // Observe the list of posts for the RecyclerView
        viewModel.posts.observe(this) { posts ->
            recyclerView.adapter = PostAdapter(posts)
        }

        // Observe the single post for the search result
        viewModel.post.observe(this) { post ->
            if (post != null) {
                tvPostDetails.text = "ID: ${post.id}\nTitre: ${post.title}\nContenu: ${post.body}"
            } else {
                tvPostDetails.text = "Aucun post trouvé."
            }
        }
    }
}