package com.ifs21006.duplikataplikasi

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21006.duplikataplikasi.databinding.FragmentCariBinding

class CariFragment : Fragment() {
    private lateinit var binding: FragmentCariBinding
    private val dataMusic = ArrayList<Music>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCariBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMusic.setHasFixedSize(false)
        dataMusic.addAll(getListMusic())
        showRecyclerList()

        binding.apply {
            svFragmentCari.setupWithSearchBar(sbFragmentCari)
            svFragmentCari.editText.setOnEditorActionListener { textView, actionId, event ->
                sbFragmentCari.setText(svFragmentCari.text)
                svFragmentCari.hide()
                val message =
                    "Kamu mencari dengan keywords:\n${svFragmentCari.text}"
                Toast.makeText(
                    requireContext(),
                    message,
                    Toast.LENGTH_LONG
                ).show()
                false
            }
        }
    }

    @SuppressLint("Recycle")
    fun getListMusic(): ArrayList<Music> {
        val dataName = resources.getStringArray(R.array.music_name)
        val dataIcon = resources.obtainTypedArray(R.array.family_music_photo)

        val listMusic = ArrayList<Music>()

        for (i in dataName.indices) {
            val music = Music(dataIcon.getResourceId(i, -1), dataName[i])
            listMusic.add(music)
        }
        dataIcon.recycle()
        return listMusic
    }

    private fun showRecyclerList() {
        val layoutManager = if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
        ) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        binding.rvMusic.layoutManager = layoutManager

        val listMusicAdapter = ListMusicAdapter(dataMusic)
        binding.rvMusic.adapter = listMusicAdapter
    }
}
