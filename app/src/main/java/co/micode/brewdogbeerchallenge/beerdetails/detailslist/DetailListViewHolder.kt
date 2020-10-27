package co.micode.brewdogbeerchallenge.beerdetails.detailslist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import co.micode.brewdogbeerchallenge.utils.format
import kotlinx.android.synthetic.main.list_item_hop.view.*
import kotlinx.android.synthetic.main.list_item_label.view.*
import kotlinx.android.synthetic.main.list_item_malt.view.amount
import kotlinx.android.synthetic.main.list_item_malt.view.name
import kotlinx.android.synthetic.main.list_item_temperature.view.*

abstract class DetailsListViewHolder<T : BeerParameters>(view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(parameter: BeerParameters) {
        setData(parameter as T)
    }

    protected abstract fun setData(parameter: T)
}

class LabeListHolder(view: View) : DetailsListViewHolder<Label>(view) {

    val name = view.label

    override fun setData(parameter: Label) {
        name.text = parameter.name
    }
}

class TemperatureListHolder(view: View) : DetailsListViewHolder<BeerTemperature>(view) {

    val temperature = view.temperature
    val duration = view.duration

    override fun setData(parameter: BeerTemperature) {
        temperature.text = parameter.temperature
        duration.text = parameter.duration.format(1)
    }
}

class MaltListHolder(view: View) : DetailsListViewHolder<BeerMalt>(view) {

    val name = view.name
    val malt = view.amount

    override fun setData(parameter: BeerMalt) {
        name.text = parameter.name
        malt.text = parameter.amount
    }
}

class HopListHolder(view: View) : DetailsListViewHolder<BeerHop>(view) {

    val name = view.name
    val malt = view.amount
    val add = view.add
    val attribute = view.attribute

    override fun setData(parameter: BeerHop) {
        name.text = parameter.name
        malt.text = parameter.amount
        add.text = parameter.add
        attribute.text = parameter.attribute
    }
}

