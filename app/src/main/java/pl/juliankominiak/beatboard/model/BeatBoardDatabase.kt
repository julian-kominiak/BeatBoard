package pl.juliankominiak.beatboard.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameRecord::class], version = 2, exportSchema = false)
abstract class BeatBoardDatabase : RoomDatabase() {

    abstract val dao: BeatBoardDAO

    companion object {

        @Volatile
        private var INSTANCE: BeatBoardDatabase? = null

        fun getInstance(context: Context): BeatBoardDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BeatBoardDatabase::class.java,
                        "beat_board_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}