package com.azer.presentation.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.azer.core.base.BaseFragment
import com.azer.core.extension.gone
import com.azer.core.extension.visible
import com.azer.presentation.R
import com.azer.presentation.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun setupUI() {
        userAdapter = UserAdapter { user ->
            viewModel.onUserClicked(user)
        }

        binding.rvUsers.adapter = userAdapter

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        val query = binding.etSearch.text.toString()
        viewModel.searchUsers(query)
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect { state ->
                        handleState(state)
                    }
                }
                launch {
                    viewModel.event.collect { event ->
                        handleEvent(event)
                    }
                }
            }
        }
    }

    private fun handleState(state: SearchUiState) {
        when (state) {
            is SearchUiState.Idle -> {
                binding.progressBar.gone()
                binding.tvEmpty.gone()
            }
            is SearchUiState.Loading -> {
                binding.progressBar.visible()
                binding.tvEmpty.gone()
            }
            is SearchUiState.Success -> {
                binding.progressBar.gone()
                userAdapter.submitList(state.users)
                if (state.users.isEmpty()) {
                    binding.tvEmpty.visible()
                } else {
                    binding.tvEmpty.gone()
                }
            }
            is SearchUiState.Error -> {
                binding.progressBar.gone()
                Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.NavigateToDetail -> {
                val bundle = Bundle().apply {
                    putString("username", event.username)
                }
                findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
            }
        }
    }
}

