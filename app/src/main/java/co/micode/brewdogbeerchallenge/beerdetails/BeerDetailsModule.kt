package co.micode.brewdogbeerchallenge.beerdetails

import android.content.res.Resources
import co.micode.brewdogbeerchallenge.api.PunkApiService
import co.micode.brewdogbeerchallenge.beerdetails.detailslist.*
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class BeerDetailsModule {

    @Provides
    fun providesBeerDetailsDataSource(
        service: PunkApiService,
        resources: Resources,
    ): BeerDetailsDataSource =
        BeerDetailsDataSourceImpl(service, resources)

    @Provides
    @IntoSet
    fun provideLabelFactory(): ViewHolderFactory<*> = LabelFactory()

    @Provides
    @IntoSet
    fun provideTemperatureFactory(): ViewHolderFactory<*> = TemperatureFactory()

    @Provides
    @IntoSet
    fun provideMaltFactory(): ViewHolderFactory<*> = MaltFactory()

    @Provides
    @IntoSet
    fun provideHopFactory(): ViewHolderFactory<*> = HopFactory()

    @Provides
    fun provideViewHolderFactoryManager(factories: Set<@JvmSuppressWildcards ViewHolderFactory<*>>) =
        ViewHolderFactoryManager(factories)
}
