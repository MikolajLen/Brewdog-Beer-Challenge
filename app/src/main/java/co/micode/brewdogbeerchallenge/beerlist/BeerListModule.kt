package co.micode.brewdogbeerchallenge.beerlist

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import co.micode.brewdogbeerchallenge.api.PunkApiService
import co.micode.brewdogbeerchallenge.utils.PAGE_SIZE
import dagger.Module
import dagger.Provides

@Module
class BeerListModule {

    @Provides
    fun providePagingConfig() = PagingConfig(PAGE_SIZE)

    @Provides
    fun providePagingSource(service: PunkApiService): PagingSource<Int, BeerListItem> =
        BeerListDataSource(service)

    @Provides
    fun providePager(source: PagingSource<Int, BeerListItem>, config: PagingConfig) =
        Pager(config) {
            source
        }
}
