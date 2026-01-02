package com.message.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.message.app.data.CountryModel
import com.message.app.databinding.ItemCountryBinding

class CountryAdapter(
    private val primaryColor: Int,
    private val textColor: Int
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    private val items = mutableListOf<CountryModel>()

    fun submitList(list: List<CountryModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryModel, position: Int) {
            binding.tvCountry.text = item.name
            binding.tvCountry.setTextColor(textColor)
            binding.ivFlag.setImageResource(item.flag)

            binding.checkbox.isChecked = item.isSelected
            binding.checkbox.buttonTintList =
                android.content.res.ColorStateList.valueOf(primaryColor)

            binding.checkbox.setOnClickListener {
                for (index in items.indices) {
                    items[index].isSelected = false
                }
                items[position].isSelected=true
                notifyDataSetChanged()
            }

        }
    }
}
