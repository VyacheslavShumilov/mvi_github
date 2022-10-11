package com.hfad.mvi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.mvi.data.Users
import com.hfad.mvi.data.services.ApiHelperImpl
import com.hfad.mvi.data.services.RetrofitBuilder
import com.hfad.mvi.databinding.ActivityMainBinding
import com.hfad.mvi.intent.MainIntent
import com.hfad.mvi.utils.ViewModelFactory
import com.hfad.mvi.viewModel.MainViewModel
import com.hfad.mvi.viewState.MainState
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),AdapterUsers.OnClickListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var adapterUsers = AdapterUsers(this, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        observeViewModel()
    }


    // TODO: Отрисовка списка
    private fun setupUI() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    binding.usersRecyclerView.context,
                    (binding.usersRecyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        binding.usersRecyclerView.adapter = adapterUsers
    }


    // TODO: Создание ViewModel
    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.api))
        )[MainViewModel::class.java]
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.LoadUser)
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Id -> {}
                    is MainState.Loading -> {
                        binding.btnFetchUser.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is MainState.UsersList -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnFetchUser.visibility = View.GONE
                        renderList(it.users)
                    }

                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, "${it.error}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(users: List<Users>) {
        users.let { data ->
            data.let { _users ->
                adapterUsers.addData(_users)
            }
        }
        adapterUsers.notifyDataSetChanged()
    }

    override fun onClickUser(login: String) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("LOGIN",login)
        startActivity(intent)
    }
}