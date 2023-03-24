package com.example.myapplication.api

import com.example.myapplication.model.Book
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import javax.inject.Inject

class Data @Inject constructor(): DataSourceInterface{
    private val gson = Gson()

    override suspend fun getBooks(): List<Book> = withContext(Dispatchers.IO) {
        var result: String
        // establish a connection
        var connection: HttpURLConnection? = null

        // try to establish a connection
        try {
            val url = URL("https://run.mocky.io/v3/b07fca4a-01e0-4000-985d-8baa538dee35")
            connection = url.openConnection() as HttpURLConnection
            // do we get data?
            connection.doInput = true

            // de we send data?
            // really necessary if you want to send data
            connection.doOutput = true

            val  httpResult: Int = connection.responseCode
            if (httpResult == HttpURLConnection.HTTP_OK){
                // we want to read the data from the website
                val inputStream = connection.inputStream

                val reader = BufferedReader(InputStreamReader(inputStream))
                val stringBuilder = StringBuilder()
                var line: String?
                try{
                    // while reader has something to read
                    while (reader.readLine().also { line = it } != null){
                        stringBuilder.append(line + "\n")
                    }
                }catch (e: IOException){
                    e.printStackTrace()
                }finally {
                    try {
                        inputStream.close()
                    }catch (e: IOException){
                        e.printStackTrace()
                    }
                }
                result = stringBuilder.toString()
            }else{
                result = connection.responseMessage
            }
        }catch (e: SocketTimeoutException){
            result = "Connection Timeout"
        }catch (e: java.lang.Exception){
            result = "Error : " + e.message
        }finally {
            // disconnect the connection
            connection?.disconnect()
        }

        gson.fromJson(result, Array<Book>::class.java).toList()
    }
}