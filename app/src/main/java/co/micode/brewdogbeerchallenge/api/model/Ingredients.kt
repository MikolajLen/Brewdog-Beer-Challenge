package co.micode.brewdogbeerchallenge.api.model

data class Ingredients(
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)
