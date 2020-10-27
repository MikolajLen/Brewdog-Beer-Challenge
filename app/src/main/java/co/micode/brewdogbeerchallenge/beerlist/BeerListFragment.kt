package co.micode.brewdogbeerchallenge.beerlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.micode.brewdogbeerchallenge.R
import co.micode.brewdogbeerchallenge.viewModel.MainViewModel
import co.micode.brewdogbeerchallenge.viewModel.MainViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class BeerListFragment : DaggerFragment() {

    @Inject
    lateinit var beerListAdapter: BeerListAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBeerList()
        handleListItemClick()
        startObservingListData()
    }

    private fun initBeerList() {
        list.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
            adapter = beerListAdapter
        }
    }

    private fun handleListItemClick() {
        beerListAdapter.beerListItemClickListener = { beerListItem ->
            beerListItem?.let {
                viewModel.showItemDetails(it)
            }
        }
    }

    private fun startObservingListData() {
        viewModel.beersDataSource.observe(viewLifecycleOwner, Observer { items ->
            beerListAdapter.submitData(lifecycle, items)
        })
    }
}
