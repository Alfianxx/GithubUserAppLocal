package com.alfian.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.alfian.githubuserapp.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail User"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        val repoText = "${user.repository} \n repository"
        val followersText = "${user.follower} \n followers"
        val followingText = "${user.following} \n following"
        val companyText = " ${user.company}"
        val locationText = " ${user.location}"

        binding.tvName.text = user.name
        binding.tvUsername.text = user.username
        binding.tvCompany.text = companyText
        binding.tvLocation.text = locationText
        binding.tvRepository.text = repoText
        binding.tvFollowers.text = followersText
        binding.tvFollowing.text = followingText

        Glide
            .with(this)
            .load(user.avatar)
            .into(binding.imgAvatar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}