package co.micode.brewdogbeerchallenge.beerdetails

import co.micode.brewdogbeerchallenge.beerdetails.detailslist.BeerParameters

data class BeerDetailsItem(
    val name: String,
    val rate: Double,
    val description: String,
    val imageUrl: String,
    val parameters: List<BeerParameters>,
)
