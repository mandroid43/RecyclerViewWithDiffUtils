package com.manapps.mandroid.recyclerviewwithdiffutils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manapps.mandroid.recyclerviewwithdiffutils.databinding.ItemLayoutBinding

class RecyclerViewItemAdapter(
    val context: Context,
    val itemClickListener: RecyclerViewItemClickListenerInterface
) :
    ListAdapter<DataModel, RecyclerViewItemAdapter.DataViewHolder>(RecyclerViewItemDiffCallBack()) {

    private class RecyclerViewItemDiffCallBack :
        DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class DataViewHolder(var holderBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(holderBinding.root) {
        fun bind(dataModel: DataModel) {
            holderBinding.itemLabel.text = dataModel.title

        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position))

        // 2 ways of onClickListener in adapterclass

        //(1)by the help of interface which sends action in Activity and than we handle rest things there
//        holder.itemView.setOnClickListener {
//            onItemClick(dataList[position])
//        }

        //(2)here we can handle logics here in adapter class
        holder.itemView.setOnClickListener {
            val dataModel = getItem(position)
            Toast.makeText(
                context,
               // dataList.get(position).title.plus(": clcked!!"),
               dataModel.title.plus(": clcked!!"),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun onItemClick(position: Int) {
        // this can be customized with required parameters
        //itemClickListener.recyclerViewItemClickListener(position)

    }


}