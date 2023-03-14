import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object InfoHandler {
    val ISU_APP_COOKIE: String = "ISU_AP_COOKIE=ORA_WWV-VxisGxe4umyc5wzJ44Nz3HAz"
    val p_request: String = "PLUGIN=1AACCD2ABE63CF378BB0B1CBA21E4F4F67C9922EFAA73885C80F4902F20712B1"
    val p_instance: String = "101939722861776"
    val p_flow_id: String = "2431"
    val p_flow_step_id: String = "4"
    val parser: ScheduleParser = ScheduleParser()

    enum class Type {
        MEETING_ROOM,
        AUDITORIUM
    }

    enum class Place {
        KRONVA, LOMO
    }

    private val client = OkHttpClient.Builder().addInterceptor(LoggingInterceptor()).followRedirects(true)
        .followSslRedirects(true).build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://isu.ifmo.ru/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    //P4 AUD SEL -17
    //kronva aud 516,20005,20007
    //    1404 1405

    //    //P4 AUD SEL -603
    //    //kronva kovork 18863,18865,18867,18871
    //    //1301 1311 1314 1312
    private val KronvCovorkingAud =
        hashMapOf<Int, Int>(Pair(1301, 18863), Pair(1311, 18865), Pair(1314, 18867), Pair(1312, 18871))
    private val KronvAuditorium = hashMapOf<Int, Int>(Pair(2337, 29), Pair(2336, 30), Pair(2326, 36), Pair(2316, 41),
        Pair(1410, 73), Pair(1419, 77), Pair(2407, 82), Pair(2412, 84),
        Pair(2414, 85), Pair(2416, 86), Pair(2426, 91), Pair(2433, 93),
        Pair(1229, 95), Pair(2304, 145), Pair(1316, 200), Pair(2201, 215),
        Pair(1404, 516), Pair(1405, 20005))
    fun getRoomInfo(roomId: Int, date: String): ScheduledRoom {
        val request = retrofit.create(APIInterface::class.java)

        var parsed: ScheduledRoom = ScheduledRoom("", HashMap(), -1);
        val response = request.getRoomInfo(
            ISU_APP_COOKIE,
            p_request,
            p_instance,
            p_flow_id,
            p_flow_step_id,
            "P4_AUD_SEL",
            "-603",
            "P4_ROOMS_ID2",
            roomId.toString(),
            "P4_DATE",
            date
        )
        //no errors handling!
        parsed = parser.parseSchedule(response.execute().body()?.string(), roomId, date)


        return parsed

    }
    //time in format 8:30
    fun getFreeRooms(place: Place, time: String, date: String, type: Type) : ArrayList<Int>{
        val freeList  = ArrayList<Int>()
        if (place == Place.KRONVA) {
            if (type == Type.AUDITORIUM) {

                return getFreeList(KronvCovorkingAud, time, date) //todo map aud
            }
            if(type == Type.MEETING_ROOM) {
                return getFreeList(KronvCovorkingAud, time, date)

            }
        }
        return ArrayList()

    }
    private fun getFreeList(aud: HashMap<Int, Int>, time: String, date: String) : ArrayList<Int> {
        val freeList  = ArrayList<Int>()
        for (pair in aud.entries.iterator() ) {
            val parsed = getRoomInfo(pair.value, date)
            if(parsed.schedule[time] == true) {
                freeList.add(pair.key)
            }
        }
        return freeList
    }
}


