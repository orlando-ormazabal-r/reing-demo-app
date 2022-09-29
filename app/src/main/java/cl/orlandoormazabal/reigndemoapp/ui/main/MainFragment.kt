package cl.orlandoormazabal.reigndemoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import cl.orlandoormazabal.reigndemoapp.R
import cl.orlandoormazabal.reigndemoapp.base.resource.Resource
import cl.orlandoormazabal.reigndemoapp.constants.HIT_URL_BUNDLE
import cl.orlandoormazabal.reigndemoapp.data.model.Hit
import cl.orlandoormazabal.reigndemoapp.databinding.FragmentMainBinding
import cl.orlandoormazabal.reigndemoapp.extensions.addDividerDecorator
import cl.orlandoormazabal.reigndemoapp.extensions.addSwipeAction
import cl.orlandoormazabal.reigndemoapp.extensions.getUrl
import cl.orlandoormazabal.reigndemoapp.extensions.observe
import cl.orlandoormazabal.reigndemoapp.ui.main.adapter.MainAdapter
import org.koin.android.ext.android.inject

private const val SHOW_LOADING = 0
private const val SHOW_CONTENT = 1

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<MainViewModel>()
    private val adapter = MainAdapter(HitManager())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservables()
        viewModel.getHitList()

    }

    private fun initViews() {
        setSwipeLayout()
        setRecyclerView()
    }

    private fun setSwipeLayout() {
        binding.swipeLayout.setOnRefreshListener {
            viewModel.clearData()
            viewModel.getHitList()
            binding.swipeLayout.isRefreshing = false
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.addSwipeAction { position ->
            adapter.removeItemAt(position, true)
            viewModel.insertDeleteHitId(adapter.getItem(position).objectId)
        }
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

    inner class HitManager : MainAdapter.HitManager {
        override fun onHitItemClick(hit: Hit) {
            findNavController().navigate(R.id.mainFragment_to_detailFragment,
            bundleOf(HIT_URL_BUNDLE to hit.getUrl()))
        }
    }
}