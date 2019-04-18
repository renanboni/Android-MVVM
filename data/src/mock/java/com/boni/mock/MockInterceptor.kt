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

            Thread.sleep(2000)

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
		"name": "Renan Boni",
		"phone": "(18)99137-0488",
		"avatar": "https://cdn.iconscout.com/icon/free/png-256/avatar-372-456324.png"
	},
	{
		"id": "2",
		"name": "Joao Antonio",
		"phone": "(11)91234-0488",
		"avatar": "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/10_avatar-512.png"
	},
    {
		"id": "3",
		"name": "Joao Bernardo",
		"phone": "(11)98482-0488",
		"avatar": "https://cdn.iconscout.com/icon/free/png-256/avatar-372-456324.png"
	},
    {
		"id": "4",
		"name": "Maria Luiza",
		"phone": "(16)99882-0488",
		"avatar": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD"
	},
    {
		"id": "5",
		"name": "Luiza Antonieta",
		"phone": "(18)92022-1222",
		"avatar": "https://cdn.iconscout.com/icon/free/png-256/avatar-369-456321.png"
	},
    {
		"id": "6",
		"name": "Neymar Junior",
		"phone": "(19)98722-1111",
		"avatar": "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png"
	},
    {
		"id": "7",
		"name": "Rogerio Ceni",
		"phone": "(22)98221-1234",
		"avatar": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD"
	},
    {
		"id": "8",
		"name": "Palmeiras Nao",
		"phone": "(11)91231-12334",
		"avatar": "https://cdn3.iconfinder.com/data/icons/business-avatar-1/512/10_avatar-512.png"
	},
    {
		"id": "9",
		"name": "Tem Mundial",
		"phone": "(18)90828-12345",
		"avatar": "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Bearded_Man-17-512.png"
	},
    {
		"id": "10",
		"name": "Santo Amaro",
		"phone": "(27)92832-0488",
		"avatar": "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png"
	},
    {
		"id": "11",
		"name": "Luis Antonio",
		"phone": "(18)99137-0488",
		"avatar": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD"
	},
    {
		"id": "12",
		"name": "Almeida Antonio",
		"phone": "(18)99137-0488",
		"avatar": "https://cdn3.iconfinder.com/data/icons/avatars-15/64/_Bearded_Man-17-512.png"
	},
    {
		"id": "13",
		"name": "Joao Antonio",
		"phone": "(11)82822-0488",
		"avatar": "https://cdn.pixabay.com/photo/2016/11/18/23/38/child-1837375_960_720.png"
	},
    {
		"id": "14",
		"name": "Vitoria Antonia",
		"phone": "(18)99999-1188",
		"avatar": "https://cdn.iconscout.com/icon/free/png-256/avatar-369-456321.png"
	},
    {
		"id": "15",
		"name": "Junior Nascimento",
		"phone": "(18)92558-1188",
		"avatar": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS1sfG6usLmkeShGnkAPRXcLwRLwSzXyWUEASrxGvCyDLB_3kdD"
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
		"id": "5",
		"clientId": "4",
		"value": 200,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	},
	{
		"id": "4",
		"clientId": "4",
		"value": 26000,
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
		"id": "14",
		"clientId": "5",
		"value": 1700,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	},
	{
		"id": "11",
		"clientId": "12",
		"value": 300,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	},
	{
		"id": "11",
		"clientId": "14",
		"value": 12000,
		"token": "1d40d305-c836-43a2-b4db-acc56bcc1393",
		"data": "2016-08-02T14:25:37.55"
	}
]
"""