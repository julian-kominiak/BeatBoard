package pl.juliankominiak.beatboard.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.juliankominiak.beatboard.model.BeatBoardDAO
import pl.juliankominiak.beatboard.model.BeatBoardDatabase
import pl.juliankominiak.beatboard.model.GameRecord

class GamesLibraryViewModel(application: Application) : ViewModel() {

    private val dao: BeatBoardDAO
    init {
        dao = BeatBoardDatabase.getInstance(application).dao
    }

    fun add(gameRecord: GameRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertGameRecord(gameRecord)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteGameRecord(id, FirebaseAuth.getInstance().currentUser?.uid!!.toString())
        }
    }

    fun edit(gameRecord: GameRecord) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.editGameRecord(gameRecord.id, gameRecord.title, gameRecord.beatTime)
        }
    }

    val gameRecords: LiveData<List<GameRecord>> =
        dao.getAllGameRecords(FirebaseAuth.getInstance().currentUser?.uid!!.toString())
}