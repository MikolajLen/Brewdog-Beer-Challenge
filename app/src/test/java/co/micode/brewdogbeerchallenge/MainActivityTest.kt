package co.micode.brewdogbeerchallenge

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingData
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.micode.brewdogbeerchallenge.beerdetails.BeerDetailsItem
import co.micode.brewdogbeerchallenge.beerlist.BeerListItem
import co.micode.brewdogbeerchallenge.di.BrewdogBeerChallengeApp
import co.micode.brewdogbeerchallenge.testDI.DaggerTestAppComponent
import co.micode.brewdogbeerchallenge.utils.NavigateAction
import co.micode.brewdogbeerchallenge.utils.NavigateToDetails
import co.micode.brewdogbeerchallenge.viewModel.MainViewModel
import co.micode.brewdogbeerchallenge.viewModel.MainViewModelFactory
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val beerDataSource = MutableLiveData<PagingData<BeerListItem>>()
    private val navigateActionMutableData = MutableLiveData<NavigateAction>()
    private val beerDetailsMutableData = MutableLiveData<BeerDetailsItem>()

    private val viewModel = mock<MainViewModel> {
        on(it.navigateActions).thenReturn(navigateActionMutableData)
        on(it.beersDataSource).thenReturn(beerDataSource)
        on(it.beerDetailsData).thenReturn(beerDetailsMutableData)
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
    fun `should navigate to item details`() {
        //given
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        var controller: NavController? = null
        scenario.onActivity {
            controller = Navigation.findNavController(it, R.id.nav_host_fragment)
        }

        //when
        navigateActionMutableData.postValue(NavigateToDetails)

        //then
        assertThat(controller!!.currentDestination!!.id).isEqualTo(R.id.detailsFragment)
    }
}
