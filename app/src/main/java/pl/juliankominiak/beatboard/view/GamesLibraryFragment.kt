package pl.juliankominiak.beatboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.juliankominiak.beatboard.R
import pl.juliankominiak.beatboard.databinding.FragmentGamesLibraryBinding
import pl.juliankominiak.beatboard.model.GameRecord
import pl.juliankominiak.beatboard.viewmodel.GamesLibraryAdapter
import pl.juliankominiak.beatboard.viewmodel.GamesLibraryViewModel

class GamesLibraryFragment : Fragment() {

    private lateinit var viewModel : GamesLibraryViewModel
    private lateinit var binding: FragmentGamesLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_games_library, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = GamesLibraryViewModel((requireNotNull(this.activity).application))
        val gamesLibraryAdapter = GamesLibraryAdapter(viewModel, this.context, parentFragmentManager)

        viewModel.gameRecords.observe(viewLifecycleOwner) {
            gamesLibraryAdapter.notifyDataSetChanged()
        }

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.games_recyclerView).let {
            it.adapter = gamesLibraryAdapter
            it.layoutManager = layoutManager
        }

        view.findViewById<Button>(R.id.button_add_game).apply {
            setOnClickListener {
                val dialog = AddGameDialogFragment(viewModel)
                dialog.show(parentFragmentManager, "add_game")
            }
        }

//        viewModel.add(GameRecord(0,"Kapitan Pazur", "Julian", "13"))
//        viewModel.add(GameRecord(0,"Star Wars", "Szymon", "24"))
    }



}