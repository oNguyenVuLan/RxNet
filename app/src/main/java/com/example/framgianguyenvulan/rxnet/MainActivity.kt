package com.example.framgianguyenvulan.rxnet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun getApps(): Observable<AppInfo> {
    return Observable.create { e ->
        var apps: MutableList<AppInfo> = ArrayList()
        var mainIntent=Intent(Intent.ACTION_MAIN,null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        var infors=this.packageManager.queryIntentActivities(mainIntent,0)
        for(infor in infors){
            apps.add(AppInfo())
        }
    }
    }
}
