//package testRailApi.api
//
//import okhttp3.Credentials
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.Request
//import okhttp3.RequestBody
//import okhttp3.RequestBody.Companion.toRequestBody
//import okhttp3.Response
//import testRailApi.api.TestRailHelper.PASSWORD
//import testRailApi.api.TestRailHelper.TESTRAIL_URL
//import testRailApi.api.TestRailHelper.USERNAME
//import testRailApi.api.TestRailHelper.client
//
//object Request {
//
//    fun get(url: String): Response {
//        val request = Request.Builder()
//            .url("$TESTRAIL_URL/$url")
//            .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
//            .build()
//
//        return client.newCall(request).execute()
//    }
//
//    // Function to send a POST request to TestRail
//    fun post(url: String, jsonBody: String): Response {
//        val mediaType = "application/json".toMediaTypeOrNull()
//        val body: RequestBody = jsonBody.toRequestBody(mediaType)
//
//        val request = Request.Builder()
//            .url("$TESTRAIL_URL/$url")
//            .header("Authorization", Credentials.basic(USERNAME, PASSWORD))
//            .post(body)
//            .build()
//
//        return client.newCall(request).execute()
//    }
//}