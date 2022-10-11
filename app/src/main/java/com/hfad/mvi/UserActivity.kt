package com.hfad.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.hfad.mvi.data.User
import com.hfad.mvi.data.services.ApiHelperImpl
import com.hfad.mvi.data.services.RetrofitBuilder
import com.hfad.mvi.databinding.ActivityUserBinding
import com.hfad.mvi.intent.UserIntent
import com.hfad.mvi.utils.UserModelFactory
import com.hfad.mvi.viewModel.UserViewModel
import com.hfad.mvi.viewState.UserState
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private var login: String = ""
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val args = intent.extras
        login = args?.get("LOGIN").toString()

        setupViewModel()
        observeViewModel()
    }

    private fun setupViewModel() {
        userViewModel = ViewModelProviders.of(
            this,
            UserModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.api
                )
            )
        )[UserViewModel::class.java]
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            userViewModel.userIntent.send(UserIntent.LoadUser)
            userViewModel.handlerIntent(login)
            userViewModel.state.collect {
                when (it) {
                    is UserState.Id -> {
                    }
                    is UserState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is UserState.UserOne -> {
                        binding.progressBar.visibility = View.GONE
                        renderUser(it.user)
                    }
                    is UserState.Error -> {
                        binding.progressBar.visibility = View.VISIBLE
                        Toast.makeText(this@UserActivity, "${it.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun renderUser(user: User) {
        user.let { _user ->
            with(binding) {
                Glide
                    .with(this@UserActivity)
                    .load(_user.avatar_url)
                    .into(avatarUserImageView)
                loginUserTextView.text = _user.login
                userIdTextView.text = _user.id.toString()
                userNodeIdTextView.text = _user.node_id
                userFollowersUrlTextView.text = _user.followers_url
                userTypeTextView.text = _user.type
            }
        }
    }
}