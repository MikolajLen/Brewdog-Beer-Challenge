package co.micode.brewdogbeerchallenge.beerdetails.detailslist

import android.view.LayoutInflater
import android.view.ViewGroup

class ViewHolderFactoryManager(private val factories: Set<ViewHolderFactory<*>>) {

    fun createViewHolderForSelectedType(parent: ViewGroup, viewType: Int) =
        factories.first { it.canHandle(viewType) }
            .create(LayoutInflater.from(parent.context), parent)
}
