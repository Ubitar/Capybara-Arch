package com.ubitar.app.demo3.adapter

import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ubitar.app.R
import com.ubitar.app.databinding.HolderDemo3Binding
import com.ubitar.app.demo3.bean.ListBean

class Demo3Adapter : BaseQuickAdapter<ListBean, BaseViewHolder>(R.layout.holder_demo3) {

    override fun onItemViewHolderCreated(viewHolder:BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<HolderDemo3Binding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: ListBean) {
        val binding = DataBindingUtil.getBinding<HolderDemo3Binding>(holder.itemView)
        binding?.listBean = item
    }
}