package gohleng.apps.myapplication.test

import gohleng.apps.myapplication.model.ChildCheckBox

internal object Test {
    val data: HashMap<String, List<ChildCheckBox>>
        get() {
            val groupList = HashMap<String, List<ChildCheckBox>>()

            val childA: MutableList<ChildCheckBox> = ArrayList()
            childA.add(ChildCheckBox("98XVC", false))
            childA.add(ChildCheckBox("124AS", false))
            childA.add(ChildCheckBox("AXC98", false))
            groupList["GROUP1"] = childA

            val childB: MutableList<ChildCheckBox> = ArrayList()
            childB.add(ChildCheckBox("MNO24", false))
            childB.add(ChildCheckBox("XXA34", false))
            groupList["GROUP2"] = childB

            val childC: MutableList<ChildCheckBox> = ArrayList()
            childC.add(ChildCheckBox("A8XC7", false))
            childC.add(ChildCheckBox("WCV32", false))
            childC.add(ChildCheckBox("ZCP93", false))
            childC.add(ChildCheckBox("WAX14", false))
            groupList["GROUP3"] = childC

            return groupList
        }
}