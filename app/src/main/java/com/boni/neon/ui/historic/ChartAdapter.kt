package com.boni.neon.ui.historic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.RecyclerView
import com.boni.neon.R
import com.boni.neon.entities.GraphView
import com.boni.neon.ext.toMonetary
import kotlinx.android.synthetic.main.chart_item_list.view.*

class ChartAdapter constructor(private val graphItems: MutableList<GraphView>):
    RecyclerView.Adapter<ChartAdapter.ViewHolder>() {

    private var maxValue = 0f

    init {
        maxValue = getMaxValueFromItems(graphItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chart_item_list, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = graphItems.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(graphItems[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val avatar = itemView.avatar
        private val line = itemView.line
        private val value = itemView.value

        fun bind(chartView: GraphView) {
            value.text = chartView.value.toMonetary().toString()
            avatar.setName(chartView.name)
            avatar.setImage(chartView.image)

            line.doOnPreDraw {
                val layoutParams = line.layoutParams
                layoutParams.height = itemView.height * getLineSize(chartView.value) / 100
                line.requestLayout()
            }
        }
    }

    private fun getMaxValueFromItems(items: MutableList<GraphView>): Float {
        return items.maxBy { it.value }?.value ?: 0f
    }

    fun getLineSize(currentValue: Float): Int {
        return if (currentValue * 50 / maxValue < 1) {
            1
        } else {
            (currentValue * 50 / maxValue).toInt()
        }
    }
}