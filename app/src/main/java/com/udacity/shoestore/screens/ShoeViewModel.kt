package com.udacity.shoestore.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoes: MutableLiveData<List<Shoe>> = MutableLiveData(emptyList())
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val _itemAdded: MutableLiveData<Boolean> = MutableLiveData(false)
    val itemAdded: LiveData<Boolean>
        get() = _itemAdded

    val name = MutableLiveData("")
    val company = MutableLiveData("")
    val size = MutableLiveData("")
    val description = MutableLiveData("")

    fun onSaveClick() {
        addShoe(
            Shoe(
                name.value.orEmpty(),
                size.value?.toDoubleOrNull() ?: 0.0,
                company.value.orEmpty(),
                description.value.orEmpty()
            )
        )
        _itemAdded.value = true
    }

    fun onSaveClickComplete() {
        _itemAdded.value = false
    }

    private fun addShoe(shoe: Shoe) {
        _shoes.value = shoes.value?.plus(shoe)
    }
}