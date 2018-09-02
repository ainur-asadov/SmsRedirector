package ru.ohrana_neva.smsredirector.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_expand_group.view.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.adapters.recycler.AdapterExpandable
import ru.ohrana_neva.smsredirector.repository.DataBase
import ru.ohrana_neva.smsredirector.utils.FragmentChangeListener

class FragmentExpandable : Fragment(), ViewExpand {

    private lateinit var listener: FragmentChangeListener
    private lateinit var groupName: String
    private lateinit var v: View
    private lateinit var adapter: AdapterExpandable

    companion object {
        fun newInstance(groupName: String): FragmentExpandable {
            val args = Bundle()
            args.putString("113", groupName)
            val fragment = FragmentExpandable()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.layout_expand_group, container, false)

        listener = activity as MainActivity

        val bundle = arguments
        groupName = bundle?.getString("113")!!

        adapter = AdapterExpandable()
        v.recycler.adapter = adapter
        v.recycler.layoutManager = LinearLayoutManager(context!!)

        v.btn_edit.setOnClickListener { listener.showEditFragment(groupName) }

        return v
    }

    override fun onResume() {
        listener.setTitle(R.string.title_group)
        val group = DataBase.readGroupByName(groupName)
        v.tv_name.text = group?.name
        v.tv_phone.text = group?.phone
        adapter.updateItems(group!!)

        super.onResume()
    }

    override fun showAlert(title: Int, msg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}