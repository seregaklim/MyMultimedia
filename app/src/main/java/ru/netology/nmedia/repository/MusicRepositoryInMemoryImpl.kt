package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Music


class MusicRepositoryInMemoryImpl : MusicRepository {
    private var nextId = 1L
    private var musics = listOf(
        Music(
            id= nextId++,
            musicTrack = "aaaaaaaaaaa",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),

        Music(
             id=nextId++,
         musicTrack = "арвавра",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Music(
            id=nextId++,
            musicTrack = "оллддддддддд",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Music(
            id=nextId++,
            musicTrack = "ддддддддддддддддддддддддддд",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Music(
            id=nextId++,
            musicTrack = "ггггггггггггггггггггггггггг",
            url ="https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Music(
            id=nextId++,
            musicTrack = "ддддддддддддддддддд",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Music(
            id=nextId++,
            musicTrack = "",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Music(
            id=nextId++,
            musicTrack = "",
            url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        ),
        Music(
            id=nextId++,
            musicTrack = "",
            url ="https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        )
    )

    private val data = MutableLiveData(musics)

    override fun getAll(): LiveData<List<Music>> = data


}