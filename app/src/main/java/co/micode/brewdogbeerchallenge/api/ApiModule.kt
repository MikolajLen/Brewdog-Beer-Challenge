package co.micode.brewdogbeerchallenge.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideApiConfiguration(@BaseUrl serverUrl: String) =
        ApiConfiguration().provideConfiguration(serverUrl)

    @Provides
    fun providePunkApiService(configuration: Retrofit) =
        configuration.create(PunkApiService::class.java)
}
