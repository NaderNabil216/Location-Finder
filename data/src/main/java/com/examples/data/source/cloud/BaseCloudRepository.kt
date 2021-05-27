package com.examples.data.source.cloud

import com.examples.entities.explore_places.query.ExplorePlacesQuery
import com.examples.entities.explore_places.response.RemoteVenuesResponse

interface BaseCloudRepository {
    suspend fun explorePlaces(
        query: ExplorePlacesQuery
    ): RemoteVenuesResponse
}