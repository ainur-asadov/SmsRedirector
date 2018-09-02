package ru.ohrana_neva.smsredirector.adapters.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_expand_group.view.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.realm.Group

class AdapterExpandable(private var group: Group? = null) : RecyclerView.Adapter<AdapterExpandable.ThisHolder>() {

    fun updateItems(group: Group) {
        this.group = group
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThisHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_expand_group, parent, false)
        return ThisHolder(v)
    }

    override fun onBindViewHolder(holder: ThisHolder, position: Int) {
        holder.tvPhone.text = group?.phones?.get(position)?.number
    }

    override fun getItemCount(): Int {
        return when(group == null) {
            true -> 0
            else -> group!!.phones!!.size
        }
    }

    class ThisHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvPhone: TextView = view.tv_phone_pass
    }
}