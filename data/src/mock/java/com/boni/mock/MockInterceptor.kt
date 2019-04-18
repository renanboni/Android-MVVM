package com.boni.mock

import com.boni.data.BuildConfig
import okhttp3.*

class MockInterceptor: Interceptor {

    companion object {
        private val MEDIA_JSON = MediaType.parse("application/json")

        private const val GENERATE_TOKEN = "/GenerateToken"
        private const val CONTACTS = "/Contacts"
        private const val TRANSFERS = "/GetTransfers"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {

            val path = chain.request().url().encodedPath()

            val mockResponse = when (path) {
                GENERATE_TOKEN -> user
                CONTACTS -> contacts
                TRANSFERS -> transfers
                else -> ""
            }

            val response = Response.Builder()
                .message("Response (mock): ")
                .body(ResponseBody.create(MEDIA_JSON, mockResponse.toByteArray()))
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .code(200)
                .build()

            return response
        } else {
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }
}


const val user = """
{
   "name": "Renan Boni",
    "email": "renan.boni@usp.br",
    "avatar": "dasdas"
}
"""

const val contacts = """
    [
	{
		"id": "1",
		"name": "Renan",
		"phone": "(18)99137-0488",
		"avatar": ""
	},
	{
		"id": "2",
		"name": "Renan",
		"phone": "(18)99137-0488",
		"avatar": ""
	}
]
"""

const val transfers = """
[
	{
		"id": "10",
		"clientId": "1",
		"value": 100,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	},
	{
		"id": "10",
		"clientId": "1",
		"value": 200,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	},
	{
		"id": "10",
		"clientId": "2",
		"value": 300,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	},
	{
		"id": "10",
		"clientId": "2",
		"value": 400,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	}
]
"""