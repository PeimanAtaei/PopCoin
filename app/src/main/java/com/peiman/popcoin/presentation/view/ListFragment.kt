package com.peiman.popcoin.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.peiman.popcoin.R
import com.peiman.popcoin.databinding.FragmentListBinding
import com.peiman.popcoin.presentation.viewModel.MainViewModel
import com.peiman.popcoin.presentation.adapter.Top10ListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    companion object {
        private const val TAG = "ListFragment"
    }

    private lateinit var listBinding: FragmentListBinding
    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var top10ListAdapter: Top10ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    // Setup UI, Listeners, and Observers
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listBinding = FragmentListBinding.bind(view)
        mainViewModel = (activity as MainActivity).mainViewModel
        setupViews()
        setListeners()

        // Request data from ViewModel
        mainViewModel.getTop10List("eur", "market_cap_desc", 10, 1, false)
    }

    // Setup RecyclerView with adapter
    private fun setupViews() {

        listBinding.topList.apply {
            adapter = top10ListAdapter
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
        }
        setObservers()
    }

    // Observe the ViewModel's live data and update the UI
    private fun setObservers() {
        mainViewModel.top10List.observe(viewLifecycleOwner) {

            // Submit the list of data to the adapter
            top10ListAdapter.differ.submitList(it.data)
            // Hide loading and show list
            listBinding.listLoading.visibility = View.GONE
            listBinding.topList.visibility = View.VISIBLE
        }
    }

    // Setup listener for RecyclerView item clicks
    private fun setListeners() {
        top10ListAdapter.setOnItemClickListener {
            // Navigate to the detail fragment on item click
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(it.id)
            findNavController().navigate(action)
        }
    }
}