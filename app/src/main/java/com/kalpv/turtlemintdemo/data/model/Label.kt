package com.kalpv.turtlemintdemo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Label(
    val color: String,
    val default: Boolean,
    val description: String,
    val id: Int,
    val name: String,
    val node_id: String,
    val url: String
):Parcelable