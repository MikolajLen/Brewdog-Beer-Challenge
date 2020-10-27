package co.micode.brewdogbeerchallenge.beerlist

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.micode.brewdogbeerchallenge.di.BrewdogBeerChallengeApp
import co.micode.brewdogbeerchallenge.testDI.DaggerTestAppComponent
import co.micode.brewdogbeerchallenge.utils.NavigateAction
import co.micode.brewdogbeerchallenge.viewModel.MainViewModel
import co.micode.brewdogbeerchallenge.viewModel.MainViewModelFactory
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class BeerListFragmentTest {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var beerListAdapter: BeerListAdapter

    private val navigateActionMutableData = MutableLiveData<NavigateAction>()
    private val beerDataSource = MutableLiveData<PagingData<BeerListItem>>()

    private val viewModel = mock<MainViewModel> {
        on(it.navigateActions).thenReturn(navigateActionMutableData)
        on(it.beersDataSource).thenReturn(beerDataSource)
    }

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<BrewdogBeerChallengeApp>()
        val component = DaggerTestAppComponent.create()
        component.inject(app)
        component.inject(this)
        whenever(viewModelFactory.create(MainViewModel::class.java)).thenReturn(viewModel)
    }


    @Test
    fun `should navigate to details on item click`() {
        //given
        launchFragment<BeerListFragment>()
        val captor = argumentCaptor<BeerListItemClickListener>()
        verify(beerListAdapter).beerListItemClickListener = captor.capture()

        //wehn
        captor.value.invoke(mock { })

        //then
        verify(viewModel).showItemDetails(any())
    }

    @Test
    fun `should set data to adapter`() {
        //given
        launchFragment<BeerListFragment>()
        val mockData = mock<PagingData<BeerListItem>>()

        //when
        beerDataSource.postValue(mockData)

        //then
        verify(beerListAdapter).submitData(any(), eq(mockData))
    }
}

inline fun <reified T : Any> argumentCaptor() = ArgumentCaptor.forClass(T::class.java)
