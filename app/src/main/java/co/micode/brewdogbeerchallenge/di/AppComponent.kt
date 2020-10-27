package co.micode.brewdogbeerchallenge.di

import android.content.Context
import co.micode.brewdogbeerchallenge.api.ApiModule
import co.micode.brewdogbeerchallenge.api.BaseUrl
import co.micode.brewdogbeerchallenge.beerdetails.BeerDetailsModule
import co.micode.brewdogbeerchallenge.beerlist.BeerListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, ActivitiesFragmentsModule::class,
    ApplicationModule::class, ApiModule::class, BeerDetailsModule::class, BeerListModule::class])
interface AppComponent {

    fun inject(app: BrewdogBeerChallengeApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        @BindsInstance
        fun baseURl(@BaseUrl baseUrl: String): Builder

        fun build(): AppComponent
    }
}
