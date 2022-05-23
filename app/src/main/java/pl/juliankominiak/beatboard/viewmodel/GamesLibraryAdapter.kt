package pl.juliankominiak.beatboard.viewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import pl.juliankominiak.beatboard.R
import pl.juliankominiak.beatboard.model.GameRecord
import pl.juliankominiak.beatboard.view.AddGameDialogFragment
import pl.juliankominiak.beatboard.view.EditGameDialogFragment

class GamesLibraryAdapter(private val viewModel: GamesLibraryViewModel,
                          private val context: Context?,
                          private val parentFragmentManager: FragmentManager)
    : RecyclerView.Adapter<GamesLibraryAdapter.GamesLibraryHolder>() {

    inner class GamesLibraryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewGameTitle: TextView = view.findViewById(R.id.game_title)
        val textViewBeatTime: TextView = view.findViewById(R.id.beat_time)
        val textViewGameOptions: TextView = view.findViewById(R.id.game_options)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesLibraryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_game, parent, false)
        return GamesLibraryHolder(view)
    }

    override fun onBindViewHolder(holder: GamesLibraryHolder, position: Int) {
        holder.textViewGameTitle.text = viewModel.gameRecords.value?.get(position)?.title

        val beatTime = viewModel.gameRecords.value?.get(position)?.beatTime
        if (beatTime == "") {
            holder.textViewBeatTime.text = "Game not beaten yet"
        } else {
            holder.textViewBeatTime.text =
                "Game beaten in " + beatTime + " hours"
        }

        holder.textViewGameOptions.setOnClickListener {
            val popup = PopupMenu(context, holder.textViewGameOptions)
            popup.inflate(R.menu.game_options_menu)
            popup.setOnMenuItemClickListener { item: MenuItem? ->
                when (item!!.itemId) {
                    R.id.game_delete -> {
                        viewModel.delete(viewModel.gameRecords.value?.get(position)?.id!!)
                    }
                    R.id.game_edit -> {
                        val dialog = EditGameDialogFragment(viewModel, viewModel.gameRecords.value?.get(position)!!)
                        dialog.show(parentFragmentManager, "edit_game")
                    }
                }
                true
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return viewModel.gameRecords.value?.size ?: 0
    }
}