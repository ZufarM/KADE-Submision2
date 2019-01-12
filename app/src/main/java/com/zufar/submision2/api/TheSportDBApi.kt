package com.zufar.submision2.api

import android.net.Uri
import com.zufar.submision2.BuildConfig

object TheSportDBApi {

    fun getLastMatch(): String{
    // https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", "4328")
            .build()
            .toString()
    }

    fun getNextMatch(): String{
        // https://www.thesportsdb.com/api/v1/json/1/eventsnext.php?id=133602
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", "4328")
            .build()
            .toString()
    }

    fun getTeamDetail(id: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=...
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }
}