package co.micode.brewdogbeerchallenge.beerdetails

import android.content.res.Resources
import co.micode.brewdogbeerchallenge.api.PunkApiService
import co.micode.brewdogbeerchallenge.api.model.PunkApiResponse
import co.micode.brewdogbeerchallenge.api.model.PunkApiResponseItem
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.BeerHop
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.BeerMalt
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.BeerTemperature
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.Label
import co.micode.brewdogbeerchallenge.testUtils.punkApiResponseItemJSON
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class BeerDetailsDataSourceImplTest {

    private val service: PunkApiService = mock { }
    private val resources: Resources = mock {
        on(it.getString(any())).thenReturn("")
    }
    private val testItem = Gson().fromJson(punkApiResponseItemJSON, PunkApiResponseItem::class.java)

    @ExperimentalCoroutinesApi
    @Test
    fun `should fetch items from API`() = runBlockingTest {
        //given
        whenever(service.fetchSingleBeer(any())).thenReturn(PunkApiResponse().apply {
            add(testItem)
        })
        val underTest = BeerDetailsDataSourceImpl(service, resources)

        //when
        underTest.loadBeerDetails(0)

        //then
        verify(service).fetchSingleBeer(0)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should properly parse response`()  = runBlockingTest {
        //given
        whenever(service.fetchSingleBeer(any())).thenReturn(PunkApiResponse().apply {
            add(testItem)
        })
        val underTest = BeerDetailsDataSourceImpl(service, resources)

        //when
        val result = underTest.loadBeerDetails(0)

        //then
        verifyResult(result)
    }

    private fun verifyResult(result: BeerDetailsItem) {
        assertThat(result.name).isEqualTo("Punk IPA 2007 - 2010")
        assertThat(result.description).isEqualTo( "Our flagship beer that...")
        assertThat(result.imageUrl).isEqualTo("https://images.punkapi.com/v2/192.png")
        assertThat(result.rate).isEqualTo(6.0)
        val parameters = result.parameters
        assertThat(parameters).contains(Label(""))
        assertThat(parameters).contains(BeerTemperature("65.0 celsius", 75.0))
        assertThat(parameters).contains(BeerMalt("Extra Pale", "5.3 kilograms"))
        assertThat(parameters).contains(BeerHop("Ahtanum", "17.5 grams", "start", "bitter"))
    }
}
