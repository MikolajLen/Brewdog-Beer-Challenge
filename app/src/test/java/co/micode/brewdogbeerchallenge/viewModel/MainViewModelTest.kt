package co.micode.brewdogbeerchallenge.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import co.micode.brewdogbeerchallenge.beerdetails.BeerDetailsDataSource
import co.micode.brewdogbeerchallenge.beerlist.BeerListItem
import co.micode.brewdogbeerchallenge.utils.NavigateToDetails
import com.example.android.kotlincoroutines.main.utils.MainCoroutineScopeRule
import com.google.common.truth.Truth
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val pager = Pager(PagingConfig(pageSize = 1)) {
        mock<PagingSource<Int, BeerListItem>> { }
    }
    private val beerDetailsDataSource: BeerDetailsDataSource = mock { }

    @Test
    fun `should navigate to item details fragment`() {
        //given
        val underTest = MainViewModel(pager, beerDetailsDataSource)

        //when
        underTest.showItemDetails(BeerListItem(1, "test", 1.0, "/"))

        //then
        Truth.assertThat(underTest.navigateActions.value).isEqualTo(NavigateToDetails)
    }

    @Test
    fun `should fetch item details`() = coroutineScope.runBlockingTest {
        //given
        val underTest = MainViewModel(pager, beerDetailsDataSource)

        //when
        underTest.showItemDetails(BeerListItem(1, "test", 1.0, "/"))

        //then
        verify(beerDetailsDataSource).loadBeerDetails(1)
    }
}
