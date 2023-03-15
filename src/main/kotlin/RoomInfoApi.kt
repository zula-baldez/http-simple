import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RoomInfoApi {
    //pls/apex/f?p=2431:4:1019::/
    ///pls/apex/wwv_flow.show
    @POST("/pls/apex/wwv_flow.show")
    @FormUrlEncoded
    fun getRoomInfo(
        @Header("Cookie") cookie: String,
        @Field("p_request") p_request: String,
        @Field("p_instance") p_instance: String,
        @Field("p_flow_id") p_flow_id: String,
        @Field("p_flow_step_id") p_flow_step_id: String,
        @Field("p_arg_names") p_arg_names1: String,
        @Field("p_arg_values") p_arg_values1: String,
        @Field("p_arg_names") p_arg_names2: String,
        @Field("p_arg_values") p_arg_value2: String,
        @Field("p_arg_names") p_arg_names3: String,
        @Field("p_arg_values") p_arg_value3: String,

        ): Call<ResponseBody>

    @GET("/pls/apex/f?p=2431:4:1019:/")
    fun checkForRedirect(
        @Header("Cookie") cookie: String,


        ): Call<ResponseBody>

    @GET("/pls/apex/f?p=2431:4:1234:/")
    fun getHtmlWithPlugin(
        @Header("Cookie") cookie: String,
        ): Call<ResponseBody>


    @GET("auth/realms/itmo/protocol/openid-connect/auth")
    fun register(
        @Query("response_type") response_type: String = "code",
        @Query("scope") scope: String = "openid",
        @Query("client_id") client_id: String = "isu",
        @Query("redirect_uri") redirect_uri: String = "https://isu.ifmo.ru/api/sso/v1/public/login",
    ): Call<ResponseBody>

    @POST("auth/realms/itmo/login-actions/authenticate")
    @FormUrlEncoded
    fun authenticate(
        @Query("session_code") session_code: String,
        @Query("execution") scope: String = "7db2448a-e998-4527-8831-184f67f677f9",
        @Query("client_id") client_id: String = "isu",
        @Query("tab_id") tab_id: String,
        @Header("User-Agent") user_agent: String = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/110.0",
        @Header("Accept") accept: String = "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
        @Header("Accept-Language") accept_language: String = "en-US,en;q=0.5",
        @Header("Accept-Encoding") accept_encoding: String = "gzip, deflate, br",
        @Header("Content-Type") content_type: String = "application/x-www-form-urlencoded",
        @Header("Origin") origin: String = "null",
        @Header("DNT") dnt: String = "1",
        @Header("Connection") connection: String = "keep-alive",
        @Header("Cookie") cookie: String,
        @Header("Upgrade-Insecure-Requests") upgrade_insecure_requests: String = "1",
        @Header("Sec-Fetch-Dest") sec_fetch_dest: String = "document",
        @Header("Sec-Fetch-Mode") sec_fetch_mode: String = "navigate",
        @Header("Sec-Fetch-Site") sec_fetch_site: String = "same-origin",
        @Header("Sec-Fetch-User") sec_fetch_user: String = "?1",
        @Header("TE") te: String = "trailers",
        @Field("username") username: String = "336768",
        @Field("password") password: String = "528759645224Aleks.",
        @Field("credentialId") credential_id: String = "",

        ): Call<ResponseBody>

    @POST("/pls/apex/wwv_flow.accept")
    @FormUrlEncoded
    fun tryBookAAAAAAAAAAA(
        @Header("Cookie") cookie: String,
        @Field("flow_id") flow_id: String,
        @Field("p_flow_step_id") p_flow_step_id: String,
        @Field("p_instance") p_instance: String,
        @Field("p_request") p_request: String = "PASS_REQUEST",
        @Field("p_t02") isu_num: String = "336439",
        @Field("p_t03") numAndName: String = "[336439] Верещагин Е.С.",
        @Field("p_t10") againIsu: String = "336439",
        @Field("p_t12") aud_num: String = "1013",
        @Field("p_t14") typeOfTimeScheduling: String = "-603",
        @Field("p_t15") date: String = "16.03.2023",
        @Field("p_t16") roomId: String = "18865",
        @Field("p_t21") roomIdAgain: String = "18865",
        @Field("p_t23") text: String = "test",
        @Field("p_t26") start: String = "10:00",
        @Field("p_t27") end: String = "10:30",
        @Field("p_t28") people_num: String = "10",
        @Field("p_t30") numIsuAgainAgain: String = "336439",
        @Field("p_t31") phone: String = "+7 (953) 2607422",

        @Field("p_t35") hz: String = "day",
        @Field("p_t54") startDay: String = "15.03.2023",
        @Field("p_t20") loh: String = "24131907",

        //p_t20: 24131907
        ): Call<ResponseBody>


}
