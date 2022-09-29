package cl.orlandoormazabal.reigndemoapp.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import cl.orlandoormazabal.reigndemoapp.data.model.HitIdEntity

@Dao
interface HitIdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hitId: HitIdEntity)
}