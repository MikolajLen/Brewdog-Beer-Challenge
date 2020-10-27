package co.micode.brewdogbeerchallenge.beerdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import co.micode.brewdogbeerchallenge.R
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.BeerParameters
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.DetailsListAdapter
import co.micode.brewdogbeerchallenge.di.BrewdogBeerChallengeApp
import co.micode.brewdogbeerchallenge.testDI.DaggerTestAppComponent
import co.micode.brewdogbeerchallenge.utils.ImageLoader
import co.micode.brewdogbeerchallenge.utils.NavigateAction
import co.micode.brewdogbeerchallenge.viewModel.MainViewModel
import co.micode.brewdogbeerchallenge.viewModel.MainViewModelFactory
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var detailsAdapter: DetailsListAdapter

    @Inject
    lateinit var imageLoader: ImageLoader

    private val navigateActionMutableData = MutableLiveData<NavigateAction>()
    private val beerDetailsMutableData = MutableLiveData<BeerDetailsItem>()

    private val viewModel = mock<MainViewModel> {
        on(it.navigateActions).thenReturn(navigateActionMutableData)
        on(it.beerDetailsData).thenReturn(beerDetailsMutableData)
    }

    private val parameters = listOf(mock<BeerParameters> { })

    private val testItem = BeerDetailsItem(
        "name",
        4.5,
        "description",
        "http://image.png",
        parameters
    )

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<BrewdogBeerChallengeApp>()
        val component = DaggerTestAppComponent.create()
        component.inject(app)
        component.inject(this)
        whenever(viewModelFactory.create(MainViewModel::class.java)).thenReturn(viewModel)
    }

    @Test
    fun `should properly update UI`() {
        //given
        launchFragmentInContainer<DetailsFragment>()

        //when
        beerDetailsMutableData.postValue(testItem)

        //then
        onView(withId(R.id.beer_name)).check(matches(withText("name")))
        onView(withId(R.id.beer_rate)).check(matches(withText("4.5")))
        onView(withId(R.id.beer_description)).check(matches(withText("description")))
    }

    @Test
    fun `should load image`() {
        //given
        launchFragment<DetailsFragment>()

        //when
        beerDetailsMutableData.postValue(testItem)

        //then
        verify(imageLoader).loadImage(eq("http://image.png"), any())
    }

    @Test
    fun `should set parameters to adapter`() {
        //given
        launchFragment<DetailsFragment>()

        //when
        beerDetailsMutableData.postValue(testItem)

        //then
        verify(detailsAdapter).setItems(parameters)
    }
}
