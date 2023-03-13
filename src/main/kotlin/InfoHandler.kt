import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object InfoHandler {
    val ISU_APP_COOKIE: String = "ISU_AP_COOKIE=ORA_WWV-9wgltnwtLiGaJR5elh7MTxvG"
    val ISU_LIB_SID: String = "ISU_LIB_SID=ORA_WWV-9wgltnwtLiGaJR5elh7MTxvG"
    val ORA_WWV_RAC_INSTANCE: String = "ORA_WWV_RAC_INSTANCE=2"
    val REMEMBER_SSO: String =
        "REMEMBER_SSO=FC84F51D11B63EA68D58127C20BBD205:D8C00EED818CD17B9CFF727951816DD5950A7AE94044D326056EEB3D5F4A4A3C451F02C5C70D9CFEAAA40DB2CF0736D0"
    val p_request: String = "PLUGIN=EAE6166322D1B57EE5653B4A4C8BA147FC2BCA7C3102BF5F9180B8EF40B2DC1F"
    val p_instance: String = "102873203866118"
    val p_flow_id: String = "2431"
    val p_flow_step_id: String = "4"

    private val client = OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).followRedirects(true)
        .followSslRedirects(true).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://isu.ifmo.ru/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    fun getRoomInfo() {
        val request = retrofit.create(APIInterface::class.java)

        val call = request.getRoomInfo(
            ISU_APP_COOKIE,
            p_request,
            p_instance,
            p_flow_id,
            p_flow_step_id,
            "P4_AUD_SEL",
            "-17",
            "P4_ROOMS_ID2",
            "29,30,36,41,73,77,82,84,85,86,91,93,95,145,200,215,516,20005,20007",
            "P4_DATE",
            "25.03.2023"

        )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    println(response.body()?.string())
                    // Handle successful response
                } else {
                    val errorMessage = response.message() // Get error message
                    print(errorMessage)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val errorMessage = t.message // Get error message
                print(errorMessage)
            }
        })

    }

}


