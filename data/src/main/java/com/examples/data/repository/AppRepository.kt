package com.examples.data.repository

import com.examples.entities.explore_places.query.ExplorePlacesQuery
import com.examples.entities.explore_places.response.RemoteVenuesResponse

interface AppRepository {
    suspend fun explorePlaces(
        query: ExplorePlacesQuery
    ): RemoteVenuesResponse

}