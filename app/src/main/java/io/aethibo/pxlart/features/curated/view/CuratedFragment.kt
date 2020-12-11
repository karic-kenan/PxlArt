package io.aethibo.pxlart.features.curated.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import io.aethibo.pxlart.R
import io.aethibo.pxlart.databinding.FragmentCuratedBinding
import io.aethibo.pxlart.features.curated.viewmodel.CuratedViewModel
import io.aethibo.pxlart.features.shared.adapter.PhotosAdapter
import io.aethibo.pxlart.features.shared.adapter.loadstate.PhotosLoadStateAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class CuratedFragment : Fragment(R.layout.fragment_curated), View.OnClickListener {

    private val binding: FragmentCuratedBinding by viewBinding()
    private val viewModel: CuratedViewModel by viewModel()
    private val photosAdapter: PhotosAdapter by lazy { PhotosAdapter() }

    private var fetchCuratedPhotosJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchCuratedPhotos()

        binding.curatedRetryButton.setOnClickListener(this)
        setupAdapter()
        setupRefreshOption()
    }

    private fun setupRefreshOption() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchCuratedPhotos()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun fetchCuratedPhotos() {
        // Make sure we cancel the previous job before creating a new one
        fetchCuratedPhotosJob?.cancel()

        fetchCuratedPhotosJob = lifecycleScope.launch {
            viewModel.getCuratedPhotos().collect {
                photosAdapter.submitData(it)
            }
        }
    }

    private fun setupAdapter() {
        binding.curatedRecyclerView.adapter = photosAdapter.withLoadStateHeaderAndFooter(
            header = PhotosLoadStateAdapter { photosAdapter.retry() },
            footer = PhotosLoadStateAdapter { photosAdapter.retry() }
        )

        binding.curatedRecyclerView.apply {
            itemAnimator = null
        }

        photosAdapter.addLoadStateListener { loadState ->

            // Only show the list if refresh succeeds.
            binding.curatedRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.curatedProgressBar.isVisible =
                loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.curatedRetryButton.isVisible =
                loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    context,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.curated_retry_button -> {
            }
        }
    }
}