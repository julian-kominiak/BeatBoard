package pl.juliankominiak.beatboard.view

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import pl.juliankominiak.beatboard.R
import pl.juliankominiak.beatboard.model.GameRecord
import pl.juliankominiak.beatboard.viewmodel.GamesLibraryViewModel

class AddGameDialogFragment(private val viewModel: GamesLibraryViewModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_add_game, null)
        val titleInput: EditText = dialogView.findViewById(R.id.title_input)
        val beatTimeInput: EditText = dialogView.findViewById(R.id.beat_time_input)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    if (titleInput.text.isNotEmpty()) {
                        viewModel.add(GameRecord(0,
                            titleInput.text.toString(),
                            FirebaseAuth.getInstance().currentUser?.uid!!,
                            beatTimeInput.text.toString())
                        )
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}