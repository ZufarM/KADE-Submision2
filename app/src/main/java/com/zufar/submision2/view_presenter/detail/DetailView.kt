package com.zufar.submision2.view_presenter.detail

import com.zufar.submision2.model.Teams

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(
        homeTeam: List<Teams>,
        awayTeam: List<Teams>
    )
}