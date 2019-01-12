package com.zufar.submision2.view_presenter.detail

import com.google.gson.Gson
import com.zufar.submision2.api.ApiRepository
import com.zufar.submision2.api.TheSportDBApi
import com.zufar.submision2.model.TeamsAwayResponse
import com.zufar.submision2.model.TeamsHomeResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson
) {

    fun getTeamList(idHome: String?, idAway: String?){
        view.showLoading()
        doAsync {
            val homeList = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idHome)),
                TeamsHomeResponse::class.java
            )

            val awayList = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(idAway)),
                TeamsAwayResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(homeList.teams,awayList.teams)
            }
        }
    }

}