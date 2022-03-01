package com.kalpv.turtlemintdemo.ui.comments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.transition.MaterialContainerTransform
import com.kalpv.turtlemintdemo.R
import com.kalpv.turtlemintdemo.data.model.Issues
import com.kalpv.turtlemintdemo.databinding.FragmentCommentsBinding
import com.kalpv.turtlemintdemo.ui.MainViewModel
import com.kalpv.turtlemintdemo.util.*
import timber.log.Timber


class CommentsFragment : Fragment(R.layout.fragment_comments) {

    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    private val commentsAdapter:CommentRecyclerAdapter by lazy(LazyThreadSafetyMode.NONE) { CommentRecyclerAdapter() }
    private val viewModel: MainViewModel by activityViewModels()
    private val args: CommentsFragmentArgs by navArgs()
    private val issue: Issues by lazy(LazyThreadSafetyMode.NONE) { args.issueData }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = resources.getInteger(R.integer.motion_duration_long).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(com.google.android.material.R.attr.colorSurface))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentsBinding.inflate(layoutInflater, container, false)
        Timber.d("$issue")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.fetchComments(issue.comments_url,issue.id)
        viewModel.commentList.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    binding.loader.makeVisible()
                    Timber.d("Show Loader")
                }
                is DataState.Success -> {
                    binding.loader.makeGone()
                    val commentList = dataState.data
                    commentsAdapter.submitList(commentList)
                    if (commentList.isEmpty())
                        binding.tvNoComments.makeVisible()
                    else
                        binding.tvNoComments.makeGone()
                }
                is DataState.Error -> {
                    binding.loader.makeGone()
                    showToast("${dataState.exception.message}")
                    Timber.e("${dataState.exception.message}")
                }
            }

        }
    }

    private fun setupViews() {
        binding.apply {
            rvComments.adapter = commentsAdapter
            tvTitle.text = issue.title
            ivAvatar.load(issue.user.avatar_url) {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            tvUsername.text = issue.user.login
            tvUpdatedOn.text = getString(R.string.label_issue_update,issue.updated_at.toFormattedDateString())
        }
    }

}