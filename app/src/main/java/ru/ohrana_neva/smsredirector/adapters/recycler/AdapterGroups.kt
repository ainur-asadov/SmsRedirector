package ru.ohrana_neva.smsredirector.adapters.recycler

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_group.view.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.mvp.presenters.PresenterMain
import ru.ohrana_neva.smsredirector.realm.Group
import java.util.*

class AdapterGroups(private val presenter: PresenterMain,
                    var groups: List<Group> = Collections.emptyList()) : RecyclerView.Adapter<AdapterGroups.ThisHolder>() {

    fun updateItems(list: List<Group>) {
        groups = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThisHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return ThisHolder(v)
    }

    override fun onBindViewHolder(holder: ThisHolder, position: Int) {
        holder.tvName.text = groups[position].name
        holder.root.setOnClickListener { presenter.goToExpandable(groups[position].name!!) }
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    class ThisHolder(view: View) : RecyclerView.ViewHolder(view) {

        val root: ConstraintLayout = view.root
        val tvName: TextView = view.tv_group_name
    }
}