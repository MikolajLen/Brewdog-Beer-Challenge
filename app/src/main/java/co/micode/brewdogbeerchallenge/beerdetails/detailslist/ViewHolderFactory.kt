package co.micode.brewdogbeerchallenge.beerdetails.detailslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.micode.brewdogbeerchallenge.R

abstract class ViewHolderFactory<T : BeerParameters> {

    abstract fun canHandle(type: Int): Boolean

    abstract fun getLayoutId(): Int

    abstract fun createHolderForView(view: View): DetailsListViewHolder<T>

    fun create(layoutInflater: LayoutInflater, parent: ViewGroup) =
        createHolderForView(layoutInflater.inflate(getLayoutId(), parent, false))
}

class LabelFactory : ViewHolderFactory<Label>() {

    override fun canHandle(type: Int) = type == Label.TYPE

    override fun getLayoutId() = R.layout.list_item_label

    override fun createHolderForView(view: View) = LabeListHolder(view)
}

class TemperatureFactory : ViewHolderFactory<BeerTemperature>() {

    override fun canHandle(type: Int) = type == BeerTemperature.TYPE

    override fun getLayoutId() = R.layout.list_item_temperature

    override fun createHolderForView(view: View) = TemperatureListHolder(view)
}

class MaltFactory : ViewHolderFactory<BeerMalt>() {

    override fun canHandle(type: Int) = type == BeerMalt.TYPE

    override fun getLayoutId() = R.layout.list_item_malt

    override fun createHolderForView(view: View) = MaltListHolder(view)
}

class HopFactory : ViewHolderFactory<BeerHop>() {

    override fun canHandle(type: Int) = type == BeerHop.TYPE

    override fun getLayoutId() = R.layout.list_item_hop

    override fun createHolderForView(view: View) = HopListHolder(view)
}
