package com.bitcode.webservices1

import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class Util {

    companion object {
        fun makeSimpleWebRequest() {
            var url = URL("https://www.flipkart.com/")
            var httpUrlConnection = url.openConnection()

            httpUrlConnection.connect()

            var inputStream = httpUrlConnection.getInputStream()

            var data = ByteArray(1024 * 2)
            var count = 0

            var stringBuffer = StringBuffer()

            while( count != -1) {
                count = inputStream.read(data)
                if(count == -1) {
                    break
                }
                stringBuffer.append(String(data, 0, count))
            }

            inputStream.close()
            Log.e("response", stringBuffer.toString())


        }

        fun getUserDetails(userId : Int) : UserModel {
            var url = URL("https://reqres.in/api/users/$userId")
            var httpUrlConnection = url.openConnection() as HttpURLConnection

            /*httpUrlConnection.requestMethod = "GET"
            httpUrlConnection.connectTimeout = 30000
            httpUrlConnection.doInput = true*/

            /*httpUrlConnection.doOutput = true
            httpUrlConnection.outputStream //write some data to this stream*/

            httpUrlConnection.connect()

            mt("res code: ${httpUrlConnection.responseCode}")
            mt("res message: ${httpUrlConnection.responseMessage}")
            mt("content type: ${httpUrlConnection.contentType}")
            mt("content len: ${httpUrlConnection.contentLength}")

            var data = ByteArray(1024 * 2)
            var count = 0
            var stringBuffer = StringBuffer()
            do {
                count = httpUrlConnection.inputStream.read(data)
                if(count != -1) {
                    stringBuffer.append(String(data, 0, count))
                }
            }while(count != -1)

            mt("${stringBuffer.toString()}")

            var jsonResponse  = JSONObject(stringBuffer.toString())
            var jsonUser = jsonResponse.getJSONObject("data")

            var userModel = UserModel(
                jsonUser.getInt("id"),
                jsonUser.getString("email"),
                jsonUser.getString("first_name"),
                jsonUser.getString("last_name"),
                jsonUser.getString("avatar")
            )

            mt("user model: ${userModel.toString()}")

            return userModel
        }

        fun mt(text : String) {
            Log.e("tag", text)
        }
    }
}