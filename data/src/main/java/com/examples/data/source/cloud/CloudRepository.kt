package com.examples.data.source.cloud

import com.examples.data.restful.ApiService
import com.examples.data.restful.Config
import com.examples.entities.explore_places.query.ExplorePlacesQuery
import com.examples.entities.explore_places.response.RemoteVenuesResponse

class CloudRepository(private val apIs: ApiService) : BaseCloudRepository {
    override suspend fun explorePlaces(
        query: ExplorePlacesQuery
    ): RemoteVenuesResponse {
        return apIs.explorePlaces(
            Config.CLIENT_ID,
            Config.CLIENT_SECRET,
            Config.version,
            query.ll,
            query.radius,
            query.limit
        )
    }

}
