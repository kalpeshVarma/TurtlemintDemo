package com.kalpv.turtlemintdemo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "comments")
data class Comment(
    val body: String,
    val created_at: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val updated_at: String,
    val url: String,
    val user: User,
    var issueId: Int? = null
) : Parcelable {

}