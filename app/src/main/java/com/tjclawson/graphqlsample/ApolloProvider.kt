package com.tjclawson.graphqlsample

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class ApolloProvider {

    val BASE_URL: String = "https://api.graph.cool/simple/v1/ck406zi1j4ekp0167qb75ww3o"


    fun getApolloClient(): ApolloClient {

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder().build()

        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build()
    }
}