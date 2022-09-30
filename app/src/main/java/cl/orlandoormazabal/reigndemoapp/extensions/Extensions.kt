package cl.orlandoormazabal.reigndemoapp.extensions

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
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
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun Hit.getTitle() =
    when {
        highLightResult.storyTitle?.value != null -> highLightResult.storyTitle.value
        highLightResult.title?.value != null -> highLightResult.title.value
        else -> "No title"
    }

@RequiresApi(Build.VERSION_CODES.O)
fun Hit.getAuthorAndCreatedAt() = "$author - ${this.getCreatedAt()}"

fun Hit.getUrl() =
    when {
        this.storyUrl != null -> this.storyUrl
        this.url != null -> this.url
        this.highLightResult.storyUrl?.value != null -> this.highLightResult.storyUrl.value
        this.highLightResult.url?.value != null -> highLightResult.url.value
        else -> null
    }

@RequiresApi(Build.VERSION_CODES.O)
fun Hit.getCreatedAt(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.FRANCE)
    val created = Date.from(Instant.parse(formatter.format(ZonedDateTime.parse(createdAt))))
    val currentDate = Calendar.getInstance()
    val datesDifference = currentDate.timeInMillis - created.time
    val minutes = datesDifference/(1000 * 60)
    val hours = datesDifference/(1000 * 60 * 60)
    val days = datesDifference/(1000 * 60 * 60 * 24)
    return when {
        days.toInt() >= 2 -> "${days}d"
        days.toInt() == 1 -> "Yesterday"
        hours in 1..23 -> "${hours}h"
        else -> "${minutes}m"
    }
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

fun RecyclerView.addSwipeListener(onLeft: (position: Int) -> Unit) {

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