package cl.orlandoormazabal.reigndemoapp.extensions

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.orlandoormazabal.reigndemoapp.data.model.*

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