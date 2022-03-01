package com.kalpv.turtlemintdemo.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kalpv.turtlemintdemo.data.model.Comment
import com.kalpv.turtlemintdemo.data.model.Label
import com.kalpv.turtlemintdemo.data.model.User

class Converters {
    @TypeConverter
    fun fromLabelList(value: List<Label>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Label>>() {}.type
        return gson.toJson(value, type)
    }


    @TypeConverter
    fun toLabelList(value: String): List<Label> {
        val gson = Gson()
        val type = object : TypeToken<List<Label>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromCommentList(value: List<Comment>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.toJson(value, type)
    }


    @TypeConverter
    fun toCommentList(value: String): List<Comment> {
        val gson = Gson()
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromUser(value: User): String {
        val gson = Gson()
        return gson.toJson(value, User::class.java)
    }

    @TypeConverter
    fun toUser(value: String): User {
        val gson = Gson()
        return gson.fromJson(value, User::class.java)
    }
}