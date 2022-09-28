package cl.orlandoormazabal.reigndemoapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.databinding.ViewHitItemBinding
import cl.orlandoormazabal.reigndemoapp.extensions.getAuthor
import cl.orlandoormazabal.reigndemoapp.extensions.getTitle

class HitItemView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributes, defStyleAttr) {

    private val binding = ViewHitItemBinding.inflate(LayoutInflater.from(context), this, true)

    fun setHitContentData(hit: Hit) {
        with(binding) {
            titleLabel.text = hit.getTitle()
            authorLabel.text = hit.getAuthor()
        }
    }
}