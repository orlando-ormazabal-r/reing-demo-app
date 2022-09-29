package cl.orlandoormazabal.reigndemoapp.extensions

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.data.model.HitEntity
import cl.orlandoormazabal.reigndemoapp.data.model.Title
import cl.orlandoormazabal.reigndemoapp.data.model.HighLightResult

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun Hit.getTitle() = highLightResult.storyTitle?.value ?: highLightResult.title.value

fun Hit.getAuthor() = author

fun Hit.getUrl() =
    when {
        this.storyUrl != null -> this.storyUrl
        this.url != null -> this.url
        this.highLightResult.storyUrl?.value != null -> this.highLightResult.storyUrl.value
        this.highLightResult.url?.value != null -> highLightResult.url.value
        else -> null
    }

fun RecyclerView.addDividerDecorator() {
    val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
    this.addItemDecoration(dividerItemDecoration)
}

fun WebView.loadUrl(url: String, func: () -> Unit = { } ) {
    this.webViewClient = object: WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            func()
        }
    }
    this.loadUrl(url)
}

fun Hit.toHitEntity() =
    HitEntity(
        id = 0,
        author = author,
        objectId = objectId,
        createdAt = createdAt,
        storyUrl = getUrl(),
        title = getTitle())

fun HitEntity.toHit() =
    Hit(
        objectId = objectId,
        createdAt = createdAt,
        author = author,
        storyUrl = storyUrl,
        url = null,
        highLightResult = HighLightResult(
            title = Title(title),
            storyTitle = null,
            storyUrl = null,
            url = null)
    )

fun RecyclerView.addSwipeAction(onLeft: (position: Int) -> Unit) {

    val simpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            when (direction) {
                ItemTouchHelper.LEFT -> {
                    onLeft(position)
                }
                else -> { }
            }
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {

            super.onChildDraw(
                c,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
            val itemView = viewHolder.itemView
            val backgroundCornerOffset = 20
            val background = ColorDrawable(Color.RED)

            when {
                dX > 0 -> {
                    background.setBounds(
                        itemView.left, itemView.top,
                        itemView.left + dX.toInt() + backgroundCornerOffset,
                        itemView.bottom)
                }
                dX < 0 -> {
                    background.setBounds(
                        itemView.right + dX.toInt() - backgroundCornerOffset,
                        itemView.top, itemView.right, itemView.bottom)
                }
                else -> background.setBounds(0, 0, 0, 0)
            }
            background.draw(c)
        }
    }
    val itemTouchHelper = ItemTouchHelper(simpleCallback)
    itemTouchHelper.attachToRecyclerView(this)
}