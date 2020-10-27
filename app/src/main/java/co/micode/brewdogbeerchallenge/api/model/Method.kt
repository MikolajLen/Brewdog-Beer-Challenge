package co.micode.brewdogbeerchallenge.api.model

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>
)
