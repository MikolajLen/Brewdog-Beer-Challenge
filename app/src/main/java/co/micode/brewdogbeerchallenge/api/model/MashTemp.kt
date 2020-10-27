package co.micode.brewdogbeerchallenge.api.model

data class MashTemp(
    val duration: Double,
    val temp: Temp,
) {

    fun format() = "${temp.value} ${temp.unit}"
}
