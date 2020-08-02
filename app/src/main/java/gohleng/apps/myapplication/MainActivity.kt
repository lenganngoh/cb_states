package gohleng.apps.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import gohleng.apps.myapplication.adapter.CheckboxExpandableListAdapter
import gohleng.apps.myapplication.adapter.CheckboxExpandableListAdapter.Listener
import gohleng.apps.myapplication.test.Test
import gohleng.apps.myapplication.widget.ThreeStateCheckBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: CheckboxExpandableListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeListView()
        initializeListViewClickers()
        initializeListViewSelector()
    }

    private fun initializeListView() {
        val dataKeys = ArrayList(Test.data.keys)
        adapter = CheckboxExpandableListAdapter(this, dataKeys as ArrayList<String>, Test.data,
            object : Listener {
                override fun onNotifyDataSetChanged() {
                    when (adapter!!.getAllCheckedChildrenCount()) {
                        adapter!!.getChildrenCount() -> {
                            cbSelectAll.setState(ThreeStateCheckBox.State.CHECKED)
                        }
                        0 -> {
                            cbSelectAll.setState(ThreeStateCheckBox.State.UNCHECKED)
                        }
                        else -> {
                            cbSelectAll.setState(ThreeStateCheckBox.State.INDETERMINATE)
                        }
                    }
                }
            }
        )
        listCheckbox.setAdapter(adapter)
    }

    private fun initializeListViewClickers() {
        listCheckbox.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val child = adapter!!.getChild(groupPosition, childPosition)
            child.setIsChecked(!child.isChecked())
            adapter!!.notifyDataSetChanged()
            true
        }
    }

    private fun initializeListViewSelector() {
        cbSelectAll.setOnClickListener {
            adapter!!.selectAllChild(!adapter!!.isAllSelected())
            adapter!!.notifyDataSetChanged()
        }
    }
}