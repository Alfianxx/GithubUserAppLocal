package com.alfian.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfian.githubuserapp.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var myList = arrayListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUser.setHasFixedSize(true)

        parseJson()
        showRecyclerList()
    }

    private fun parseJson() {
        val inputStream: InputStream = assets.open("githubuser.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val jsonObj = JSONObject(json)
        val user = jsonObj.getJSONArray("users")

        for (i in 0 until user.length()) {
            val userData = user.getJSONObject(i)
            val dataUser = User()
            dataUser.username = userData.getString("username")
            dataUser.name = userData.getString("name")
            dataUser.avatar = resources.getIdentifier(
                userData.getString("avatar"),
                "drawable",
                "com.alfian.githubuserapp"
            )
            dataUser.company = userData.getString("company")
            dataUser.location = userData.getString("location")
            dataUser.repository = userData.getInt("repository")
            dataUser.follower = userData.getInt("follower")
            dataUser.following = userData.getInt("following")
            myList.add(dataUser)
        }
    }

    private fun showRecyclerList() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(myList)
        binding.rvUser.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val showDetail = Intent(this@MainActivity, DetailActivity::class.java)
                showDetail.putExtra(DetailActivity.EXTRA_USER, data)
                startActivity(showDetail)
            }
        })
    }


}