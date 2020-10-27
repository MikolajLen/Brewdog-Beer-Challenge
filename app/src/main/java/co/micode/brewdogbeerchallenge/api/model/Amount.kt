package co.micode.brewdogbeerchallenge.api.model

data class Amount(
    val unit: String,
    val value: Double,
) {

    fun format() = "${value} ${unit}"
}
