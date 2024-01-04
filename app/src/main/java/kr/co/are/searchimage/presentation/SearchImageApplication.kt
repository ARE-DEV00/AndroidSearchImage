package kr.co.are.searchimage.presentation

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp
import kr.co.are.searchimage.BuildConfig
import kr.co.are.searchimage.R


@HiltAndroidApp
class SearchImageApplication : Application() {

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    override fun onCreate() {
        super.onCreate()

        //Init Logger
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag(getString(R.string.logger))
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        //Init Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)


    }

}