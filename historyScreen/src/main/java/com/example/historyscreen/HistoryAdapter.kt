package com.example.historyscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.historyscreen.databinding.HistoryRecyclerViewItemBinding
import com.example.model.data.DataModel

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    fun setData(newData: List<DataModel>) {
        this.data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerItemViewHolder {
        val binding = HistoryRecyclerViewItemBinding.inflate(
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

    inner class RecyclerItemViewHolder(private val binding: HistoryRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                headerHistoryTextviewRecyclerItem.text = data.text
                itemView.setOnClickListener { openDescription(data) }
            }
        }

        private fun openDescription(data: DataModel) {
            //здесь надо запрограммировать открытие, чтобы можно было повторно открыть значение без
            //ввода, потом реализую
        }
    }
}