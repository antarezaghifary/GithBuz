package com.azer.presentation.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.azer.core.base.BaseFragment
import com.azer.core.extension.gone
import com.azer.core.extension.visible
import com.azer.domain.model.User
import com.azer.presentation.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {
    private val viewModel: DetailViewModel by viewModels()

    override fun setupUI() {
        val username = arguments?.getString("username")
        if (username != null) {
            viewModel.getUserDetail(username)
        } else {
            Toast.makeText(requireContext(), "Username not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is DetailUiState.Loading -> {
                            binding.progressBar.visible()
                        }
                        is DetailUiState.Success -> {
                            binding.progressBar.gone()
                            displayUser(state.user)
                        }
                        is DetailUiState.Error -> {
                            binding.progressBar.gone()
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }


    private fun displayUser(user: User) {
        binding.apply {
            ivAvatar.load(user.avatarUrl) {
                crossfade(true)
            }
            tvName.text = user.name ?: user.login
            tvUsername.text = "@${user.login}"
            tvBio.text = user.bio ?: "No bio available"
            tvFollowersCount.text = user.followers?.toString() ?: "0"
            tvFollowingCount.text = user.following?.toString() ?: "0"
            tvReposCount.text = user.publicRepos?.toString() ?: "0"
        }
    }
}
