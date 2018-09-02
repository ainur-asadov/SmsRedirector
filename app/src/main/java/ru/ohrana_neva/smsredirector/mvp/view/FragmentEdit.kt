package ru.ohrana_neva.smsredirector.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_edit.view.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.adapters.recycler.AdapterEdit
import ru.ohrana_neva.smsredirector.dialogs.DialogAlert
import ru.ohrana_neva.smsredirector.mvp.models.ModelEdit
import ru.ohrana_neva.smsredirector.mvp.presenters.PresenterEdit
import ru.ohrana_neva.smsredirector.realm.Group
import ru.ohrana_neva.smsredirector.utils.FragmentChangeListener

class FragmentEdit : Fragment(), ViewEdit {

    private lateinit var presenter: PresenterEdit
    private lateinit var listener: FragmentChangeListener
    private lateinit var adapter: AdapterEdit
    private lateinit var v: View

    companion object {
        fun newInstance(groupName: String): FragmentEdit {
            val args = Bundle()
            args.putString("113", groupName)
            val fragment = FragmentEdit()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = LayoutInflater.from(context!!).inflate(R.layout.layout_edit, container, false)

        presenter = PresenterEdit(ModelEdit())

        val bundle = arguments
        val name = bundle?.getString("113")
        presenter.groupName = name!!

        listener = activity as MainActivity

        presenter.attachView(this)

        adapter = AdapterEdit(presenter)
        v.recycler.adapter = adapter
        v.recycler.layoutManager = LinearLayoutManager(context)

        v.view_delete.setOnClickListener(delete)
        v.btn_save.setOnClickListener(save)
        v.btn_add.setOnClickListener(add)

        presenter.viewReady()

        return v
    }

    override fun onResume() {
        listener.setTitle(R.string.title_edit)
        super.onResume()
    }

    override fun showList(group: Group?) {
        adapter.updateItems(group)
    }

    override fun showAlert(title: Int, msg: Int) {
        DialogAlert().onCreateDialog(activity!!, title, msg).show()
    }

    override fun setData(name: String, phone: String) {
        presenter.phone = phone
        v.et_name.setText(name)
        v.et_phone.setText(phone)
    }

    override fun reset() {
        v.et_add_phone.text.clear()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private val delete = View.OnClickListener {
        presenter.deleteGroup()
        listener.returnToStartStack()
        showAlert(R.string.title_default, R.string.text_group_removed)
    }

    private val save = View.OnClickListener {
        presenter.changeData(v.et_name.text.toString(), v.et_phone.text.toString())
    }

    private val add = View.OnClickListener {
        presenter.addPhone(v.et_add_phone.text.toString())
    }
}