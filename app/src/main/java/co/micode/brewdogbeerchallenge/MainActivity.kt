package co.micode.brewdogbeerchallenge

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import co.micode.brewdogbeerchallenge.utils.NavigateToDetails
import co.micode.brewdogbeerchallenge.viewModel.MainViewModel
import co.micode.brewdogbeerchallenge.viewModel.MainViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        startObservingNavigationItems()
    }

    private fun setupToolbar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar
            .setupWithNavController(navController, appBarConfiguration)
    }

    private fun startObservingNavigationItems() {
        viewModel.navigateActions.observe(this, {
            when (it) {
                is NavigateToDetails -> findNavController(R.id.nav_host_fragment).navigate(R.id.navigateToDetails)
            }
        })
    }
}
