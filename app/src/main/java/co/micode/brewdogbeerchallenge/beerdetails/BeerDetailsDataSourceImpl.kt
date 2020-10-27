package co.micode.brewdogbeerchallenge.beerdetails

import android.content.res.Resources
import co.micode.brewdogbeerchallenge.R
import co.micode.brewdogbeerchallenge.api.PunkApiService
import co.micode.brewdogbeerchallenge.api.model.PunkApiResponseItem
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.*

class BeerDetailsDataSourceImpl(
    private val service: PunkApiService,
    private val resources: Resources,
) : BeerDetailsDataSource {

    override suspend fun loadBeerDetails(id: Int) = getBeerDetailItem(id).run {
        BeerDetailsItem(
            name = name,
            rate = abv,
            description = description,
            imageUrl = image_url,
            parameters = getParametersFromDetailItem(this))
    }

    private suspend fun getBeerDetailItem(id: Int) = service.fetchSingleBeer(id).first()

    private fun getParametersFromDetailItem(beerDetailsItem: PunkApiResponseItem) =
        arrayListOf<BeerParameters>().apply {
            add(Label(resources.getString(R.string.temperature)))
            addAll(getTemperatures(beerDetailsItem))
            add(Label(resources.getString(R.string.malts)))
            addAll(getMalts(beerDetailsItem))
            add(Label(resources.getString(R.string.hops)))
            addAll(getHops(beerDetailsItem))
        }

    private fun getTemperatures(beerDetailsItem: PunkApiResponseItem): List<BeerTemperature> {
        return beerDetailsItem.method.mash_temp.map {
            BeerTemperature(
                temperature = it.format(),
                duration = it.duration)
        }
    }

    private fun getMalts(beerDetailsItem: PunkApiResponseItem): List<BeerMalt> {
        return beerDetailsItem.ingredients.malt.map {
            BeerMalt(name = it.name, amount = it.amount.format())
        }
    }

    private fun getHops(beerDetailsItem: PunkApiResponseItem): List<BeerHop> {
        return beerDetailsItem.ingredients.hops.map {
            BeerHop(name = it.name,
                amount = it.amount.format(),
                add = it.add,
                attribute = it.attribute)
        }
    }
}
