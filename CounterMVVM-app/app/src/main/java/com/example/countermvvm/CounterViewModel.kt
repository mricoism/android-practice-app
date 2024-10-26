package com.example.countermvvm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel(
//    private val counterRepository: CounterRepository // Di Project besar menggunakan ini. Tapi karna latihan jadi di gantikan dengan membuat direct object di dalam CounterViewModel
): ViewModel() {
    private val _repository: CounterRepository = CounterRepository() // Direct make object
//    private val _count = mutableStateOf(0)
private val _count = mutableStateOf(_repository.getCounter().count)

    val count: MutableState<Int> = _count

    fun increment() {
//        _count.value++
        _repository.incrementCounter()
        _count.value = _repository.getCounter().count
    }

    fun decrement() {
//        _count.value--
        _repository.decrementCounter()
        _count.value = _repository.getCounter().count
    }
}