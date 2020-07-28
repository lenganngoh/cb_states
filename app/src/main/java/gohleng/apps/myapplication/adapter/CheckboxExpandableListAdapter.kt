package gohleng.apps.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import gohleng.apps.myapplication.R
import gohleng.apps.myapplication.model.ChildCheckBox
import gohleng.apps.myapplication.widget.ThreeStateCheckBox

class CheckboxExpandableListAdapter internal constructor(
    private val context: Context,
    private val listTitle: List<String>,
    private val mapData: HashMap<String, List<ChildCheckBox>>,
    private val listener: Listener
) : BaseExpandableListAdapter() {

    override fun getGroup(groupPosition: Int): Any {
        return this.listTitle[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        view: View?,
        parent: ViewGroup?
    ): View {
        var convertView = view
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.viewholder_list_group_checkbox, null)
        }

        initGroupCheckBox(convertView, groupPosition)
        return convertView!!
    }

    private fun initGroupCheckBox(convertView: View?, groupPosition: Int) {
        val cbItem = convertView!!.findViewById<ThreeStateCheckBox>(R.id.cbItem)
        cbItem.text = getGroup(groupPosition) as String

        cbItem.setOnClickListener {
            val isGroupChecked = cbItem.cachedState == ThreeStateCheckBox.State.CHECKED
            cbItem.setState(if (isGroupChecked) ThreeStateCheckBox.State.UNCHECKED else ThreeStateCheckBox.State.CHECKED)
            selectAllChildOfGroup(!isGroupChecked, groupPosition)
        }

        initGroupCheckBoxState(cbItem, groupPosition)
    }

    private fun initGroupCheckBoxState(cbItem: ThreeStateCheckBox, groupPosition: Int) {
        val checkedChildren = getAllCheckedChildrenCount(groupPosition)
        when {
            checkedChildren == 0 -> {
                cbItem.setState(ThreeStateCheckBox.State.UNCHECKED)
            }
            checkedChildren < getChildrenCount(groupPosition) -> {
                cbItem.setState(ThreeStateCheckBox.State.INDETERMINATE)
            }
            else -> {
                cbItem.setState(ThreeStateCheckBox.State.CHECKED)
            }
        }
    }

    private fun getAllCheckedChildrenCount(groupPosition: Int): Int {
        var checkedChildren = 0
        for (x in 0 until getChildrenCount(groupPosition)) {
            val child = getChild(groupPosition, x)
            if (child.isChecked()) {
                checkedChildren++
            }
        }
        return checkedChildren
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.mapData[this.listTitle[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): ChildCheckBox {
        return this.mapData[this.listTitle[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        view: View?,
        parent: ViewGroup?
    ): View {
        var convertView = view
        if (view == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.viewholder_list_child_checkbox, null)
        }

        initChildCheckBoxState(convertView, groupPosition, childPosition)
        return convertView!!
    }

    private fun initChildCheckBoxState(convertView: View?, groupPosition: Int, childPosition: Int) {
        val cbItem = convertView!!.findViewById<CheckBox>(R.id.cbItem)

        val child = getChild(groupPosition, childPosition)
        cbItem.setOnCheckedChangeListener(null)

        cbItem.text = child.getName()
        cbItem.isChecked = child.isChecked()

        cbItem.setOnCheckedChangeListener { _, isChecked ->
            child.setIsChecked(isChecked)
            notifyDataSetChanged()
        }
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return this.listTitle.size
    }

    private fun getGroupSelectedCount(): Int {
        var checkedGroup = 0
        for (x in 0 until groupCount) {
            if (getAllCheckedChildrenCount(x) == getChildrenCount(x)) {
                checkedGroup++
            }
        }
        return checkedGroup
    }

    fun getGroupSelectedCountWithIndeterminate(): Int {
        var checkedGroup = 0
        for (x in 0 until groupCount) {
            if (getAllCheckedChildrenCount(x) > 0) {
                checkedGroup++
            }
        }
        return checkedGroup
    }

    fun isAllGroupSelected() : Boolean {
        return getGroupSelectedCount() == groupCount
    }

    fun selectAllChild(shouldSelect: Boolean) {
        for (x in 0 until groupCount) {
            for (y in 0 until getChildrenCount(x)) {
                val child = getChild(x, y)
                child.setIsChecked(shouldSelect)
            }
        }
        notifyDataSetChanged()
    }

    private fun selectAllChildOfGroup(shouldSelect: Boolean, groupPosition: Int) {
        for (x in 0 until getChildrenCount(groupPosition)) {
            val child = getChild(groupPosition, x)
            child.setIsChecked(shouldSelect)
        }
        notifyDataSetChanged()
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        listener.onNotifyDataSetChanged()
    }

    interface Listener {
        fun onNotifyDataSetChanged()
    }
}
