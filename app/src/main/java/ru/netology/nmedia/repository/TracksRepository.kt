package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Music
import ru.netology.nmedia.dto.Tracks


interface TracksRepository {
    fun getAll(): LiveData<List<Music>>
    fun  playNextByMe (id: Long)
    fun   canselNextByMe (id: Long)
}