package com.examples.data.mapper

import android.util.Log
import com.examples.entities.base.ErrorModel
import com.examples.entities.base.ErrorStatus
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class CloudErrorMapper @Inject constructor(private val gson: Gson) {

    fun mapToDomainErrorException(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                when {
                    throwable.code() == 401 ->
                        ErrorModel(ErrorStatus.UNAUTHORIZED)
                    else ->
                        getHttpError(throwable.response()?.errorBody())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel("TIME OUT!!",0,ErrorStatus.TIMEOUT)
            }

            // handle connection error
            is IOException -> {
                ErrorModel("CHECK CONNECTION",0,ErrorStatus.NO_CONNECTION)
            }

            is UnknownHostException -> {
                ErrorModel("CHECK CONNECTION",0,ErrorStatus.NO_CONNECTION)
            }
            else ->
                ErrorModel("NOT DEFIEND",0,ErrorStatus.NOT_DEFINED)
        }
        return errorModel!!
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            // use response body to get error detail
            val result = body?.string()
            Log.d("getHttpError", "getErrorMessage() called with: errorBody = [$result]")
            val json = Gson().fromJson(result, JsonObject::class.java)
            ErrorModel(json.toString(), 400, ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(ErrorStatus.NOT_DEFINED)
        }
    }
}