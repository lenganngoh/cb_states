package gohleng.apps.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import gohleng.apps.myapplication.R


class ThreeStateCheckBox : AppCompatCheckBox {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    enum class State {
        CHECKED,
        UNCHECKED,
        INDETERMINATE
    }

    var cachedState: State = State.UNCHECKED

    fun setState(state: State) {
        cachedState = when (state) {
            State.UNCHECKED -> {
                setButtonDrawable(R.drawable.vector_unchecked)
                State.UNCHECKED
            }
            State.CHECKED -> {
                setButtonDrawable(R.drawable.vector_checked)
                State.CHECKED
            }
            else -> {
                setButtonDrawable(R.drawable.vector_indeterminate)
                State.INDETERMINATE
            }
        }
    }
}