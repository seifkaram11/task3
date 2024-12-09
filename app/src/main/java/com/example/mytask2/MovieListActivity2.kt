import android.annotation.SuppressLint
import android.widget.TextView
import com.example.mytask2.Movie
import com.example.mytask2.R
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MovieListActivity2 : AppCompatActivity() {
    private val movieList = mutableListOf<Movie>()
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val username = intent.getStringExtra("username") ?: "User"
        findViewById<TextView>(R.id.welcomeText).text = "Welcome: $username"
        val recyclerView = findViewById<RecyclerView>(R.id.movieRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MovieAdapter(movieList)
        recyclerView.adapter = adapter
        loadMovies(10)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("AppCrash", "Unhandled exception in thread ${thread.name}", throwable)
        }
    }
    private fun loadMovies(count: Int) {
        val startIndex = movieList.size + 1
        val endIndex = startIndex + count

        for (i in startIndex until endIndex) {
            movieList.add(
                Movie(
                    title = "Movie $i",
                    description = "This is the description for Movie $i",
                    genre = if (i % 2 == 0) "Action" else "Drama",
                    imageResId = R.drawable.sample_image
                )
            )
        }
        adapter.notifyItemRangeInserted(movieList.size - count, count)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
    /* to add 5 items when the more button is clicked*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_more -> {
                loadMovies(5)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        finishAffinity()
    }
}
