package com.kalpv.turtlemintdemo.ui.issues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.kalpv.turtlemintdemo.R
import com.kalpv.turtlemintdemo.data.model.Issues
import com.kalpv.turtlemintdemo.databinding.FragmentHomeBinding
import com.kalpv.turtlemintdemo.ui.MainViewModel
import com.kalpv.turtlemintdemo.util.*
import timber.log.Timber

class IssuesFragment : Fragment(R.layout.fragment_home), IssuesInteraction {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var issuesAdapter: IssuesRecyclerAdapter? = null
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = resources.getInteger(R.integer.motion_duration_long).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        setupViews()
        setupObservers()
    }


    private fun setupViews() {
        issuesAdapter = IssuesRecyclerAdapter(this)
        binding.rvIssues.apply {
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = issuesAdapter
        }
    }

    private fun setupObservers() {
        viewModel.fetchIssues()
        viewModel.issueList.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    binding.loader.makeVisible()
                    Timber.d("Show Loader")
                }
                is DataState.Success -> {
                    binding.loader.makeGone()
                    val issueList = dataState.data
                    issuesAdapter?.submitList(issueList)
                }
                is DataState.Error -> {
                    binding.loader.makeGone()
                    showToast("${dataState.exception.message}")
                    Timber.e("${dataState.exception.message}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onIssueClicked(view: View, issue: Issues) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = resources.getInteger(R.integer.motion_duration_long).toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = resources.getInteger(R.integer.motion_duration_long).toLong()
        }

        val transitionName = getString(R.string.transition_name_issue_comment)
        val extras = FragmentNavigatorExtras(view to transitionName)
        val directions = IssuesFragmentDirections.actionHomeFragmentToCommentsFragment(issue)
        findNavController().navigate(directions, extras)

    }

}