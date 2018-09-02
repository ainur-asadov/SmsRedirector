package ru.ohrana_neva.smsredirector.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_main.view.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.adapters.recycler.AdapterGroups
import ru.ohrana_neva.smsredirector.dialogs.DialogCreateGroup
import ru.ohrana_neva.smsredirector.mvp.models.ModelMain
import ru.ohrana_neva.smsredirector.mvp.presenters.PresenterMain
import ru.ohrana_neva.smsredirector.realm.Group
import ru.ohrana_neva.smsredirector.repository.DataBase
import ru.ohrana_neva.smsredirector.utils.FragmentChangeListener

class FragmentMain : Fragment(), ViewMain {

    private lateinit var presenter: PresenterMain
    private lateinit var adapter: AdapterGroups
    private lateinit var listener: FragmentChangeListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.layout_main, container, false)

        presenter = PresenterMain(ModelMain())
        listener = activity as MainActivity

        adapter = AdapterGroups(presenter)
        v.recycler.adapter = adapter
        v.recycler.layoutManager = LinearLayoutManager(context!!)

        v.btn_add_group.setOnClickListener { listener.showCreateFragment() }

        presenter.attachView(this)
        return  v
    }

    override fun onResume() {
        listener.setTitle(R.string.title_groups)
        presenter.viewReady()
        super.onResume()
    }

    override fun showList(list: List<Group>) {
        adapter.updateItems(list)
    }

    override fun showConfirmAddGroup() {
        DialogCreateGroup().onCreateDialog(activity!!).show()
    }

    override fun showAlert(title: Int, msg: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showExpand(groupName: String) {
        listener.showExpandFragment(groupName)
    }

    override fun onDestroy() {
        DataBase.closeDB()
        presenter.detachView()
        super.onDestroy()
    }
}