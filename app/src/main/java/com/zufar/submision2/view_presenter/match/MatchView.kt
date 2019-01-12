package com.zufar.submision2.view_presenter.match

import com.zufar.submision2.model.Match


interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Match>)
}