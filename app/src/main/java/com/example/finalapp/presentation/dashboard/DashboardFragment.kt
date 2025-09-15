package com.example.finalapp.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.R
import com.example.finalapp.presentation.dashboard.DashboardAdapter
import com.example.finalapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val vm: DashboardViewModel by viewModels()

    private lateinit var rv: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var adapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // views
        rv = view.findViewById(R.id.rvEntities)
        progress = view.findViewById(R.id.progress)

        // adapter + click -> details
        adapter = DashboardAdapter { entity ->
            findNavController().navigate(
                R.id.action_dashboard_to_details,
                bundleOf("entity" to entity) // Entity implements @Parcelize
            )
        }
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        // get keypass from args
        val keypass = requireArguments().getString("keypass")
        if (keypass.isNullOrBlank()) {
            Toast.makeText(requireContext(), "Missing keypass", Toast.LENGTH_SHORT).show()
            return
        }

        // trigger load + observe state
        vm.load(keypass)

        vm.state.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Loading -> {
                    progress.isVisible = true
                }
                is Resource.Success -> {
                    progress.isVisible = false
                    adapter.submitList(res.data?.entities ?: emptyList())
                }
                is Resource.Error -> {
                    progress.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        res.message ?: "Unknown error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
