package io.aethibo.pxlart.ui.search.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import by.kirich1409.viewbindingdelegate.viewBinding
import io.aethibo.pxlart.R
import io.aethibo.pxlart.databinding.FragmentSearchBinding
import io.aethibo.pxlart.ui.search.viewmodel.SearchViewModel
import io.aethibo.pxlart.ui.shared.adapter.PhotosAdapter
import io.aethibo.pxlart.ui.shared.adapter.loadstate.PhotosLoadStateAdapter
import io.aethibo.pxlart.ui.utils.hideKeyboard
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search), SearchView.OnQueryTextListener {

    private val binding: FragmentSearchBinding by viewBinding()
    private val viewModel: SearchViewModel by viewModel()
    private val photosAdapter: PhotosAdapter by lazy { PhotosAdapter() }

    private var searchJob: Job? = null

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = ""
        fun newInstance() = SearchFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding.searchRetryButton.setOnClickListener {
            photosAdapter.retry()
        }

        initAdapter()
        savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (!outState.isEmpty)
            outState.putString(
                LAST_SEARCH_QUERY,
                DEFAULT_QUERY
            )
    }

    private fun handleScrollToTop() {
        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            photosAdapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.searchRecyclerView.scrollToPosition(0) }
        }
    }

    private fun initAdapter() {

        binding.searchRecyclerView.apply {
            adapter = photosAdapter.withLoadStateHeaderAndFooter(
                header = PhotosLoadStateAdapter { photosAdapter.retry() },
                footer = PhotosLoadStateAdapter { photosAdapter.retry() })

            itemAnimator = null
        }

        photosAdapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.searchRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.searchProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.searchRetryButton.isVisible = loadState.source.refresh is LoadState.Error

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search_menu)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchForPhotos(query)
            handleScrollToTop()
            hideKeyboard()
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean = true

    private fun searchForPhotos(query: String) {
        // Make sure we cancel the previous job before creating new one
        searchJob?.cancel()

        searchJob = lifecycleScope.launch {
            viewModel.getSearchResult(query).collect {
                photosAdapter.submitData(it)
            }
        }
    }
}