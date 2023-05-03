package com.fajar.moviedb.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.moviedb.R
import com.fajar.moviedb.core.data.Resource
import com.fajar.moviedb.core.ui.MovieAdapter
import com.fajar.moviedb.databinding.FragmentSearchBinding
import com.fajar.moviedb.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val movieAdapter = MovieAdapter()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        if (activity != null) {
            binding.apply {
                rvResult.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = movieAdapter
                }


                movieAdapter.onItemClick = { selectedData ->
                    val moveToDetail = Intent(activity, DetailActivity::class.java)
                    moveToDetail.putExtra(DetailActivity.EXTRA_FILM, selectedData)
                    startActivity(moveToDetail)
                }
                swipeToRefresh.setOnRefreshListener {
                    observeSearchResult()
                }
                swipeToRefresh.isRefreshing = false
            }
            observeSearchResult()
        }

    }

    override fun onResume() {
        super.onResume()
        if (movieAdapter.itemCount > 0) {
            binding.onInitialSearchStateMessage.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.search)
        search.expandActionView()

        val searchView = search.actionView as SearchView

        searchView.apply {
            onActionViewExpanded()
            setIconifiedByDefault(false)
            isFocusable = true
            isIconified = false
            requestFocusFromTouch()
            requestFocus()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    clearFocus()
                    binding.rvResult.scrollToPosition(0)
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query != null && query.isNotEmpty()) {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                            viewError.root.visibility = View.GONE
                            viewEmpty.root.visibility = View.GONE
                            onInitialSearchStateMessage.visibility = View.GONE
                            rvResult.scrollToPosition(0)
                        }
                        searchViewModel.setSearchQuery(query)
                        observeSearchResult()
                    } else if (query.equals("")) {
                        movieAdapter.clearList()
                    }
                    return true
                }
            })
        }
    }


    private fun observeSearchResult() {
        binding.apply {
            searchViewModel.searchResult.observe(viewLifecycleOwner) { results ->
                if (results != null) {
                    when (results) {
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            val searchResult = results.data
                            progressBar.visibility = View.GONE
                            if (searchResult != null) {
                                movieAdapter.setData(searchResult)
                                rvResult.scrollToPosition(0)
                                swipeToRefresh.isRefreshing = false
                                if (searchResult.isEmpty()){
                                   viewEmpty.root.visibility = View.VISIBLE
                                    onInitialSearchStateMessage.visibility = View.GONE
                                }
                            }
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                           viewError.root.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}