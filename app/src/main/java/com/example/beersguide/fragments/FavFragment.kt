package com.example.beersguide.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.beersguide.MainActivity
import com.example.beersguide.R
import com.example.beersguide.adapter.FavMealAdapter
import com.example.beersguide.databinding.FragmentFavBinding
import com.example.beersguide.databinding.FragmentHomeBinding
import com.example.beersguide.viewModel.HomeViewModel


class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favMealAdapter: FavMealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel= (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentFavBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        observeFavLiveData()

    }

    private fun setRecyclerView() {
        favMealAdapter = FavMealAdapter()
        binding.rvFav.apply {
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=favMealAdapter
        }
    }

    private fun observeFavLiveData() {
        viewModel.observeFavMealsLivedata().observe(requireActivity(), Observer {
            favMealAdapter.differ.submitList(it)
        })
    }

}