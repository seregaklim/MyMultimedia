package ru.netology.nmedia.activity

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import ru.netology.nmedia.adapter.MusicsAdapter
import ru.netology.nmedia.adapter.OnInteractionListener

import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.MusicViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.NonCancellable.start
import ru.netology.nmedia.R
import ru.netology.nmedia.dto.Music


class MainActivity : AppCompatActivity() {

    private val mediaObserver = MediaLifecycleObserver()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


       lifecycle.addObserver(mediaObserver)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MusicViewModel by viewModels()


        val adapter = MusicsAdapter(object : OnInteractionListener {



           override fun onPlay(music: Music) {

              mediaObserver.apply {


                 if (player != null) {

                      player!!.reset()
                      player!!.getCurrentPosition()
                      player!!.setDataSource(music.url)//мое воспроизведение
                      player!!.prepare()
                     // player!!.seekTo(getCurrentPosition. )
                      player!!.start()

                 }
                  player?.getDuration()
                  player?.start()

              }
           }


          override  fun onPause(music: Music) {

              mediaObserver.apply {
                  if (player != null) {
                      if (player!!.isPlaying()) {
                          player!!.pause()
                      } else {
                          player!!.getDuration();
                          player!!.start();
                      }
                  }
              }
          }

        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { musics ->
            adapter.submitList(musics)
        }
        //разделитель постов
        binding.list.addItemDecoration(
            DividerItemDecoration(binding.list.context,
                DividerItemDecoration.VERTICAL)
        )

    }}









