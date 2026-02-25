package com.githbuz.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.githbuz.domain.model.User
import com.githbuz.presentation.databinding.FragmentFavoriteBinding
import com.githbuz.presentation.search.UserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeUiState()
        viewModel.getFavoriteUsers()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(
            onItemClick = { user ->
                // TODO: navigate to detail screen
            },
            onFavoriteClick = { user, isFavorite ->
                if (isFavorite) {
                    viewModel.removeFavoriteUser(user)
                } else {
                    viewModel.addFavoriteUser(user)
                }
            }
        )
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userAdapter
        }
    }

    private fun observeUiState() {
        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                handleUiState(state)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleUiState(state: FavoriteUiState) {
        when (state) {
            is FavoriteUiState.Loading -> {
                binding.progressBar.isVisible = true
                binding.tvEmpty.isVisible = false
                binding.rvUsers.isVisible = false
            }
            is FavoriteUiState.Success -> {
                binding.progressBar.isVisible = false
                binding.tvEmpty.isVisible = false
                binding.rvUsers.isVisible = true
                userAdapter.submitList(state.users)
            }
            is FavoriteUiState.Empty -> {
                binding.progressBar.isVisible = false
                binding.tvEmpty.isVisible = true
                binding.rvUsers.isVisible = false
            }
            is FavoriteUiState.Error -> {
                binding.progressBar.isVisible = false
                binding.tvEmpty.isVisible = true
                binding.tvEmpty.text = state.message
                binding.rvUsers.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
