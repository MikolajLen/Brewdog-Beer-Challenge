package co.micode.brewdogbeerchallenge.beerdetails.detailslist

sealed class BeerParameters(val type: Int)

data class Label(val name: String) : BeerParameters(TYPE) {

    companion object {
        const val TYPE = 0
    }
}

data class BeerTemperature(val temperature: String, val duration: Double) :
    BeerParameters(TYPE) {

    companion object {
        const val TYPE = 1
    }
}

data class BeerMalt(val name: String, val amount: String) : BeerParameters(TYPE) {

    companion object {
        const val TYPE = 2
    }
}

data class BeerHop(val name: String, val amount: String, val add: String, val attribute: String) :
    BeerParameters(TYPE) {

    companion object {
        const val TYPE = 3
    }
}
