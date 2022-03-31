package ru.netology.nmedia.adapter

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.CardMusicBinding
import ru.netology.nmedia.dto.Music
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.coroutines.NonCancellable.start
import org.jetbrains.annotations.NotNull
import ru.netology.nmedia.activity.MediaLifecycleObserver
import ru.netology.nmedia.viewmodel.MusicViewModel
import android.R
import android.media.AudioTrack


interface OnInteractionListener {
    fun onPlay(music: Music) {}
    fun onPause(music: Music) {}
}

class MusicsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Music, PostViewHolder>(MusicDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val music = getItem(position)
        holder.bind(music)
    }
}

class PostViewHolder(
    private val binding: CardMusicBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {



    fun bind(music: Music) {


        binding.apply {
            musicTrack.text = music.musicTrack


            binding.play.visibility = View.VISIBLE
            binding.pause.visibility = View.GONE

                //проигрывать
            play.setOnClickListener {
                binding.play.visibility = View.GONE
                binding.pause.visibility = View.VISIBLE

                onInteractionListener.onPlay(music)

                }


                        //пауза
            pause.setOnClickListener {

                onInteractionListener.onPlay(music)

                binding.play.visibility = View.VISIBLE
                binding.pause.visibility = View.GONE


            }
        }
    }
}







class MusicDiffCallback : DiffUtil.ItemCallback<Music>() {
    override fun areItemsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Music, newItem: Music): Boolean {
        return oldItem == newItem
    }
}
