package co.micode.brewdogbeerchallenge.di

import co.micode.brewdogbeerchallenge.MainActivity
import co.micode.brewdogbeerchallenge.beerdetails.DetailsFragment
import co.micode.brewdogbeerchallenge.beerlist.BeerListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesFragmentsModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindBeerListFragment(): BeerListFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment
}
