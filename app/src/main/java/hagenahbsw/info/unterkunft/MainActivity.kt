package hagenahbsw.info.unterkunft

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.unterkunft_list_view)

        val unterkunftList = unterkuenfte.getUnterkuenfteFromFile("bettundbike_extract.geojson", this)
        val listLaenge = unterkunftList.size()
        var filteredUnterkunftList: MutableList<unterkunft> = mutableListOf()

        for (i in 0 until listLaenge) {
            val unterkunft = unterkunftList.get(i)
            val x_coord = unterkunft.geometry.coordinates[0]
            val y_coord = unterkunft.geometry.coordinates[1]
            val drin = unterkunft.inArea(8.0, 45.0, 11.0, 52.0)
            if (drin) {
                filteredUnterkunftList.add(unterkunft)
            }
        }

        val FilteredListLaenge = filteredUnterkunftList.size
        val listItems = arrayOfNulls<String>(FilteredListLaenge)
        for (i in 0 until FilteredListLaenge) {
            val unterkunft = filteredUnterkunftList[i]
            listItems[i] = "${unterkunft.name} \n" +
                    "${unterkunft.desc} \n" +
                    "[${unterkunft.geometry.coordinates[0].toString()}," +
                    " ${unterkunft.geometry.coordinates[1].toString()}]"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter

    }
}