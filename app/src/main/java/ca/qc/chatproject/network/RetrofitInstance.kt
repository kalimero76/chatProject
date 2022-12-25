package ca.qc.chatproject.network

import ca.qc.chatproject.network.api.LoginApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val  BASE_URL = "https://ggproject.alwaysdata.net/"
// !!!!!!!!!!!!!! this class has not to be changed
class RetrofitInstance {
    companion object{

        // (2)
        private val retrofitInstance by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        // (1)
        val retrofitService: LoginApiService by lazy {
                                     // (3)
            retrofitInstance.create(LoginApiService::class.java)
        }
    }
}