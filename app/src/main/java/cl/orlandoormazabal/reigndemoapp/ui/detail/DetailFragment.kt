package cl.orlandoormazabal.reigndemoapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.orlandoormazabal.reigndemoapp.constants.HIT_URL_BUNDLE
import cl.orlandoormazabal.reigndemoapp.databinding.FragmentDetailBinding
import cl.orlandoormazabal.reigndemoapp.extensions.loadUrl

private const val SHOW_LOADING = 0
private const val SHOW_CONTENT = 1
private const val SHOW_NO_URL = 2

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var hitUrl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hitUrl = arguments?.getString(HIT_URL_BUNDLE)
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayView(SHOW_LOADING)
        verifyUrl()
    }

    private fun verifyUrl() {
        if( hitUrl != null) {
            loadHitPage(hitUrl!!)
        } else {
            displayView(SHOW_NO_URL)
        }
    }

    private fun loadHitPage(url: String) {
        binding.webView.loadUrl(url) {
            displayView(SHOW_CONTENT)
        }
    }

    private fun displayView(viewIndex: Int) {
        binding.flipper.displayedChild = viewIndex
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}