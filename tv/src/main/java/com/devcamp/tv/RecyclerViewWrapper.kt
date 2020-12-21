package com.devcamp.tv

import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewWrapper<T, V>(val view: V, private val onItemClickListener: OnItemClickListener?,
                                private val onItemLongClickListener: OnItemLongClickListener?,
                                private val onItemCheckedChangedListener: OnItemCheckedChangedListener? = null) :
        RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener,
        CompoundButton.OnCheckedChangeListener where V : View, V : RecyclerViewWrapper.Binder<T> {

    override fun onLongClick(view: View): Boolean {
        return onItemLongClickListener?.onItemLongClick(view, layoutPosition) ?: false
    }

    override fun onClick(view: View) {
        view.context.preventMultipleClick {
            onItemClickListener?.onItemClick(view, layoutPosition)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        onItemCheckedChangedListener?.onItemCheckChanged(buttonView, layoutPosition, isChecked)
    }

    interface Binder<T> {
        fun bind(data: T, position: Int)

        fun click(onClickListener: View.OnClickListener)

        fun longClick(onLongClickListener: View.OnLongClickListener)

        fun checkItem(data: T, position: Int, onCheckedChangeListener: CompoundButton.OnCheckedChangeListener?)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemClick(view: View, positionParent: Int, positionChild: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int): Boolean
    }

    interface OnItemCheckedChangedListener {
        fun onItemCheckChanged(view: View, position: Int, checked: Boolean)
    }
}
