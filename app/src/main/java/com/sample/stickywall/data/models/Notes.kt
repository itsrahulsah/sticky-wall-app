package com.sample.stickywall.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id") var id:Int = 0,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "note") val note:String
)
