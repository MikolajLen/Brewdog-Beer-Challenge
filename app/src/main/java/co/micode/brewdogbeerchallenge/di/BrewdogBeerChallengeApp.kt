package co.micode.brewdogbeerchallenge.di

import android.app.Application
import co.micode.brewdogbeerchallenge.BuildConfig
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BrewdogBeerChallengeApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .baseURl(BuildConfig.SERVER_URL)
            .build()
            .inject(this)
    }

    override fun androidInjector() = activityInjector
}
