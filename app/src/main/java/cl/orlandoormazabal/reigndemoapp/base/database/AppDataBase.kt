package cl.orlandoormazabal.reigndemoapp.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.orlandoormazabal.reigndemoapp.data.model.HitEntity
import cl.orlandoormazabal.reigndemoapp.data.model.HitIdEntity
import cl.orlandoormazabal.reigndemoapp.domain.HitDao
import cl.orlandoormazabal.reigndemoapp.domain.HitIdDao

@Database(entities = [HitEntity::class, HitIdEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun hitDao(): HitDao
    abstract fun hitIdDao(): HitIdDao
}