package com.example.mytask2
import MovieAdapter
import android.annotation.SuppressLint
import android.widget.TextView
import com.example.mytask2.Movie
import com.example.mytask2.R
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MovieListActivity : AppCompatActivity() {
    private val movieList = mutableListOf<Movie>()
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = "IMDB.EG"
        toolbar.subtitle="Movie APP"
        /*to set the toolbar as the action bar*/
        setSupportActionBar(toolbar)
        /*to get the username from the intent*/
        val username = intent.getStringExtra("username") ?: "User"
        /*to set the welcome text with the username*/
        findViewById<TextView>(R.id.welcomeText).text = "Welcome: $username"
        /*to set the recycler view*/
        val recyclerView = findViewById<RecyclerView>(R.id.movieRecyclerView)
        /*to set the layout manager to the recycler view to be a linear layout manager to display the items in a vertical list*/
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        /*to set the adapter*/
        adapter = MovieAdapter(movieList)
        /*to set the adapter to the recycler view*/
        recyclerView.adapter = adapter
        /*to load 10 movies*/
//        loadMovies(10)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("AppCrash", "Unhandled exception in thread ${thread.name}", throwable)
        }
        add_elmate("The Lord of the Rings: The Fellowship of the Ring","The Fellowship of the Ring (2001)A fellowship of hobbits, men, elves, and a dwarf embark on a quest to destroy the One Ring, an artifact of great power that threatens the entire world.","2001",R.drawable.ione)
        add_elmate("Game of Thrones","Set in the fictional Seven Kingdoms of Westeros, this epic fantasy series chronicles the struggles for power among noble families.","2011-2019",R.drawable.i2)
        add_elmate("Stranger Things","A group of young friends in a small town encounter supernatural forces and government conspiracies.","2016-Present",R.drawable.i3)
        add_elmate("Breaking Bad","A high school chemistry teacher diagnosed with terminal cancer turns to a life of crime to secure his family's future.","2008-2013",R.drawable.i4)
        add_elmate("The Dark Knight","Batman confronts the Joker, a psychopathic criminal mastermind who plans to plunge Gotham City into chaos.","2008",R.drawable.i5)
        add_elmate("Squid Game","Desperate contestants risk their lives in a series of deadly children's games for a chance to win a huge cash prize.","2021",R.drawable.i6)
        add_elmate("The Mandalorian","A lone Mandalorian bounty hunter protects a mysterious child in a dangerous galaxy.","2019-Present",R.drawable.i7)
        add_elmate("Money Heist","A group of highly skilled thieves plan and execute elaborate heists on the Royal Mint of Spain and the Bank of Spain.","2017-2021",R.drawable.i8)
        add_elmate("The Witcher","Geralt of Rivia, a mutated monster hunter, navigates a dangerous world filled with magic and monsters.","2019-Present",R.drawable.i9)
        add_elmate("Peaky Blinders","The Peaky Blinders, a crime gang based in Birmingham, England, rise to power in the aftermath of World War I.","2013-2022",R.drawable.i10)
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
    private fun add_elmate(Name:String,Description:String,Date_in:String,Image_in:Int){
        movieList.add(
            Movie(
                title = Name,
                description = Description,
                genre = Date_in,
                imageResId = Image_in
            )
        )
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
    var just_once:Boolean=true
    /* to add 5 items when the more button is clicked*/
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_more -> {
//                loadMovies(5)
                    add_elmate(
                        "Black Mirror",
                        "An anthology series exploring the dark side of technology and its impact on society.",
                        "2011-Present",
                        R.drawable.i11
                    )
                    add_elmate(
                        "The Walking Dead",
                        "A group of survivors navigate a post-apocalyptic world overrun by zombies.",
                        "2010-2022",
                        R.drawable.i12
                    )
                    add_elmate(
                        "Narcos",
                        "A crime drama series chronicling the rise and fall of notorious drug kingpin Pablo Escobar.",
                        "2015-2017",
                        R.drawable.i13
                    )
                    add_elmate(
                        "Sherlock",
                        "A modern adaptation of the classic detective stories featuring Sherlock Holmes and Dr. Watson.",
                        "2010-2017",
                        R.drawable.i14
                    )
                    add_elmate(
                        "The Office",
                        "A mockumentary-style comedy series following the lives of employees at the fictional Dunder Mifflin Paper Company.",
                        "2005-2013",
                        R.drawable.i15
                    )
                    Toast.makeText(this, "Five items added to the list", Toast.LENGTH_LONG).show()
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
