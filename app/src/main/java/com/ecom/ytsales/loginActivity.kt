package com.ecom.ytsales

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.ecom.ytsales.databinding.ActivityLoginBinding
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


class loginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@loginActivity, R.layout.activity_login)

        getUserList()
    }

    fun getUserList() {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.login("Developer5@gmail.com",123456)
                if (response.isSuccessful()) {
                    //your code for handaling success response


                } else {
                    Toast.makeText(
                        this@loginActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }

    }


   // requesting and getting response
    interface ApiInterface {
        @POST("/api/authaccount/login")
        suspend fun login
                    (@Query("email") email: String, @Query("password") password :Int): Response<ResponseLogin>

     }




    // initialize retrofit
    object RetrofitClient {

        fun getInstance(): Retrofit {
            var mHttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            var mOkHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(mHttpLoggingInterceptor)
                .build()


            var retrofit: Retrofit = retrofit2.Retrofit.Builder()
                .baseUrl("https://www.restapi.adequateshop.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(mOkHttpClient)
                .build()
            return retrofit
        }
    }

}