package com.example.professionaldevelopment.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.professionaldevelopment.R
import com.example.professionaldevelopment.databinding.FragmentMainScreenBinding
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.ui.MainViewModel
import com.example.professionaldevelopment.ui.adapters.MainFragmentAdapter
import com.example.professionaldevelopment.ui.base.OnItemClickListener
import com.example.professionaldevelopment.ui.base.RenderView

class MainScreenFragment : Fragment(), RenderView {
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private var adapter: MainFragmentAdapter? = null
    val model:MainViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }

    private val onItemClickListener: OnItemClickListener =
        object : OnItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(context, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model?.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(childFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        mainListRecyclerview.layoutManager =
                            LinearLayoutManager(context)
                        mainListRecyclerview.adapter =
                            MainFragmentAdapter(onItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progressBarHorizontal.visibility = View.VISIBLE
                    progressBarRound.visibility = View.GONE
                    progressBarHorizontal.progress = appState.progress
                } else {
                    progressBarHorizontal.visibility = View.GONE
                    progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) = with(binding) {
        showViewError()
        errorTextview.text = error ?: getString(R.string.undefined_error)
        reloadButton.setOnClickListener {
            model?.getData("hi", true)
        }
    }

    private fun showViewSuccess() = with(binding) {
        successLinearLayout.visibility = View.VISIBLE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.GONE
    }

    private fun showViewLoading() = with(binding) {
        successLinearLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.VISIBLE
        errorLinearLayout.visibility = View.GONE
    }

    private fun showViewError() = with(binding) {
        successLinearLayout.visibility = View.GONE
        loadingFrameLayout.visibility = View.GONE
        errorLinearLayout.visibility = View.VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}