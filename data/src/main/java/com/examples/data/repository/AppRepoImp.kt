package com.examples.data.repository

import com.examples.data.source.cloud.BaseCloudRepository
import javax.inject.Inject

class AppRepoImp @Inject constructor(
    private val cloudRepository: BaseCloudRepository

) : AppRepository {

}