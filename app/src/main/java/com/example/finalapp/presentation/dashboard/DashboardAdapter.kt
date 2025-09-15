package com.example.finalapp.presentation.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapp.R
import com.example.finalapp.data.models.Entity

class DashboardAdapter(
    private val onClick: (Entity) -> Unit
) : ListAdapter<Entity, DashboardAdapter.EntityViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Entity>() {
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }
    }

    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtField: TextView = itemView.findViewById(R.id.txtField)
        private val txtConcept: TextView = itemView.findViewById(R.id.txtConcept)
        private val txtScientist: TextView = itemView.findViewById(R.id.txtScientist)

        fun bind(item: Entity) {
            txtField.text = item.field
            txtConcept.text = item.concept
            txtScientist.text = item.scientist

            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
