package com.app.koin_mvvm_retrofit_flow_room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_post")
data class Post(
    @PrimaryKey
    var id: Int? = null,
    var body: String? = null,
    var title: String? = null,
    var userId: Int? = null
)