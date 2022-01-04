package dev.ogabek.jsonwithvalley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.*
import com.bumptech.glide.Glide
import dev.ogabek.jsonwithvalley.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        mainBinding.buttonParse.setOnClickListener {
            apiCall()
        }

    }

    private fun apiCall() {
        val jsonObject = JSONObject()
        val url = "https://some-random-api.ml/img/cat"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, jsonObject, { respond ->
                Toast.makeText(this, "API Ulandi", Toast.LENGTH_LONG).show()
                mainBinding.textViewResult.text = respond["link"].toString()
                Glide.with(mainBinding.imgCat.context).asBitmap().load(respond["link"]).into(mainBinding.imgCat)
            }, {
                Toast.makeText(this, "API Ulanmadi", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}