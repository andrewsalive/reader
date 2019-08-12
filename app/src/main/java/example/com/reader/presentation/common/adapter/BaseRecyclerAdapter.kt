package example.com.reader.presentation.common.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import example.com.reader.extensions.doOnClick

abstract class BaseRecyclerAdapter<T>(protected val context: Context) : RecyclerView.Adapter<BaseRecyclerHolder>() {

    protected val inflater: LayoutInflater = LayoutInflater.from(context)
    open var items: MutableList<T> = mutableListOf()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    private var itemClickedListener: ((T) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items: List<T>?) {
        val pos = itemCount
        if (items != null && items.isNotEmpty()) {
            this.items.addAll(items)
            notifyItemRangeInserted(pos, items.size)
        }
    }

    open fun addItems(items: List<T>?, pos: Int) {
        if (items != null && items.isNotEmpty()) {
            this.items.addAll(pos, items)
            notifyItemRangeInserted(pos, items.size)
        }
    }

    open fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    open fun addItem(item: T, pos: Int) {
        items.add(pos, item)
        notifyItemInserted(pos)
    }

    open fun getItem(position: Int): T {
        return items[position]
    }

    fun getPosition(item: T): Int {
        return items.indexOf(item)
    }

    open fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(item: T) {
        val position = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        holder.itemView.doOnClick { itemClickedListener?.invoke(getItem(holder.adapterPosition)) }
    }

    open fun itemClicked(block: (T) -> Unit) {
        itemClickedListener = block
    }
}