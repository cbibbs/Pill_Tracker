package net.bibbs.pilltracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Date

class PillViewModel : ViewModel() {
    private val _pills = MutableLiveData<List<Pill>>(emptyList())
    val pills: LiveData<List<Pill>> = _pills

    fun addPill(pill: Pill) {
        val currentList = _pills.value ?: emptyList()
        _pills.value = currentList + pill
    }

    fun takePill(pillName: String) {
        val currentList = _pills.value ?: return
        val updatedList = currentList.map {
            if (it.name == pillName) {
                it.copy(history = it.history + Date())
            } else {
                it
            }
        }
        _pills.value = updatedList
    }
}