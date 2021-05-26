package com.examples.data.source.prefrences

import com.examples.data.source.prefrences.FixedSizeStack

/**
 * Created by Nader Nabil on 14/12/2020.
 */
interface StorageManager {
    var accessToken: String
    var refreshToken: String
    var userId: String
    var userProfileImage: String
    var userName: String
    var userPosition: String
    var departmentName: String
    var userEmail: String
    var userPhone: String
    var searchHistory: FixedSizeStack<String>

    fun clearUserData()
}