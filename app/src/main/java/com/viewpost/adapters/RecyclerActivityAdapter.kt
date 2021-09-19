package com.viewpost.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mostpopular.NyTimesResponse
import com.viewpost.databinding.EachRowBinding
import com.viewpost.ui.DetailActivity
/*
* Showing the list of most viewed post
*
* */

class RecyclerActivityAdapter(private var results: List<NyTimesResponse.Result>) :
    RecyclerView.Adapter<RecyclerActivityAdapter.PostViewHolder>() {
    private lateinit var binding: EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        binding.root.setOnClickListener {
            parent.context.startActivity(Intent(parent.context, DetailActivity::class.java))
        }
        return PostViewHolder(binding.root)
    }
   /*
   * set the data
   * */
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.title.text = results[position].title
        binding.author.text = results[position].byline
        binding.date.text = results[position].publishedDate
        if (position < results.size) {
            val result = results.get(position)
            if (result.media.isNotEmpty()){
                val media = result.media[0]
                val url=media.mediaMetadata[0].url
                Glide.with(binding.root.context)
                    .load(url).into(binding.imgRound);
            }


        }


    }

    override fun getItemCount(): Int = results.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    /* get the data from repository */
    fun setData(postList: NyTimesResponse) {
        this.results = postList.results
        notifyDataSetChanged()
    }
}