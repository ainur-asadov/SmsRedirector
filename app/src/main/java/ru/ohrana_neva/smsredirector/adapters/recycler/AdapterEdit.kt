package ru.ohrana_neva.smsredirector.adapters.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_edit.view.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.mvp.presenters.PresenterEdit
import ru.ohrana_neva.smsredirector.realm.Group

class AdapterEdit(private val presenter: PresenterEdit,
                  var group: Group? = null) : RecyclerView.Adapter<AdapterEdit.ThisHolder>() {

    fun updateItems(group: Group?) {
        this.group = group
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThisHolder {
        return ThisHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_edit, parent, false))
    }

    override fun onBindViewHolder(holder: ThisHolder, position: Int) {
        val phone = group?.phones?.get(position)?.number
        holder.tvPhone.text = phone
        holder.vDelete.setOnClickListener { presenter.deletePhone(phone!!) }
    }

    override fun getItemCount(): Int {
        return when(group) {
            null -> 0
            else -> group?.phones!!.size
        }
    }

    class ThisHolder(view: View) : RecyclerView.ViewHolder(view) {

        val vDelete: View = view.view_delete2
        val tvPhone: TextView = view.tv_phone_pass
    }
}