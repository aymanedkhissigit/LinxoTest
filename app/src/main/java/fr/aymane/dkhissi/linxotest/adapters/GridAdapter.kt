package fr.aymane.dkhissi.linxotest.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.aymane.dkhissi.linxotest.R


class GridAdapter( var listUrl: MutableList<String> ) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view = when (viewType) {
            0 -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_itemphoto2, parent, false)
            }
            1 -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_itemphoto, parent, false)
            }
            2 -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_itemphoto3, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_itemphoto, parent, false)
            }
        }

        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return position % 3

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        try {
            holder.image1.load(listUrl[(position * 3)])
        }catch (ex: Exception) {

        }

        try {
            holder.image2.load(listUrl[(position * 3)]+1)

        } catch (ex: Exception) {

        }

        try {
            holder.image3.load(listUrl[(position * 3)]+2)

        } catch (ex: Exception) {

        }


    }

    override fun getItemCount(): Int {
        return Math.ceil((listUrl.size.toDouble() / 3.0)).toInt()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val image1 = mView.findViewById<ImageView>(R.id.img_photos1)
        val image2 = mView.findViewById<ImageView>(R.id.img_photos2)
        val image3 = mView.findViewById<ImageView>(R.id.img_photos3)
    }

    fun submitList(data: List<String>?) {
        listUrl = data as MutableList<String>
        notifyDataSetChanged()

    }
}
