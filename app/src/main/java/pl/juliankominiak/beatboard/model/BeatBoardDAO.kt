package pl.juliankominiak.beatboard.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.StringJoiner

@Dao
interface BeatBoardDAO {

    @Insert
    fun insertGameRecord(gameRecord: GameRecord)

    @Query("SELECT * FROM game_records WHERE user = :uid")
    fun getAllGameRecords(uid: String): LiveData<List<GameRecord>>

    @Query("DELETE FROM game_records WHERE id = :id AND user = :uid")
    fun deleteGameRecord(id: Long, uid: String)

    @Query("UPDATE game_records SET title = :game_title, beat_time = :beat_time WHERE id = :id")
    fun editGameRecord(id: Long, game_title: String, beat_time: String)
}