package com.example.flickrandroidclient.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrandroidclient.databinding.FlickrSearchFragmentBinding
import com.example.flickrandroidclient.viewmodel.FlickrViewModel
import com.example.flickrandroidclient.recyclerview.FlickrPhotoPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FlickrSearchFragment : Fragment() {
    private var firstFragmentBinding: FlickrSearchFragmentBinding? = null
    private val flickrViewModel: FlickrViewModel by viewModels()

    private val binding get() = firstFragmentBinding!!
    private var pagingAdapter: FlickrPhotoPagingAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        firstFragmentBinding = FlickrSearchFragmentBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = firstFragmentBinding?.recyclerView ?: return null

        recyclerView.layoutManager = GridLayoutManager(context, 3)

        /* Single Page Data
        flickrViewModel.getFlickrData("dog").observe(viewLifecycleOwner) {
            recyclerView.adapter = FlickrPhotoAdapter(it)
        }*/

        //Paging
        pagingAdapter = FlickrPhotoPagingAdapter(findNavController())

        recyclerView.adapter = pagingAdapter

        lifecycleScope.launch {
            flickrViewModel.getFlickrPagingData(arguments?.getString("SEARCH_TEXT") ?: "")
                .collectLatest { flickrPagingData -> pagingAdapter?.submitData(flickrPagingData) }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pagingAdapter = null
        firstFragmentBinding = null
    }
}