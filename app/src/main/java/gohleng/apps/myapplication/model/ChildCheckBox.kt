package gohleng.apps.myapplication.model

class ChildCheckBox constructor(name: String, isChecked: Boolean) {
    private var name: String? = null
    private var isChecked: Boolean = false

    init {
        this.name = name
        this.isChecked = isChecked
    }

    fun getName() : String? {
        return name
    }

    fun setIsChecked(isChecked: Boolean) {
        this.isChecked = isChecked
    }

    fun isChecked(): Boolean {
        return isChecked
    }
}