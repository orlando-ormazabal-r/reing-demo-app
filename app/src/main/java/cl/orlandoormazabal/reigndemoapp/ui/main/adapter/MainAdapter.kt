package cl.orlandoormazabal.reigndemoapp.ui.main.adapter

import android.view.ViewGroup
import cl.orlandoormazabal.reigndemoapp.base.adapter.BaseAdapter
import cl.orlandoormazabal.reigndemoapp.base.viewholder.BaseViewHolder
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.widget.HitItemView

class MainAdapter(private val manager: HitManager): BaseAdapter<Hit>() {

    interface HitManager {
        fun onHitItemClick(hit: Hit)
    }

    override fun provideComparator() = compareBy(Hit::objectId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Hit> =
        HitViewHolder(HitItemView(parent.context))

    inner class HitViewHolder(private val view: HitItemView): BaseViewHolder<Hit>(view) {

        override fun bindView(item: Hit) {
            view.setHitContentData(item)
            view.setOnClickListener {
                manager.onHitItemClick(item)
            }
        }
    }
}