package com.viewpost.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.viewpost.R
import com.viewpost.adapters.RecyclerActivityAdapter
import com.viewpost.databinding.ActivityRecyclerBinding
import com.viewpost.utils.ApiState
import com.viewpost.utils.NetworkState
import com.viewpost.viewmodels.NyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

/*
* Main screen which contain list of popular post
* */
@AndroidEntryPoint
class MostPopularPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private lateinit var postAdapter: RecyclerActivityAdapter
    private val mainViewModel: NyViewModel by viewModels()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        handleData()
        binding.btnGetPost.setOnClickListener {
            handleData()
        }


    }

    private fun handleData() {
        if (NetworkState().checkForInternet(this)) {
            handleDataFromApi()
            binding.btnGetPost.isVisible = false

        } else {
            Toast.makeText(this, getString(R.string.internet_connection), Toast.LENGTH_LONG).show()
            handleViewVisibility(false, false)
            binding.btnGetPost.isVisible = true
        }    }

    /*
    * initialise adapter
    * */
    private fun initRecyclerview() {
        postAdapter = RecyclerActivityAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MostPopularPostActivity)
            adapter = postAdapter
        }
    }


    /*
    *
    * get data from repository and handle it
    * */
    private fun handleDataFromApi() {
        mainViewModel.getMostViewApiData()
        lifecycleScope.launchWhenStarted {
            mainViewModel._postStateFlow.collect { it ->
                when (it) {
                    is ApiState.Loading -> {
                        handleViewVisibility(false, true)
                    }
                    is ApiState.Failure -> {
                        handleViewVisibility(false, false)
                    }
                    is ApiState.Success -> {
                        handleViewVisibility(true, false)
                        postAdapter.setData(it.nyData)
                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty -> {

                    }
                }
            }
        }

    }

    /*
*
* handle view visibility of view
* */
    private fun handleViewVisibility(recyclerViewVisible: Boolean, progressBarVisible: Boolean) {
        binding.recyclerview.isVisible = recyclerViewVisible
        binding.progressBar.isVisible = progressBarVisible
    }

}