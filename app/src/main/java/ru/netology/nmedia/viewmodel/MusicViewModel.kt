package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.MusicRepository
import ru.netology.nmedia.repository.MusicRepositoryInMemoryImpl

class MusicViewModel : ViewModel() {
    // упрощённый вариант
    private val repository: MusicRepository = MusicRepositoryInMemoryImpl()
    val data = repository.getAll()





}