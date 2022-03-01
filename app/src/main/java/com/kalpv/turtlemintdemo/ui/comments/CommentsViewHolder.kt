package com.kalpv.turtlemintdemo.ui.comments

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.kalpv.turtlemintdemo.R
import com.kalpv.turtlemintdemo.data.model.Comment
import com.kalpv.turtlemintdemo.databinding.RowCommentsBinding
import com.kalpv.turtlemintdemo.util.toFormattedDateString

class CommentsViewHolder constructor(private val binding: RowCommentsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(comment: Comment) = with(binding) {
        tvUsername.text = comment.user.login
        ivAvatar.load(comment.user.avatar_url){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        updateViews(comment)
    }

    fun updateViews(comment: Comment){
        binding.tvCommentText.text = comment.body
        binding.tvUpdatedOn.apply {
         text = context.getString(R.string.label_issue_update,comment.updated_at.toFormattedDateString())
        }
    }
}