package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.TracksRepository
import ru.netology.nmedia.repository.TracksRepositoryInMemoryImpl


class TracksViewModel : ViewModel() {
    // упрощённый вариант
    private val repository: TracksRepository = TracksRepositoryInMemoryImpl()

    val data = repository.getAll()

    //запуск следующего трека
    fun playNextByMe(id: Long) = repository. playNextByMe(id)

    //запуск выбранного трека
    fun playByMe(id: Long) = repository. playByMe(id)

    //востановление флага , после проигрования трека
    fun  canselNextByMe (id: Long)=repository.canselNextByMe(id)
}