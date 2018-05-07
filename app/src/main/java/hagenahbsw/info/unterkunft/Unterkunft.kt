package hagenahbsw.info.unterkunft

import android.content.Context
import com.google.gson.Gson
import org.json.JSONException
import java.util.ArrayList


class coord(val coordinates: Array<Double>) {
//Abweichung von der Großschreibung der Classes wegen JSON Umwandlung und der Identität zu den Namen im JSON-String
fun get(i: Int) = coordinates[i]
 }
class unterkunft(val name: String,
                 val desc: String,
                 val link_href: String,
                 val link_text: String,
                 val geometry:coord){
    fun inArea(minLon: Double, minLat: Double, maxLon: Double, maxLat: Double):Boolean{
        //function to determine whether unterkunft is in a given area
        return minLon <= this.geometry.coordinates[0] && maxLon >= this.geometry.coordinates[0] &&
                minLat <= this.geometry.coordinates[1] && maxLat >= this.geometry.coordinates[0]
    }
}
//Abweichung von der Großschreibung der Classes wegen JSON Umwandlung und der Identität zu den Namen im JSON-String

class unterkuenfte(val Unterkuenfte: Array<unterkunft>) {
//Abweichung von der Großschreibung der Classes wegen JSON Umwandlung und der Identität zu den Namen im JSON-String
    fun size() : Int = Unterkuenfte.size
    fun get(i:Int) = Unterkuenfte[i]



companion object {
    fun getUnterkuenfteFromFile(filename: String, context: Context): unterkuenfte {
        try {
            val gsonString = loadJsonFromAsset(filename, context)
            val myOtherunterkuenfte = Gson().fromJson(gsonString, unterkuenfte::class.java)
            return myOtherunterkuenfte
        } catch (e: JSONException) {
            e.printStackTrace()
            val myUnterkunft= unterkunft("void",
                    "void",
                    "https://wwww.bettundbike.de",
                    "void",
                    coord(arrayOf<Double>(0.0, 0.0)))
            val myOtherUnterkuenfte = unterkuenfte(arrayOf(myUnterkunft, myUnterkunft))
            return myOtherUnterkuenfte
            }

    }

    private fun loadJsonFromAsset(filename: String, context: Context): String {
        var gsonString: String
        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            gsonString = String(buffer, Charsets.UTF_8)
            return gsonString
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return """Unterkuenfte": [
            |{
                |"name": "void",
                |"desc": "void",
                |"link_href": "wwww.bettundbike.de",
                |"link_text": "Bett + Bike Info",
                |"geometry":
                |{"coordinates":
                |[10.0, 50.0]}
            |]"""

        }
    }
}
}
