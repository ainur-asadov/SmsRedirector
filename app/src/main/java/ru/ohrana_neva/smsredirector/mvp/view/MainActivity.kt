package ru.ohrana_neva.smsredirector.mvp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.ohrana_neva.smsredirector.R
import ru.ohrana_neva.smsredirector.repository.DataBase
import ru.ohrana_neva.smsredirector.utils.FragmentChangeListener
import ru.ohrana_neva.smsredirector.utils.changeFragment
import ru.ohrana_neva.smsredirector.utils.changeFragmentWithoutBackStack

class MainActivity : AppCompatActivity(), FragmentChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragmentWithoutBackStack(FragmentMain(), R.id.container)
    }

    override fun setTitle(title: Int) {
        tv_title.text = resources.getString(title)
    }

    override fun showCreateFragment() {
        changeFragment(FragmentCreate(), R.id.container)
    }

    override fun showExpandFragment(name: String) {
        changeFragment(FragmentExpandable.newInstance(name), R.id.container)
    }

    override fun showEditFragment(groupName: String) {
        changeFragment(FragmentEdit.newInstance(groupName), R.id.container)
    }

    override fun returnToStartStack() {
        changeFragmentWithoutBackStack(FragmentMain(), R.id.container)
    }

    override fun onDestroy() {
        DataBase.closeDB()
        super.onDestroy()
    }
}
