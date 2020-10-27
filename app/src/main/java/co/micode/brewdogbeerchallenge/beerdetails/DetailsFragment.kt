package co.micode.brewdogbeerchallenge.beerdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.micode.brewdogbeerchallenge.R
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.DetailsListAdapter
import co.micode.brewdogbeerchallenge.utils.ImageLoader
import co.micode.brewdogbeerchallenge.utils.RATE_PRECISION
import co.micode.brewdogbeerchallenge.utils.format
import co.micode.brewdogbeerchallenge.viewModel.MainViewModel
import co.micode.brewdogbeerchallenge.viewModel.MainViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var detailsAdapter: DetailsListAdapter

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initParametersList()
        observeDetailsData()
    }

    private fun initParametersList() {
        parameters_list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailsAdapter
        }
    }

    private fun observeDetailsData() {
        viewModel.beerDetailsData.observe(viewLifecycleOwner, {
            beer_name.text = it.name
            beer_rate.text = it.rate.format(RATE_PRECISION)
            beer_description.text = it.description
            imageLoader.loadImage(it.imageUrl, beer_logo)
            detailsAdapter.setItems(it.parameters)
        })
    }
}
