package com.gamefort.ui.gamedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.gamefort.R
import com.gamefort.databinding.FragmentGameDetailsBinding

class GameDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailsBinding

    private val arguments: GameDetailsFragmentArgs by navArgs()

    private val viewModel: GameDetailsViewModel by lazy {
        ViewModelProvider(this).get(GameDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_details,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = arguments.gameIdArgument

        // TODO fetch game by id
//        viewModel.setRecipe(recipe)
    }
}