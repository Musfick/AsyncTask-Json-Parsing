package com.foxhole.asynctaskjsonparsing

import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Musfick Jamil on 4/25/2020$.
 */

class ExampleAsyncTask : AsyncTask<Void, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        println("debug : PreExecute call")
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun doInBackground(vararg params: Void?): String? {

        var line : String? = ""

        try {
            val urls = URL("https://jsonplaceholder.typicode.com/posts")
            val conn = urls.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            val inputStream = conn.inputStream

            val inputReader = InputStreamReader(inputStream)

            val bufferReader = BufferedReader(inputReader)

            for (l in bufferReader.lines()){
                line += l
            }
        }catch (e : Exception){

        }

        return line
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)

        val jsonArray = JSONArray(result)
        for (i in 0..jsonArray.length()-1) {
            val jsonObject = jsonArray.getJSONObject(i)
            println("debug : PostExecute call ${jsonObject.getString("title")}")
        }
    }

}