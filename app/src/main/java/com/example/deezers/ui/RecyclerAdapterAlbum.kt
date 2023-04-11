import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deezers.R
import com.example.deezers.service.data.AlbumData

class RecyclerAdapterAlbum(private val albumList: List<AlbumData>, private val onClickListener: (Long) -> Unit)
    : RecyclerView.Adapter<RecyclerAdapterAlbum.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_second, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albumList[position], onClickListener)
    }

    override fun getItemCount() = albumList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val albumTitle: TextView = itemView.findViewById(R.id.album_title)
        private val albumCover: ImageView = itemView.findViewById(R.id.album_cover)

        fun bind(albumData: AlbumData, onClickListener: (Long) -> Unit) {
            albumTitle.text = albumData.title

            // Load the album cover image using Glide or another image loading library
            Glide.with(itemView.context)
                .load(albumData.cover)
                .into(albumCover)


            albumTitle.setOnClickListener {
                onClickListener(albumData.id)

            }
        }



    }
}
