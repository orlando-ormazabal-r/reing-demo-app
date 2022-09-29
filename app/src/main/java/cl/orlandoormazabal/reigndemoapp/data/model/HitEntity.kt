package cl.orlandoormazabal.reigndemoapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hit_table")
data class HitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "object_id")
    val objectId: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "story_url")
    val storyUrl: String?,
    @ColumnInfo(name = "title")
    val title: String,
)

@Entity(tableName = "hit_id_table")
data class HitIdEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "object_id")
    val objectId: String
)