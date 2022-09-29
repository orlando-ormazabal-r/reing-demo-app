package cl.orlandoormazabal.reigndemoapp.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.orlandoormazabal.reigndemoapp.data.model.HitEntity

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHit(hitList: HitEntity)

    @Query("SELECT * FROM hit_table WHERE object_id NOT IN (SELECT object_id FROM hit_id_table)")
    suspend fun getHits(): List<HitEntity>

    @Query("DELETE FROM hit_table")
    suspend fun deletePreviousHits()
}