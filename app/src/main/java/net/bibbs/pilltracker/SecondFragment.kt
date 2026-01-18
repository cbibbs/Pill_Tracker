package net.bibbs.pilltracker

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import net.bibbs.pilltracker.databinding.FragmentSecondBinding
import java.util.Calendar

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PillViewModel by activityViewModels()

    private val selectedTimes = mutableMapOf<Int, Pair<Int, Int>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.radioGroupType.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_interval) {
                binding.layoutPillInterval.visibility = View.VISIBLE
                binding.layoutDailyTimes.visibility = View.GONE
            } else {
                binding.layoutPillInterval.visibility = View.GONE
                binding.layoutDailyTimes.visibility = View.VISIBLE
            }
        }

        setupTimePicker(binding.buttonTime1, 1)
        setupTimePicker(binding.buttonTime2, 2)
        setupTimePicker(binding.buttonTime3, 3)

        binding.buttonSave.setOnClickListener {
            val name = binding.editPillName.text.toString()
            if (name.isBlank()) {
                binding.layoutPillName.error = "Name is required"
                return@setOnClickListener
            }

            val newPill = if (binding.radioInterval.isChecked) {
                val intervalString = binding.editPillInterval.text.toString()
                val interval = intervalString.toIntOrNull()
                if (interval == null || interval <= 0) {
                    binding.layoutPillInterval.error = "Enter a valid interval"
                    return@setOnClickListener
                }
                Pill(name, intervalHours = interval)
            } else {
                if (selectedTimes.isEmpty()) {
                    Toast.makeText(context, "Select at least one time", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Pill(name, scheduledTimes = selectedTimes.values.toList())
            }

            viewModel.addPill(newPill)
            Toast.makeText(context, "Pill added", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun setupTimePicker(button: Button, index: Int) {
        button.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(
                context,
                { _, hour, minute ->
                    selectedTimes[index] = hour to minute
                    button.text = String.format("%02d:%02d", hour, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}