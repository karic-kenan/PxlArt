package io.aethibo.pxlart.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pandora.bottomnavigator.BottomNavigator
import io.aethibo.pxlart.R
import io.aethibo.pxlart.ui.curated.view.CuratedFragment
import io.aethibo.pxlart.ui.profile.view.ProfileFragment
import io.aethibo.pxlart.ui.search.view.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var navigator: BottomNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PxlArt)
        setContentView(R.layout.activity_main)

        navigator = BottomNavigator.onCreate(
                fragmentContainer = R.id.fragment_container,
                bottomNavigationView = findViewById(R.id.navigation),
                rootFragmentsFactory = mapOf(
                        R.id.home to { CuratedFragment.newInstance() },
                        R.id.search to { SearchFragment.newInstance() },
                        R.id.profile to { ProfileFragment.newInstance() }
                ),
                defaultTab = R.id.home,
                activity = this
        )
    }

    override fun onBackPressed() {
        if (!navigator.pop()) {
            super.onBackPressed()
        }
    }
}