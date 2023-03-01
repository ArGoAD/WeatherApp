package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import org.json.JSONObject


//This constant show api key for fun get result
const val API_KEY = "08a93803070148b58e031443230103"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bGet.setOnClickListener {

            getResult("London")

        }

    }

    private fun getResult(name: String){

        //Create url with link(date about weather in this moment) and API_key, and city name
        val url = "https://api.weatherapi.com/v1/current.json?" +
                "key=$API_KEY&q=$name&aqi=no"


        //Use volley library for create queue request
        val queue = Volley.newRequestQueue(this)

        //create stringRequest for queue
        val stringRequest = StringRequest(Request.Method.GET,
            url,


            {
                response ->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")

                //Allow get only temp in C
                Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
            },

            //Show errors
            {
                Log.d("MyLog", "VolleyError: $it")
            }
        )

        queue.add(stringRequest)


    }
}