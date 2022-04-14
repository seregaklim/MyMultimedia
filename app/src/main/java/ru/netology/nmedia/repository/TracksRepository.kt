package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.dto.Music
import ru.netology.nmedia.dto.Tracks


interface TracksRepository {
    fun getAll(): LiveData<List<Music>>
  //трек по кругу
    fun  playNextByMe (id: Long)
   //запуск выбранного трека
    fun  playByMe (id: Long)
   //после проигрования трека, ставим флаг на место
    fun   canselNextByMe (id: Long)
}