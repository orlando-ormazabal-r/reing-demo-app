package cl.orlandoormazabal.reigndemoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cl.orlandoormazabal.reigndemoapp.base.resource.Resource
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.databinding.FragmentMainBinding
import cl.orlandoormazabal.reigndemoapp.extensions.addDividerDecorator
import cl.orlandoormazabal.reigndemoapp.extensions.observe
import cl.orlandoormazabal.reigndemoapp.ui.main.adapter.MainAdapter
import org.koin.android.ext.android.inject

private const val SHOW_LOADING = 0
private const val SHOW_CONTENT = 1

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<MainViewModel>()
    private val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        initObservables()
        viewModel.getHitList()
    }

    private fun setRecyclerView() {
        binding.recyclerView.addDividerDecorator()
        binding.recyclerView.adapter = adapter
    }

    private fun initObservables() {
        with(viewModel) {
            observe(hitList,::result)
        }
    }

    private fun result(result: Resource<List<Hit>>?) {
        when (result) {
            is Resource.Loading -> displayView(SHOW_LOADING)
            is Resource.Success -> {
                adapter.swapItems(result.data)
                displayView(SHOW_CONTENT)
            }
            else -> { }
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
