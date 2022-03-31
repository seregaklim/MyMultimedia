package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Music


interface MusicRepository {
    fun getAll(): LiveData<List<Music>>

}