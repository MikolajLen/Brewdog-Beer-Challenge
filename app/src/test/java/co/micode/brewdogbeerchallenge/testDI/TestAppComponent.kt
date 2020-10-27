package co.micode.brewdogbeerchallenge.testDI

import co.micode.brewdogbeerchallenge.MainActivityTest
import co.micode.brewdogbeerchallenge.beerdetails.DetailsFragmentTest
import co.micode.brewdogbeerchallenge.beerlist.BeerListFragmentTest
import co.micode.brewdogbeerchallenge.di.ActivitiesFragmentsModule
import co.micode.brewdogbeerchallenge.di.BrewdogBeerChallengeApp
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivitiesFragmentsModule::class, TestModule::class])
interface TestAppComponent {
    fun inject(application: BrewdogBeerChallengeApp)
    fun inject(test: DetailsFragmentTest)
    fun inject(test: BeerListFragmentTest)
    fun inject(test: MainActivityTest)
}
