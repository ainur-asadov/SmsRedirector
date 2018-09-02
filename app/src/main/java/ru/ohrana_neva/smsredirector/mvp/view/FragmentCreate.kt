package ru.ohrana_neva.smsredirector.mvp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_create_group.*
import kotlinx.android.synthetic.main.layout_create_group.view.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.dialogs.DialogAlert
import ru.ohrana_neva.smsredirector.mvp.models.ModelCreate
import ru.ohrana_neva.smsredirector.mvp.presenters.PresenterCreate
import ru.ohrana_neva.smsredirector.utils.FragmentChangeListener

class FragmentCreate : Fragment(), ViewCreate {

    private lateinit var presenter: PresenterCreate
    private lateinit var listener: FragmentChangeListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.layout_create_group, container, false)

        presenter = PresenterCreate(ModelCreate())
        listener = activity as MainActivity

        v.btn_add.setOnClickListener(add)
        v.btn_save.setOnClickListener(save)

        presenter.attachView(this)

        return v
    }

    override fun onResume() {
        listener.setTitle(R.string.title_create_group)
        super.onResume()
    }

    private val add = View.OnClickListener {
        presenter.addPhone(et_pass.text.toString())
        et_pass.text.clear()
    }

    private val save = View.OnClickListener {
        presenter.check(et_name.text.toString(), et_sender.text.toString(), et_pass.text.toString())
    }

    override fun showAlert(title: Int, msg: Int) {
        DialogAlert().onCreateDialog(activity!!, title, msg).show()
    }

    override fun reset() {
        et_name.text.clear()
        et_sender.text.clear()
        et_pass.text.clear()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}