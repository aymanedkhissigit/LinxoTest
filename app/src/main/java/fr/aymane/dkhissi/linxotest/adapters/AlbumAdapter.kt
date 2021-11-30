package fr.aymane.dkhissi.linxotest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.aymane.dkhissi.linxotest.Objects.GlobalObject
import fr.aymane.dkhissi.linxotest.R



class AlbumAdapter(val onItemClick: (Int) -> Unit ) : androidx.recyclerview.widget.ListAdapter<GlobalObject,
        AlbumViewHolder>(GlobalObject.DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            AlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item1, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder (holder : AlbumViewHolder, position : Int ) {
        val album = getItem(position)
        holder.txt_album_name.text = "Album name : ${album.nameAlbum}"
        holder.txt_author_name.text = "Author name : ${album.nameAuthor}"
        holder.cardView.setOnClickListener {
            onItemClick(album.albumId)
        }
    }


}

class AlbumViewHolder ( view : View) : RecyclerView.ViewHolder ( view ) {

    val txt_album_name: TextView = view . findViewById (R.id.txt_album_name )
    val txt_author_name : TextView = view . findViewById (R.id.txt_author_name )
    val cardView: CardView = view . findViewById (R.id.cardView )
}