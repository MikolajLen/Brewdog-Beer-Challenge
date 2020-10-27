package co.micode.brewdogbeerchallenge.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Pager
import co.micode.brewdogbeerchallenge.beerdetails.BeerDetailsDataSource
import co.micode.brewdogbeerchallenge.beerlist.BeerListItem
import javax.inject.Inject

class MainViewModelFactory
@Inject
constructor(
    private val pager: Pager<Int, BeerListItem>,
    private val beerDetailsDataSource: BeerDetailsDataSource,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(pager, beerDetailsDataSource) as T
        else throw IllegalArgumentException("Unknown viewmodel class: $modelClass")

}
