package net.bibbs.pilltracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.bibbs.pilltracker.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PillViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PillAdapter(
            onPillClick = { pill ->
                val bundle = bundleOf("pillName" to pill.name)
                findNavController().navigate(R.id.action_FirstFragment_to_HistoryFragment, bundle)
            },
            onTakeClick = { pill ->
                viewModel.takePill(pill.name)
            }
        )
        binding.recyclerviewPills.adapter = adapter

        viewModel.pills.observe(viewLifecycleOwner) { pills ->
            adapter.submitList(pills)
            binding.textviewEmpty.visibility = if (pills.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}