package com.example.professionaldevelopment.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.ImageLoader
import coil.request.LoadRequest
import com.example.professionaldevelopment.R
import com.example.professionaldevelopment.databinding.FragmentDescriptionBinding
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.utils.convertMeaningsToString

class DescriptionFragment(
    private val data: DataModel
) : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        descriptionHeader.text = data.text
        descriptionTextview.text = convertMeaningsToString(data.meanings!!)
        useCoilToLoadPhoto(descriptionImageview, data.meanings[0].imageUrl)
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageUrl: String?) {
        val request = LoadRequest.Builder(requireContext())
            .data("https:$imageUrl")
            .target(
                {},
                { result -> imageView.setImageDrawable(result) },
                { imageView.setImageResource(R.drawable.ic_load_error_vector) }
            )
            .build()
        ImageLoader(requireContext()).execute(request)
    }
}