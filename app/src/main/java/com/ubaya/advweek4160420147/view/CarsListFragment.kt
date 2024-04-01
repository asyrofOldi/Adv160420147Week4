package com.ubaya.advweek4160420147.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4160420147.R
import com.ubaya.advweek4160420147.databinding.FragmentCarsListBinding
import com.ubaya.advweek4160420147.databinding.FragmentStudentListBinding
import com.ubaya.advweek4160420147.viewmodel.CarsViewModel
import com.ubaya.advweek4160420147.viewmodel.ListViewModel

class CarsListFragment : Fragment() {

    private lateinit var carsViewModel: CarsViewModel
    private lateinit var carsListAdapter: CarsListAdapter
    private lateinit var binding: FragmentCarsListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Menggunakan View Binding untuk inflate layout
        binding = FragmentCarsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        carsViewModel = ViewModelProvider(this).get(CarsViewModel::class.java)

        // Initialize adapter dengan list kosong
        carsListAdapter = CarsListAdapter(arrayListOf())

        // Setup RecyclerView
        binding.recView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = carsListAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE

            carsViewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        carsViewModel.refresh()
        observeViewModel()
    }

    private fun observeViewModel() {
        carsViewModel.carsLD.observe(viewLifecycleOwner, Observer { cars ->
            cars?.let {
                binding.recView.visibility = View.VISIBLE
                carsListAdapter.updateCarsList(it) // Sesuaikan dengan metode adapter Anda
            }
        })

        carsViewModel.carsLoadErrorLD.observe(viewLifecycleOwner, Observer { isError ->
            binding.txtError.visibility = if (isError) View.VISIBLE else View.GONE
        })

        carsViewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                binding.progressLoad.visibility = View.VISIBLE
                binding.recView.visibility = View.GONE
                binding.txtError.visibility = View.GONE
            } else {
                binding.progressLoad.visibility = View.GONE
            }
        })
    }



}