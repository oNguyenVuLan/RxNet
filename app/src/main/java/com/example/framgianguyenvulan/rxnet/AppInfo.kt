package com.example.framgianguyenvulan.rxnet

import java.util.*

/**
 * Created by FRAMGIA\nguyen.vu.lan on 12/18/17.
 */
class AppInfo(var mName:String,var mIcon:String,var mLastUpdateTime:Long):Comparable<AppInfo> {
    override fun compareTo(other: AppInfo): Int {
        var appInfo:AppInfo=other
        return mName.compareTo(appInfo.mName)
    }
}