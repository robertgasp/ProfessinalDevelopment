package com.example.professionaldevelopment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.professionaldevelopment.databinding.MainFragmentRecyclerItemBinding
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.ui.base.OnItemClickListener

class MainFragmentAdapter(
    private var onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<MainFragmentAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(dataFromMainFragment: List<DataModel>) {
        this.data = dataFromMainFragment
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentAdapter.RecyclerItemViewHolder {
        val binding = MainFragmentRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: MainFragmentRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                headerTextviewRecyclerItem.text = data.text
                descriptionTextviewRecyclerItem.text = data.meanings[0].translation.translation
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(itemData: DataModel) {
        onItemClickListener.onItemClick(itemData)
    }
}