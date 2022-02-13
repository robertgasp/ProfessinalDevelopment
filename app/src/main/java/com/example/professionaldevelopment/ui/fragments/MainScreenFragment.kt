package com.example.professionaldevelopment.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.professionaldevelopment.R
import com.example.professionaldevelopment.databinding.FragmentMainScreenBinding
import com.example.model.data.AppState
import com.example.model.data.DataModel
import com.example.professionaldevelopment.ui.viewModel.MainViewModel
import com.example.professionaldevelopment.ui.adapters.MainFragmentAdapter
import com.example.utils.alertDialog.AlertDialogFragment
import com.example.professionaldevelopment.ui.base.OnItemClickListener
import com.example.professionaldevelopment.ui.base.RenderView
import com.example.utils.network.isOnline
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(), RenderView {


    lateinit var model: MainViewModel
    var isNetworkAvailable: Boolean = false


    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private var adapter: MainFragmentAdapter? = null


    private val onItemClickListener: OnItemClickListener =
        object : OnItemClickListener {
            override fun onItemClick(data: DataModel) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.container,DescriptionFragment(data))
                    .addToBackStack("2")
                    .commit()

                Toast.makeText(context, data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(requireContext())
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModel()
        model = viewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.subscribe().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    isNetworkAvailable = isOnline(requireContext())
                    if (isNetworkAvailable) {
                        model?.getData(searchWord, true)
                    } else {
                        showNoInternetConnectionDialog()
                    }
                }
            })
            searchDialogFragment.show(childFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    private fun showNoInternetConnectionDialog() {
        showAlert(
            "No available Internet connection",
            "Please check your Internet connection. This app doesn't work without Internet"
        )
    }

    private fun showAlert(title: String, message: String) {
        AlertDialogFragment.newInstance(title, message).show(
            childFragmentManager,
            DIALOG_FRAGMENT_TAG
        )
    }

    private fun isDialogNull(): Boolean {
        return childFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
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
                    progressBarHorizontal.progress = appState.progress!!
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
        loadingFrameLayout.visibility  = View.GONE
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
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }

}