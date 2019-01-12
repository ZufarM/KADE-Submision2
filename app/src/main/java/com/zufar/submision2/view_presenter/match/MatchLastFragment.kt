package com.zufar.submision2.view_presenter.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson

import com.zufar.submision2.adapter.MatchAdapter
import com.zufar.submision2.api.ApiRepository
import com.zufar.submision2.model.Match
import com.zufar.submision2.util.invisible
import com.zufar.submision2.util.visible
import com.zufar.submision2.view_presenter.detail.DetailActivity

import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), MatchView {

    private var events: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {

                    relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                        listMatch = recyclerView {
                            lparams (width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {
                        }.lparams{
                            centerHorizontally()
                        }
                    }
                }
            }

            adapter = MatchAdapter(events){
                startActivity<DetailActivity>(
                    // Event
                    "name" to "${it.strEvent}",
                    "date" to "${it.dateEvent}",

                    // home Team
                    "homeId" to "${it.idHomeTeam}",
                    "homeTeam" to "${it.strHomeTeam}",
                    "homeScore" to "${it.intHomeScore}",
                    //linesup
                    "HomeGoalDetails" to "${it.strHomeGoalDetails}",
                    "HomeLineupGoalkeeper" to "${it.strHomeLineupGoalkeeper}",
                    "HomeLineupDefense" to "${it.strHomeLineupDefense}",
                    "HomeLineupMidfield" to "${it.strHomeLineupMidfield}",
                    "HomeLineupForward" to "${it.strHomeLineupForward}",
                    "HomeLineupSubstitutes" to "${it.strHomeLineupSubstitutes}",

                    // away Team
                    "awayId" to "${it.idAwayTeam}",
                    "awayTeam" to "${it.strAwayTeam}",
                    "awayScore" to "${it.intAwayScore}",
                    //linesup
                    "AwayGoalDetails" to "${it.strAwayGoalDetails}",
                    "AwayLineupGoalkeeper" to "${it.strAwayLineupGoalkeeper}",
                    "AwayLineupDefense" to "${it.strAwayLineupDefense}",
                    "AwayLineupMidfield" to "${it.strAwayLineupMidfield}",
                    "AwayLineupForward" to "${it.strAwayLineupForward}",
                    "AwayLineupSubstitutes" to "${it.strAwayLineupSubstitutes}"
                )
            }
            listMatch.adapter = adapter

            val request = ApiRepository()
            val gson = Gson()
            presenter = MatchPresenter(this@LastMatchFragment, request, gson)
            presenter.getLastMatchEvent()

            // Dibagian swipe refresh misal digunakan muncl error
            // "e: java.lang.IllegalStateException: Backend Internal error: Exception during code generation"
            swipeRefresh.onRefresh {
                presenter.getLastMatchEvent()
            }
        }.view
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): LastMatchFragment {
            val fragment = LastMatchFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
