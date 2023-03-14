import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object InfoHandler {
    val ISU_APP_COOKIE: String = "ISU_AP_COOKIE=ORA_WWV-kKQ8ZBYzy4plkGwe7wF9i5hH"
    val ISU_LIB_SID: String = "ISU_LIB_SID=ORA_WWV-9wgltnwtLiGaJR5elh7MTxvG"
    val ORA_WWV_RAC_INSTANCE: String = "ORA_WWV_RAC_INSTANCE=2"
    val REMEMBER_SSO: String =
        "REMEMBER_SSO=FC84F51D11B63EA68D58127C20BBD205:D8C00EED818CD17B9CFF727951816DD5950A7AE94044D326056EEB3D5F4A4A3C451F02C5C70D9CFEAAA40DB2CF0736D0"
    val p_request: String = "PLUGIN=F049D55FF3C75840E1103EA04E42C34BCAB274271079987220403CB313E426B3"
    val p_instance: String = "111945333926235"
    val p_flow_id: String = "2431"
    val p_flow_step_id: String = "4"
    val parser: ScheduleParser = ScheduleParser()
    private val client = OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).followRedirects(true)
        .followSslRedirects(true).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://isu.ifmo.ru/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    fun getRoomInfo(roomId: Int, date: String): ScheduledRoom {
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
            roomId.toString(),
            "P4_DATE",
            date

        )
        var parsed: ScheduledRoom = ScheduledRoom("", HashMap(), -1);
        val response = request.getRoomInfo(ISU_APP_COOKIE,
            p_request,
            p_instance,
            p_flow_id,
            p_flow_step_id,
            "P4_AUD_SEL",
            "-17",
            "P4_ROOMS_ID2",
            roomId.toString(),
            "P4_DATE",
            date
        )
        //println(response.execute().body()?.string())
        parsed = parser.parseSchedule(response.execute().body()?.string(), roomId, date)

        /*call(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    //println(response.body()?.string())
                    parsed = parser.parseSchedule(response.body()?.string(), roomId, date)


                } else {
                    val errorMessage = response.message() // Get error message
                    print(errorMessage)
                }
                return
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                val errorMessage = t.message // Get error message
                print(errorMessage)
                return
            }
        })*/
        return parsed

    }

}


