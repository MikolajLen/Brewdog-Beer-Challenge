package co.micode.brewdogbeerchallenge.beerlist

import co.micode.brewdogbeerchallenge.api.model.PunkApiResponseItem

data class BeerListItem(val id: Int, val name: String, val abv: Double, val imageUrl: String) {

    companion object {

        fun generateFrom(item: PunkApiResponseItem) =
            BeerListItem(id = item.id, name = item.name, abv = item.abv, imageUrl = item.image_url)
    }
}
