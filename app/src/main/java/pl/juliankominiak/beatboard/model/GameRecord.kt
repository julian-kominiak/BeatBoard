package pl.juliankominiak.beatboard.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_records")
data class GameRecord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "user") var user: String,
    @ColumnInfo(name = "beat_time") var beatTime: String

)