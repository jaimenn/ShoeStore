package com.udacity.shoestore.view.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.models.Shoe
import timber.log.Timber

enum class SaveState {
    SAVE,
    NOOP
}

class ShoesViewModel :  ViewModel() {
    private var _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes : LiveData<MutableList<Shoe>>
        get() = _shoes

    private var _saveState = MutableLiveData<SaveState>()
    val saveState : LiveData<SaveState>
        get() = _saveState

    init {
        Timber.i("in init")
        _shoes.value = mutableListOf()
        addShoe("Shoe10", 8.0, "Company10", "desc10")
        _saveState.value = SaveState.NOOP
    }

    fun addShoe(name: String, size: Double, company: String, description: String) {
        Timber.i("Adding shoe")
        _shoes.value?.add(Shoe(name, size, company, description))
        Timber.i(_shoes.value?.joinToString())
    }

    fun onEventSave(name: String,
                    size: String,
                    company: String,
                    description: String) {
        var sizeDouble = 0.0
        try {
            sizeDouble = size.toDouble()
        } catch (e: NumberFormatException) {
            Timber.i("Invalid size entered")
        }
        addShoe(name, sizeDouble, company, description)
        _saveState.value = SaveState.SAVE
    }

    fun onEventSaveComplete() {
        _saveState.value = SaveState.NOOP
    }



}