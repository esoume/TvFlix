package com.android.tvmaze.shows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.android.tvmaze.R
import com.android.tvmaze.databinding.NetworkFailureListItemBinding

class ShowsStateViewHolder(
    private val binding: NetworkFailureListItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progress.isVisible = loadState is LoadState.Loading
        binding.errorGroup.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ShowsStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.network_failure_list_item, parent, false)
            val binding = NetworkFailureListItemBinding.bind(view)
            return ShowsStateViewHolder(binding, retry)
        }
    }
}