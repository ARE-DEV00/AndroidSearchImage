package kr.co.are.searchimage.presentation

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.HiltAndroidApp
import kr.co.are.searchimage.BuildConfig
import kr.co.are.searchimage.presentation.utils.AppTimberTree
import timber.log.Timber


@HiltAndroidApp
class SearchImageApplication : Application() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    override fun onCreate() {
        super.onCreate()

        //Init Logger
        if (BuildConfig.DEBUG) {
            Timber.plant(AppTimberTree())
        }

        //Init Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)


    }

}