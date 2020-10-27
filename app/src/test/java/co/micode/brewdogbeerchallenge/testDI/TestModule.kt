package co.micode.brewdogbeerchallenge.testDI

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.DetailsListAdapter
import co.micode.brewdogbeerchallenge.beerlist.BeerListAdapter
import co.micode.brewdogbeerchallenge.beerlist.BeerListItem
import co.micode.brewdogbeerchallenge.utils.ImageLoader
import co.micode.brewdogbeerchallenge.viewModel.MainViewModelFactory
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestModule {

    @Singleton
    @Provides
    fun provideTestPager() = Pager(PagingConfig(pageSize = 1)) {
        mock<PagingSource<Int, BeerListItem>> { }
    }

    @Provides
    @Singleton
    fun provideMainViewModelFactory(): MainViewModelFactory = mock { }

    @Provides
    @Singleton
    fun provideBeerListAdapter(): BeerListAdapter = mock { }

    @Provides
    @Singleton
    fun provideDetailsListAdapter(): DetailsListAdapter = mock { }

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader = mock { }
}
