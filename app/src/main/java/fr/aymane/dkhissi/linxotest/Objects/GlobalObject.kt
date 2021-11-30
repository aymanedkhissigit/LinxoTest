package fr.aymane.dkhissi.linxotest.Objects

import androidx.recyclerview.widget.DiffUtil




data class GlobalObject (val userID : Int, val albumId : Int ,val nameAlbum : String, val nameAuthor : String ) {

    class DiffCallback : DiffUtil.ItemCallback<GlobalObject>() {
        override fun areItemsTheSame(oldItem: GlobalObject, newItem: GlobalObject): Boolean {
            return oldItem.albumId == newItem.albumId
        }

        override fun areContentsTheSame(oldItem: GlobalObject, newItem: GlobalObject): Boolean {
            return  oldItem.userID == newItem.userID
                    && oldItem.albumId == newItem.albumId
                    && oldItem.nameAlbum == newItem.nameAlbum
                    && oldItem.nameAuthor == newItem.nameAuthor
        }
    }
}