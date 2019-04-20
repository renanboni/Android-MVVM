package com.boni.mock

import com.boni.data.BuildConfig
import com.boni.data.MockData
import com.google.gson.Gson
import okhttp3.*

@Suppress("IMPLICIT_CAST_TO_ANY")
class MockInterceptor: Interceptor {

    companion object {
        private val MEDIA_JSON = MediaType.parse("application/json")

        private const val GENERATE_TOKEN = "/GenerateToken"
        private const val CONTACTS = "/Contacts"
        private const val TRANSFERS = "/GetTransfers"
        private const val SEND_MONEY = "/SendMoney"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {

            Thread.sleep(2000)

            val path = chain.request().url().encodedPath()

            val mockResponse = when (path) {
                GENERATE_TOKEN -> MockData.getUser()
                CONTACTS -> MockData.getContacts()
                TRANSFERS -> MockData.getTransfers()
                SEND_MONEY -> {
                    val body = chain.request().body() as FormBody
                    val clientId = body.encodedValue(0)
                    val value = body.encodedValue(1).toFloat()

                    MockData.addTransfer(
                        clientId,
                        value
                    )
                }
                else -> ""
            }

            val response = Response.Builder()
                .message("Response (mock): ")
                .body(ResponseBody.create(MEDIA_JSON, Gson().toJson(mockResponse).toByteArray()))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(200)

            if(path == GENERATE_TOKEN) {
                response.addHeader("x-auth-token", "1d40d305-c836-43a2-b4db-acc56bcc1393")
            }

            return response.build()
        } else {
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}
