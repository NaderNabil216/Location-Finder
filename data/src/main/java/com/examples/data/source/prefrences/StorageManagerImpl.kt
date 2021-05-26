package com.examples.data.source.prefrences

import com.orhanobut.hawk.Hawk
import javax.inject.Inject

/**
 * Created by Nader Nabil on 14/12/2020.
 */
class StorageManagerImpl @Inject constructor() : StorageManager {
    override var accessToken: String
        get() = Hawk.get(ACCESS_TOKEN_KEY, "")
        set(value) {
            Hawk.put(ACCESS_TOKEN_KEY, value)
        }
    override var refreshToken: String
        get() = Hawk.get(REFRESH_TOKEN_KEY, "")
        set(value) {
            Hawk.put(REFRESH_TOKEN_KEY, value)
        }
    override var userId: String
        get() = Hawk.get(USER_ID_KEY, "")
        set(value) {
            Hawk.put(USER_ID_KEY, value)
        }

    override var userProfileImage: String
        get() =  Hawk.get(USER_PROFILE_IMAGE, "")
        set(value) {
            Hawk.put(USER_PROFILE_IMAGE, value)
        }
    override var userName: String
        get() =  Hawk.get(USER_NAME, "")
        set(value) {
            Hawk.put(USER_NAME, value)
        }
    override var userPosition: String
        get() =  Hawk.get(USER_POSITION, "")
        set(value) {
            Hawk.put(USER_POSITION, value)
        }

    override var departmentName: String
        get() =  Hawk.get(DEPARTMENT_NAME, "")
        set(value) {
            Hawk.put(DEPARTMENT_NAME, value)
        }
    override var userEmail: String
        get() =  Hawk.get(USER_EMAIL, "")
        set(value) {
            Hawk.put(USER_EMAIL, value)
        }
    override var userPhone: String
        get() =  Hawk.get(USER_PHONE, "")
        set(value) {
            Hawk.put(USER_PHONE, value)
        }
    override var searchHistory: FixedSizeStack<String>
        get() = Hawk.get(SEARCH_HISTORY, FixedSizeStack(10))
        set(value) {
            Hawk.put(SEARCH_HISTORY,value)
        }


    override fun clearUserData() {
        accessToken = ""
        refreshToken = ""
        userId = ""
        userProfileImage = ""
        userName = ""
        userPosition = ""
        departmentName = ""
        userEmail = ""
        userPhone = ""
        searchHistory = FixedSizeStack(10)
    }

    companion object {
        const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
        const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY"
        const val USER_ID_KEY = "USER_ID_KEY"
        const val USER_PROFILE_IMAGE = "USER_PROFILE_IMAGE"
        const val USER_NAME = "USER_NAME"
        const val USER_POSITION = "USER_POSITION"
        const val DEPARTMENT_NAME = "DEPARTMENT_NAME"
        const val USER_EMAIL = "user_email"
        const val USER_PHONE = "user_phone"
        const val SEARCH_HISTORY ="search_history"
    }
}