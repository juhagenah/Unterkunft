package hagenahbsw.info.unterkunft

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.unterkunft_list_view)

        val unterkunftList = Unterkuenfte.getUnterkuenfteFromFile("bettundbike_extract.geojson", this)
        val listLaenge = unterkunftList.size()
        var filteredUnterkunftList: MutableList<Unterkunft> = mutableListOf()

        for (i in 0 until listLaenge) {
            val unterkunft = unterkunftList.get(i)
            val x_coord = unterkunft.geometry.coordinates[0]
            val y_coord = unterkunft.geometry.coordinates[1]
            val drin = unterkunft.inArea(8.0, 45.0, 11.0, 52.0)
            if (drin) {
                filteredUnterkunftList.add(unterkunft)
            }
        }

        val context = this
        listView.setOnItemClickListener { _, _, position, _->
            val selectedUnterkunft = filteredUnterkunftList[position]
            val detailIntent = UnterkunftDetailActivity.newIntent(context,selectedUnterkunft)
            startActivity(detailIntent)
        }

        val adapter = UnterkunftAdapter(this, filteredUnterkunftList)
        listView.adapter = adapter

    }
}