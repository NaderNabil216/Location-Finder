package com.examples.data.restful

import com.examples.entities.explore_places.response.RemoteVenuesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(Config.EXPLORE_VENUES)
    suspend fun explorePlaces(
        @Query(Config.CLIENT_ID_QUERY) clientId: String ,
        @Query(Config.CLIENT_SECRET_QUERY) clientSecret: String ,
        @Query(Config.VERSION_QUERY) version: String,
        @Query(Config.LAT_LNG_QUERY) latlng: String,
        @Query(Config.RADIUS_QUERY) radius: String,
        @Query(Config.LIMIT_QUERY) limit: String
    ): RemoteVenuesResponse
}