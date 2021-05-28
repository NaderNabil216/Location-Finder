package com.examples.domain.mappers

import com.examples.entities.explore_places.local.ExploredPlace
import com.examples.entities.explore_places.remote.RemoteVenue
import com.youxel.domain.base.ModelMapper
import javax.inject.Inject

class ExploredPlaceMapper @Inject constructor() : ModelMapper<RemoteVenue, ExploredPlace> {
    override fun convert(from: RemoteVenue?): ExploredPlace {
        return from?.let { remoteVenue ->
            ExploredPlace(
                remoteVenue.name ?: "",
                remoteVenue.categories?.first()?.icon?.let {
                    "${it.prefix ?: ""}$placeIconSize${it.suffix ?: ""}"
                } ?: "",
                remoteVenue.location?.address ?: ""
            )
        } ?: ExploredPlace("", "", "")
    }

    companion object {
        private const val placeIconSize = 64
    }
}