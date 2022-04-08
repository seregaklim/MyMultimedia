package ru.netology.nmedia.dto



data class Music (
    val id: Long,
    val title:String,
    val subtitle: String,
    val artist: String,
    val published: String,
    val genre: String,
    val tracks:List<Tracks> = listOf (Tracks(55,"","",false)))

data class Tracks(
    val id: Long,
    val musicTrack: String,
    val url: String,
    val playByMe :Boolean
    )

