package com.kalpv.turtlemintdemo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "issues")
data class Issues(
    val body: String?,
    val comments: Int,
    val comments_url: String,
    val created_at: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val labels: List<Label>,
    val labels_url: String,
    val locked: Boolean,
    val node_id: String,
    val number: Int,
    val repository_url: String,
    val state: String,
    val timeline_url: String,
    val title: String,
    val updated_at: String,
    val url: String,
    val user: User
) : Parcelable