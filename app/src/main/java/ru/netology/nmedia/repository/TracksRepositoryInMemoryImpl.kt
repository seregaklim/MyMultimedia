package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Music
import ru.netology.nmedia.dto.Tracks


class TracksRepositoryInMemoryImpl : TracksRepository {
    private var nextId = 0L
    private var musics = listOf (
        Music(
            id =1,
            title="SoundHelix Songs",
            subtitle = "www.soundhelix.com",
            artist = "T. Schürger",
            published ="2009, 2010, 2011, 2013",
            genre=  "electronic",
            tracks =listOf (

                Tracks(
                    id= nextId++,
                    musicTrack = "SoundHelix Песня 1",
                    url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
                    playByMe = false
                ),

                Tracks(
                    id=nextId++,
                    musicTrack = "SoundHelix Песня 2",
                    url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
                    playByMe = false
                ),
                Tracks(
                    id=nextId++,
                    musicTrack = "SoundHelix Песня 3",
                    url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
                    playByMe = false
                ),
                Tracks(
                    id=nextId++,
                    musicTrack = "SoundHelix Песня 4",
                    url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3",
                    playByMe = false
                ),
                Tracks(
                    id=nextId++,
                    musicTrack = "SoundHelix Песня 5",
                    url ="https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3",
                    playByMe = false
                ),
                Tracks(
                    id=nextId++,
                    musicTrack = "SoundHelix Песня 6",
                    url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3",
                    playByMe = false
                ),
                Tracks(
                    id=nextId++,
                    musicTrack = "SoundHelix Песня 7",
                    url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3",
                    playByMe = false

                ),
                Tracks(
                    id=nextId++,
                    musicTrack = "SoundHelix Песня 8",
                    url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3" ,
                    playByMe = false
                )
            )
        )
    )  //.reversed()   //наоборот


    private val data = MutableLiveData(musics)

    override fun getAll(): LiveData<List<Music>> = data

    //запуск следующего трека
    override fun   playNextByMe (id: Long) {

        musics.forEach { music ->
            music.tracks.let { tracks ->

                tracks.map {

                    if (it.id <= id) if (it.id +1 != id) it else it.copy(playByMe = !it.playByMe)
                    else if (id * it.id - id != id) it else it.copy(playByMe = !it.playByMe)
                }
            }
        }
        data.value = musics
    }
    //возврат в прежнее положение
    override fun   canselNextByMe (id: Long) {
        musics.forEach { music ->
            music.tracks.let { tracks ->

                tracks.map {
                    if (it.id  != id) it else it.copy(playByMe = !it.playByMe)
                }
            }
        }
        data.value = musics
    }

}






