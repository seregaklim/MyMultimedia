package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.adapter.OnInteractionListener

import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.TracksViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import ru.netology.nmedia.adapter.MusicAdapter
import ru.netology.nmedia.dto.Music
import ru.netology.nmedia.dto.Tracks


class MainActivity : AppCompatActivity() {
    private val mediaObserver = MediaLifecycleObserver()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        lifecycle.addObserver(mediaObserver)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: TracksViewModel by viewModels()

        val adapter = MusicAdapter(object : OnInteractionListener {


            override fun onPlay(tracks: Tracks ) {

                if (tracks.playByMe == true) {

                    //запуск следующего трека
                    viewModel.playNextByMe(tracks.id)

                    viewModel.canselNextByMe(tracks.id)


                } else { //запуск следующего трека
                    viewModel.playNextByMe(tracks.id)
                }

            }

        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { musics ->

            musics.forEach { music ->

                music.tracks.let { tracks ->

                    adapter.submitList(tracks)
                }


                //разделитель постов
                binding.list.addItemDecoration(
                    DividerItemDecoration(
                        binding.list.context,
                        DividerItemDecoration.VERTICAL
                    )
                )

                viewModel.data.observe(this) { musics ->

                    musics.forEach { music ->

                        binding.apply {

                            title.text = music.title
                            title.text = music.title
                            artist.text = music.artist
                            published.text = music.published
                            genre.text = music.genre

                        }
                    }
                }
            }
        }
    }
}







