package com.gamefort.ui.gamelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gamefort.R
import com.gamefort.databinding.FragmentGameListBinding
import com.gamefort.ui.adapter.GameListItemAdapter
import com.gamefort.ui.util.disableUserInteraction
import com.gamefort.ui.util.reEnableUserInteraction
import com.gamefort.ui.util.showSnackbar

class GameListFragment : Fragment() {

    private lateinit var binding: FragmentGameListBinding

    private val viewModel: GameListViewModel by lazy {
        ViewModelProvider(this).get(GameListViewModel::class.java)
    }

    private var gameAdapter: GameListItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_list, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        gameAdapter = GameListItemAdapter { gameListItem, _ ->
            val directions = GameListFragmentDirections
                .actionGameListFragmentToGameDetailFragment(gameListItem.id)
            findNavController().navigate(directions)
        }
        binding.gameRecyclerView.adapter = gameAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservations()
    }

    private fun setupViewModelObservations() {
        viewModel.successMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, true)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, false)
        }

        viewModel.isContentLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.shimmerLayout.shimmerFrameLayout.showShimmer(isLoading)

            if (isLoading) {
                disableUserInteraction()
            } else {
                reEnableUserInteraction()
            }
        }

        viewModel.games.observe(viewLifecycleOwner) {
            it?.apply {
                gameAdapter?.dataList = it
                binding.gameRecyclerView.smoothScrollToPosition(0)
            }
        }
    }
}