# cb_states
#
# The goal of this project is to showcase the functionality of a three state checkbox with defined children and group
# • None: This state represents that Checkbox is not selected.
# • Indeterminate: This state represents the Checkbox is partially selected.
# • Checked: This state represents the Checkbox is fully selected.
#
# With the following mechanics
# • L1: This will be the top most level. It will have L2 & L3 as Childs hence maintain its state based upon Childs selections.
# • L2: This will be second level. It will have L3 as Childs & L1 as parent hence maintain its state based upon both parent & Childs selections.
# • L3: This will be the third level. It will have L2 & L1 both as parent hence maintain its state based upon both parents selections. Also, as it’s the last level in the tree so it doesn’t have indeterminate selection.
#
#
# To fulfill this assignment, a custom ExpandableListView, ExpandableListViewAdapter, and CheckBox are created.
# 
# Language used: Kotlin
#
