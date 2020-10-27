package co.micode.brewdogbeerchallenge.beerlist

import androidx.paging.PagingSource
import co.micode.brewdogbeerchallenge.api.PunkApiService

class BeerListDataSource(private val punkApiService: PunkApiService) :
    PagingSource<Int, BeerListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerListItem> {
        try {
            val pageNumber = getPageNumber(params)

            return LoadResult.Page(
                data = convertResponseToBeerListItems(params),
                prevKey = getPreviousKey(pageNumber),
                nextKey = getNextKey(pageNumber)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun convertResponseToBeerListItems(params: LoadParams<Int>): List<BeerListItem> {
        return getApiResponseForCurrentPage(params).map {
            BeerListItem.generateFrom(it)
        }
    }

    private suspend fun getApiResponseForCurrentPage(params: LoadParams<Int>) =
        punkApiService.fetchBeers(getPageNumber(params), params.loadSize)

    private fun getNextKey(nextPage: Int) = nextPage + PAGE_INCREMENT

    private fun getPreviousKey(nextPage: Int) =
        if (nextPage == FIRST_PAGE) null else nextPage - PAGE_INCREMENT

    private fun getPageNumber(params: LoadParams<Int>) =
        params.key ?: FIRST_PAGE

    companion object {
        const val FIRST_PAGE = 1
        const val PAGE_INCREMENT = 1
    }
}
