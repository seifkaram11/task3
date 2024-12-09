import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask2.Movie
import com.example.mytask2.R
class MovieAdapter( private val movieList : List<Movie> ) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.movieImage)
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        val movieDescription: TextView = itemView.findViewById(R.id.movieDescription)
        val movieGenre: TextView = itemView.findViewById(R.id.movieGenre)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieImage.setImageResource(movie.imageResId)
        holder.movieTitle.text = movie.title
        holder.movieDescription.text = movie.description
        holder.movieGenre.text = "Genre: ${movie.genre}"
        holder.itemView.animation = android.view.animation.AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.fade_in
        )
    }
    override fun getItemCount(): Int = movieList.size
}