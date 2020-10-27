package co.micode.brewdogbeerchallenge.viewModel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.cachedIn
import co.micode.brewdogbeerchallenge.beerdetails.BeerDetailsDataSource
import co.micode.brewdogbeerchallenge.beerdetails.BeerDetailsItem
import co.micode.brewdogbeerchallenge.beerlist.BeerListItem
import co.micode.brewdogbeerchallenge.utils.NavigateAction
import co.micode.brewdogbeerchallenge.utils.NavigateToDetails
import kotlinx.coroutines.launch

class MainViewModel(
    pager: Pager<Int, BeerListItem>,
    private val beerDetailsDataSource: BeerDetailsDataSource,
) : ViewModel() {

    private val navigateActionMutableData = MutableLiveData<NavigateAction>()
    val navigateActions: LiveData<NavigateAction> = navigateActionMutableData

    private val beerDetailsMutableData = MutableLiveData<BeerDetailsItem>()
    val beerDetailsData: LiveData<BeerDetailsItem> = beerDetailsMutableData

    val beersDataSource = pager
        .flow
        .cachedIn(viewModelScope)
        .asLiveData()

    fun showItemDetails(beerListItem: BeerListItem) {
        fetchItemDetails(beerListItem)
        navigateToDetailsScreen()
    }

    private fun navigateToDetailsScreen() {
        navigateActionMutableData.postValue(NavigateToDetails)
    }

    private fun fetchItemDetails(beerListItem: BeerListItem) {
        viewModelScope.launch {
            beerDetailsMutableData.postValue(beerDetailsDataSource.loadBeerDetails(beerListItem.id))
        }
    }
}
