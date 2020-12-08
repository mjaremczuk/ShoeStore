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

    private val _cancelled: MutableLiveData<Boolean> = MutableLiveData(false)
    val cancelled: LiveData<Boolean>
        get() = _cancelled

    private val _invalidForm: MutableLiveData<Boolean> = MutableLiveData(false)
    val invalidForm: LiveData<Boolean>
        get() = _invalidForm

    val name = MutableLiveData("")
    val company = MutableLiveData("")
    val size = MutableLiveData("")
    val description = MutableLiveData("")

    fun onSaveClick() {
        val formFilled = listOf(
                name.value.orEmpty(),
                size.value.orEmpty(),
                company.value.orEmpty(),
                description.value.orEmpty())
                .none { it.isEmpty() }

        if (formFilled) {
            addShoe(
                    Shoe(
                            name.value.orEmpty(),
                            size.value?.toDoubleOrNull() ?: 0.0,
                            company.value.orEmpty(),
                            description.value.orEmpty()
                    )
            )
            clearData()
            _itemAdded.value = true
        } else {
            _invalidForm.value = true
        }
    }

    fun onCancelClick() {
        clearData()
        _cancelled.value = true
    }

    fun onSaveClickComplete() {
        _itemAdded.value = false
        _invalidForm.value = false
    }

    fun onCancelClickComplete() {
        _cancelled.value = false
        _invalidForm.value = false
    }

    private fun addShoe(shoe: Shoe) {
        _shoes.value = shoes.value?.plus(shoe)
    }

    private fun clearData() {
        name.value = ""
        company.value = ""
        size.value = ""
        description.value = ""
    }
}