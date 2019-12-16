package com.tjclawson.graphqlsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloCallback
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_get_posts.setOnClickListener {
            getAllPosts()
        }

        button_create_post.setOnClickListener {
            createNewPost(et_title.text.toString(), et_description.text.toString(), et_image_url.text.toString())
        }

    }

    private fun getAllPosts() {
        ApolloProvider().getApolloClient().query(
            GetAllPostsQuery.builder().build()
        ).enqueue(object: ApolloCall.Callback<GetAllPostsQuery.Data>() {

            override fun onFailure(e: ApolloException) {
                Log.i("BIGBRAIN", e.message.toString())
            }

            override fun onResponse(response: Response<GetAllPostsQuery.Data>) {
                Log.i("BIGBRAIN", "QUERY: " + response.data().toString())
            }
        })
    }

    private fun createNewPost(mTitle: String, mDescription: String, mImageUrl: String) {
        val createNewPostMutation: CreateNewPostMutation = CreateNewPostMutation(mTitle, mDescription, mImageUrl)

        ApolloProvider().getApolloClient().mutate(createNewPostMutation)
            .enqueue(object: ApolloCall.Callback<CreateNewPostMutation.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.i("BIGBRAIN", e.message.toString())
                }

                override fun onResponse(response: Response<CreateNewPostMutation.Data>) {
                    Log.i("BIGBRAIN", "MUTATE: " + response.data().toString())
                }
            })
    }
}
