package com.kalpv.turtlemintdemo.data.db

import androidx.room.*
import com.kalpv.turtlemintdemo.data.model.Comment
import com.kalpv.turtlemintdemo.data.model.Issues
import kotlinx.coroutines.flow.Flow

@Dao
interface IssuesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllIssues(issueList: List<Issues>)

    @Transaction
    @Query("SELECT * FROM issues")
    fun getAllIssues(): List<Issues>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: List<Comment>)

}