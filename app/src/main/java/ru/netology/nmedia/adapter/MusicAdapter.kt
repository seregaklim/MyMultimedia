package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.netology.nmedia.activity.MediaLifecycleObserver
import ru.netology.nmedia.databinding.CardTracksBinding
import ru.netology.nmedia.dto.Music
import ru.netology.nmedia.dto.Tracks


interface OnInteractionListener {
    //запуск выбранного трека
    fun onPlay(tracks: Tracks) {}
   //запуск следующего трека
    fun onNextPlay(tracks: Tracks ) {}
    //ставим флажок, после проигрования трека назад
    fun onСanselNextByMe(tracks:Tracks)

}

class MusicAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Tracks,MusicViewHolder>(MusicDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MusicViewHolder {
        val binding = CardTracksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val tracks = getItem(position)
        holder.bind(tracks)
    }
}

class MusicViewHolder(
    private val binding: CardTracksBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {



    fun bind (tracks:Tracks) {

        val mediaObserver = MediaLifecycleObserver()

        binding.apply {

                musicTrack.text = tracks.musicTrack.toString()

                //следующий трек +
                if (tracks.playByMe) {

                    binding.play.visibility = View.GONE
                    binding.pause.visibility = View.VISIBLE


                    mediaObserver.apply {

                        player?.apply {

                            val currentPosition = currentPosition // сохраняем перед сбросом
                            reset()

                            // окончание трека
                            setOnCompletionListener {
                                //следующий трек +
                                onInteractionListener.onNextPlay(tracks)
                                onInteractionListener.onСanselNextByMe(tracks)
                                binding.play.visibility = View.VISIBLE
                               binding.pause.visibility = View.GONE

                            }
                            setDataSource(tracks.url) //мое воспроизведение
                            prepare()
                           seekTo(currentPosition)
                            start()
                       } }

               } else {
                    binding.play.visibility = View.VISIBLE
                    binding.pause.visibility = View.GONE
                }


                //проигрывать выбранный трек (старт)
                play.setOnClickListener {

                    onInteractionListener.onPlay(tracks)
      }

                //пауза
                pause.setOnClickListener {

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


                    binding.play.visibility = View.VISIBLE
                    binding.pause.visibility = View.GONE


                }


            }
        }
    }


class MusicDiffCallback : DiffUtil.ItemCallback<Tracks>() {
    override fun areItemsTheSame(oldItem: Tracks, newItem: Tracks): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem:Tracks, newItem: Tracks): Boolean {
        return oldItem == newItem
    }
}






























