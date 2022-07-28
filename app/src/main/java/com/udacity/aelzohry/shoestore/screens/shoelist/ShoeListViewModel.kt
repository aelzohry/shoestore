package com.udacity.aelzohry.shoestore.screens.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.udacity.aelzohry.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _addShoeEvent = MutableLiveData<Boolean>()
    val addShoeEvent: LiveData<Boolean>
        get() = _addShoeEvent

    // boolean state to use in view for showing/hiding empty state
    val isShoeListEmpty = Transformations.map(_shoeList) {
        it.isNullOrEmpty()
    }

    fun onAdd() {
        _addShoeEvent.value = true
    }

    fun addShoe(shoe: Shoe) {
        val list = _shoeList.value
        list?.let {
            it.add(shoe)
            _shoeList.value = it
        }
    }

    fun removeShoe(shoe: Shoe) {
        val list = _shoeList.value
        _shoeList.value = list?.filter { it != shoe }?.toMutableList()
    }

    fun addShoeEventCompleted() {
        _addShoeEvent.value = false
    }

}