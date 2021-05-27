package com.examples.data.repository

import com.examples.data.source.cloud.BaseCloudRepository
import com.examples.entities.explore_places.query.ExplorePlacesQuery
import com.examples.entities.explore_places.response.RemoteVenuesResponse
import javax.inject.Inject

class AppRepoImp @Inject constructor(
    private val cloudRepository: BaseCloudRepository
) : AppRepository {
    override suspend fun explorePlaces(query: ExplorePlacesQuery): RemoteVenuesResponse {
        return cloudRepository.explorePlaces(query)
    }

}