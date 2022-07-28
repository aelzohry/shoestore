package com.udacity.aelzohry.shoestore.screens.shoedetails

import androidx.lifecycle.*
import com.udacity.aelzohry.shoestore.models.Shoe

class ShoeDetailsViewModel : ViewModel() {

    val shoe = MutableLiveData(Shoe("", "", "", ""))

    private val _saveEvent = MutableLiveData<Boolean>()
    val saveEvent: LiveData<Boolean>
        get() = _saveEvent

    private val _cancelEvent = MutableLiveData<Boolean>()
    val cancelEvent: LiveData<Boolean>
        get() = _cancelEvent

    private val _validationFailedEvent = MutableLiveData<Boolean>()
    val validationFailedEvent: LiveData<Boolean>
        get() = _validationFailedEvent

    fun onSave() {
        // Validate inputs
        val shoe = shoe.value ?: return onValidationFailed()
        if (shoe.name.isNotEmpty() &&
            shoe.company.isNotEmpty() &&
            shoe.size.isNotEmpty() &&
            shoe.company.isNotEmpty()
        ) {
            _saveEvent.value = true
        } else {
            onValidationFailed()
        }
    }

    private fun onValidationFailed() {
        _validationFailedEvent.value = true
    }

    fun onValidationFailedEventCompleted() {
        _validationFailedEvent.value = false
    }

    fun onSaveEventCompleted() {
        _saveEvent.value = false
    }

    fun onCancel() {
        _cancelEvent.value = true
    }

    fun onCancelEventCompleted() {
        _cancelEvent.value = false
    }

}