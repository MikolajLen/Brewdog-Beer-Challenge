package co.micode.brewdogbeerchallenge.beerdetails

interface BeerDetailsDataSource {

    suspend fun loadBeerDetails(id: Int): BeerDetailsItem
}
