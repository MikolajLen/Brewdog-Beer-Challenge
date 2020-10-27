package co.micode.brewdogbeerchallenge.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideResource(application: Context) = application.resources
}
