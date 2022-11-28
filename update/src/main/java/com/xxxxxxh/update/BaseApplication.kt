package com.xxxxxxh.update

import android.app.Application
import org.xutils.x

abstract class BaseApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        x.Ext.init(this)
    }

    abstract fun getAppId(): String
    abstract fun getAppName():String
    abstract fun getUrl(): String
    abstract fun getAesPassword(): String
    abstract fun getAesHex(): String
    abstract fun getToken():String

}