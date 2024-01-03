package kr.co.are.searchimage.presentation

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp
import kr.co.are.searchimage.BuildConfig


@HiltAndroidApp
class SearchImageApplication :Application(){

    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    override fun onCreate() {
        super.onCreate()

        //Init Logger
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0) // (Optional) How many method line to show. Default 2
            .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
            .tag("[ARE]") // (Optional) Global tag for every log. Default PRETTY_LOGGER
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