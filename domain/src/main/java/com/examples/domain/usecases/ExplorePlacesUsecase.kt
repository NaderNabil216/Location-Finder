package com.examples.domain.usecases

import com.examples.data.mapper.CloudErrorMapper
import com.examples.data.repository.AppRepository
import com.examples.domain.base.RemoteUseCase
import com.examples.domain.mappers.ExploredPlaceMapper
import com.examples.entities.explore_places.local.ExploredPlace
import com.examples.entities.explore_places.query.ExplorePlacesQuery
import com.examples.entities.explore_places.response.RemoteVenuesResponse
import javax.inject.Inject

class ExplorePlacesUsecase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val appRepository: AppRepository,
    private val mapper: ExploredPlaceMapper
) : RemoteUseCase<ExplorePlacesQuery, RemoteVenuesResponse, List<ExploredPlace>>(
    errorUtil
) {
    override suspend fun executeOnBackground(parameters: ExplorePlacesQuery): RemoteVenuesResponse {
        return appRepository.explorePlaces(parameters)
    }

    override suspend fun convert(dto: RemoteVenuesResponse): List<ExploredPlace> {
        val placesList = arrayListOf<ExploredPlace>()
        dto.result?.groups?.forEach { groupsItem ->
            groupsItem?.items?.forEach { item ->
                placesList.add(mapper.convert(item?.venue))
            }
        }
        return placesList
    }
}