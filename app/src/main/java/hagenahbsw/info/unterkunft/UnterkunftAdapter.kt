package hagenahbsw.info.unterkunft

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class UnterkunftAdapter(private val context: Context,
                          private val dataSource : MutableList<Unterkunft>) : BaseAdapter()  {
    //Analog to ReceipeAdapter from www.raywenderlich.com/186976/androis-list-view-tutorial-2

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_unterkunft, parent, false)

        val nameTextView = rowView.findViewById(R.id.unterkunft_list_name) as TextView
        val descTextView = rowView.findViewById(R.id.unterkunft_list_desc) as TextView
        val detailTextView = rowView.findViewById(R.id.unterkunft_list_detail) as TextView

        val unterkunft = getItem(position) as Unterkunft
        nameTextView.text = unterkunft.name
        descTextView.text = unterkunft.desc
        detailTextView.text = "[${unterkunft.geometry.coordinates[0].toString()}, " +
                "${unterkunft.geometry.coordinates[1].toString()}]"

        return rowView
    }
}
