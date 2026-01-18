package net.bibbs.pilltracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.bibbs.pilltracker.databinding.PillItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class PillAdapter(
    private val onPillClick: (Pill) -> Unit,
    private val onTakeClick: (Pill) -> Unit
) : ListAdapter<Pill, PillAdapter.PillViewHolder>(PillDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillViewHolder {
        val binding = PillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PillViewHolder(binding, onPillClick, onTakeClick)
    }

    override fun onBindViewHolder(holder: PillViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PillViewHolder(
        private val binding: PillItemBinding,
        private val onPillClick: (Pill) -> Unit,
        private val onTakeClick: (Pill) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormat = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())

        fun bind(pill: Pill) {
            binding.pillName.text = pill.name
            
            val nextTime = pill.nextTime
            if (nextTime != null) {
                binding.pillNextTime.text = "Next dose: ${dateFormat.format(nextTime)}"
            } else {
                binding.pillNextTime.text = "No doses taken yet"
            }
            
            binding.root.setOnClickListener { onPillClick(pill) }
            binding.buttonTake.setOnClickListener { onTakeClick(pill) }
        }
    }

    class PillDiffCallback : DiffUtil.ItemCallback<Pill>() {
        override fun areItemsTheSame(oldItem: Pill, newItem: Pill): Boolean = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Pill, newItem: Pill): Boolean = oldItem == newItem
    }
}