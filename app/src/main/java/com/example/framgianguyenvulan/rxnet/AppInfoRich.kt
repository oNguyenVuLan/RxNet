package com.example.framgianguyenvulan.rxnet

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import java.util.*

/**
 * Created by FRAMGIA\nguyen.vu.lan on 12/18/17.
 */
class AppInfoRich : Comparable<AppInfoRich> {
    var mContext: Context? = null
    var mComponent: ComponentName? = null
    var mPackageInfo: PackageInfo? = null
    var mIcon: Drawable? = null
    var mName: String? = null
    var mResolveInfo: ResolveInfo? = null

    constructor(context: Context, resolveInfo: ResolveInfo) {
        mContext = context
        mResolveInfo = resolveInfo
        mComponent = ComponentName(resolveInfo.activityInfo.applicationInfo.packageName,
                resolveInfo.activityInfo.name)
        mPackageInfo = context.packageManager.getPackageInfo(mResolveInfo!!.activityInfo.packageName, 0)
    }

    override fun compareTo(other: AppInfoRich): Int {

        return mName!!.compareTo(other.mName!!)
    }

    @Throws(PackageManager.NameNotFoundException::class)
    fun getNameFromResolveInfo(ri: ResolveInfo): String {
        var name: String? = ri.resolvePackageName
        if (ri.activityInfo != null) {
            val res = mContext.getPackageManager().getResourcesForApplication(ri.activityInfo.applicationInfo)
            val engRes = getEnglishRessources(res)

            if (ri.activityInfo.labelRes != 0) {
                name = engRes.getString(ri.activityInfo.labelRes)

                if (name == null || name == "") {
                    name = res.getString(ri.activityInfo.labelRes)
                }
            } else {
                name = ri.activityInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString()
            }
        }
        return name!!
    }

    fun getEnglishRessources(standardResources: Resources): Resources {
        val assets = standardResources.assets
        val metrics = standardResources.displayMetrics
        val config = Configuration(standardResources.configuration)
        config.locale = Locale.US
        return Resources(assets, metrics, config)
    }
}