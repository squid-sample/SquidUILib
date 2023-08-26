package com.android.squid.mvvm.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M, B : ViewDataBinding>(
    context: Context,
    diffCallback: DiffUtil.ItemCallback<M>
) : ListAdapter<M, RecyclerView.ViewHolder>(diffCallback) {
    private var mContext: Context? = context
    private var mOnItemClickListener: OnItemClickListener<M>? = null
    private var mOnItemLongClickListener: OnItemLongClickListener<M>? = null
    private var mOnItemTouchListener: OnItemTouchListener<M>? = null

    init {
        setHasStableIds(true)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val b = DataBindingUtil.inflate<B>(
            LayoutInflater.from(mContext),
            getLayoutResId(viewType), parent, false
        )
        val holder = BaseBindingViewHolder(b.root)
        var layoutParams = holder.itemView.layoutParams
        layoutParams = getLayoutParam(layoutParams, parent, viewType)
        holder.itemView.layoutParams = layoutParams
        holder.itemView.setOnClickListener {
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mOnItemClickListener?.onItemClick(getItem(position), position)
            }
        }
        holder.itemView.setOnTouchListener { view: View?, motionEvent: MotionEvent? ->
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                if (motionEvent != null) {
                    if (view != null) {
                        mOnItemTouchListener?.onTouch(getItem(position), motionEvent, view)
                    }
                }
            }
            false
        }
        holder.itemView.setOnLongClickListener {
            val position = holder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                mOnItemLongClickListener?.onItemLongClick(getItem(position), position)
            }
            return@setOnLongClickListener false
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val b = DataBindingUtil.getBinding<B>(holder.itemView)
        b?.let {
            this.onBindItem(it, getItem(position), holder)
            it.executePendingBindings()
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    class BaseBindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    protected open fun getLayoutParam(
        layoutParams: ViewGroup.LayoutParams,
        parent: ViewGroup, viewType: Int
    ): ViewGroup.LayoutParams {
        return layoutParams
    }

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    protected abstract fun onBindItem(b: B, item: M, holder: RecyclerView.ViewHolder?)

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<M>) {
        this.mOnItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener<M>) {
        this.mOnItemLongClickListener = onItemLongClickListener
    }

    fun setOnItemTouchListener(onItemTouchListener: OnItemTouchListener<M>) {
        this.mOnItemTouchListener = onItemTouchListener
    }

    interface OnItemClickListener<I> {
        fun onItemClick(item: I, position: Int)
    }

    interface OnItemLongClickListener<I> {
        fun onItemLongClick(item: I, position: Int)
    }

    interface OnItemTouchListener<I> {
        fun onTouch(item: I, event: MotionEvent, view: View)
    }
}