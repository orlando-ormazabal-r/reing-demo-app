package cl.orlandoormazabal.reigndemoapp.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.orlandoormazabal.reigndemoapp.data.model.Hit

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun Hit.getTitle() = highLightResult.storyTitle?.value ?: highLightResult.title.value

fun Hit.getAuthor() = author

fun RecyclerView.addDividerDecorator() {
    val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
    this.addItemDecoration(dividerItemDecoration)
}
