package com.foxhole.asynctaskjsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ExampleAsyncTask().execute()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/posts"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                val jsonArray = JSONArray(response)
                for (i in 0..jsonArray.length()-1) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    println("debug : PostExecute call ${jsonObject.getString("title")}")
                }
            },
            Response.ErrorListener { })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}
