package tn.esprit.nascar.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tn.esprit.nascar.dao.EventDao
import tn.esprit.nascar.models.Events

@Database(entities = [Events::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nascar_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}