package cl.orlandoormazabal.reigndemoapp.widget

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.databinding.ViewHitItemBinding
import cl.orlandoormazabal.reigndemoapp.extensions.getAuthorAndCreatedAt
import cl.orlandoormazabal.reigndemoapp.extensions.getTitle

class HitItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributes, defStyleAttr) {

    private val binding = ViewHitItemBinding.inflate(LayoutInflater.from(context), this, true).apply {
        val params = this.root.layoutParams
        params.width = Resources.getSystem().displayMetrics.widthPixels
        this.root.layoutParams = params
    }

    fun setHitContentData(hit: Hit) {
        with(binding) {
            titleLabel.text = hit.getTitle()
            authorLabel.text = hit.getAuthorAndCreatedAt()
        }
    }
}