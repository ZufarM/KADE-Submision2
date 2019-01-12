package com.zufar.submision2.view_presenter.detail

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.zufar.submision2.R
import com.zufar.submision2.api.ApiRepository
import com.zufar.submision2.model.Teams
import kotlinx.android.synthetic.main.notification_template_lines_media.view.*
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private var home: MutableList<Teams> = mutableListOf()
    private var away: MutableList<Teams> = mutableListOf()

    // Event
    private var nameEvent: String = ""
    lateinit var dateEvent: TextView

    // home team
    lateinit var imageHome: ImageView
    lateinit var teamHome: TextView
    lateinit var scoreHome: TextView
    lateinit var goalsHome: TextView
    // linesup
    lateinit var goalskeeperHome: TextView
    lateinit var defenseHome: TextView
    lateinit var midfieldHome: TextView
    lateinit var forwardHome: TextView
    lateinit var substitutesHome: TextView

    // away team
    lateinit var imageAway: ImageView
    lateinit var teamAway: TextView
    lateinit var scoreAway: TextView
    lateinit var goalsAway: TextView
    // linesup
    lateinit var goalskeeperAway: TextView
    lateinit var defenseAway: TextView
    lateinit var midfieldAway: TextView
    lateinit var forwardAway: TextView
    lateinit var substitutesAway: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)

        scrollView {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(8)
                gravity = Gravity.CENTER_VERTICAL

                linearLayout {
                    gravity = Gravity.CENTER
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_VERTICAL
                        // Home

                        imageHome = imageView {
                            padding = dip(16)
                        }.lparams(width = 250, height = 250)

                        teamHome = textView {
                            gravity = Gravity.CENTER
                            textSize = 22f
                            text = " Home "
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            setTypeface(null, Typeface.BOLD)
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        linearLayout {
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER_VERTICAL
                            scoreHome = textView {
                                textSize = 30f
                                text = " 0 "
                                textAlignment = right
                                setTypeface(null, Typeface.BOLD)
                            }
                            textView {
                                text = " vs "
                                textSize = 19f
                                setTypeface(null, Typeface.ITALIC)
                            }
                            scoreAway = textView {
                                textSize = 30f
                                text = " 0 "
                                setTypeface(null, Typeface.BOLD)
                            }
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_VERTICAL
                        // Away
                        imageAway = imageView {
                            padding = dip(16)
                        }.lparams(width = 250, height = 250)
                        teamAway = textView {
                            gravity = Gravity.CENTER
                            text = " Away "
                            textSize = 22f
                            textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            setTypeface(null, Typeface.BOLD)
                        }

                    }
                }

                dateEvent = textView {
                    gravity = Gravity.CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                }.lparams(width = matchParent, height = wrapContent)

                // goals
                textView {
                    topPadding = dip(4)
                    gravity = Gravity.CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    text = " goals "
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = matchParent, height = wrapContent)
                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Home
                        lparams(width = 250, height = wrapContent)

                        goalsHome = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        leftPadding = dip(8)
                        rightPadding = dip(8)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Away
                        lparams(width = 250, height = wrapContent)

                        goalsAway = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }

                // goals keeper
                textView {
                    topPadding = dip(4)
                    gravity = Gravity.CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    text = " goals keeper "
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = matchParent, height = wrapContent)
                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Home
                        lparams(width = 250, height = wrapContent)

                        goalskeeperHome = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        leftPadding = dip(8)
                        rightPadding = dip(8)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Away
                        lparams(width = 250, height = wrapContent)

                        goalskeeperAway = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }
                // defense
                textView {
                    topPadding = dip(4)
                    gravity = Gravity.CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    text = " defense "
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = matchParent, height = wrapContent)
                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Home
                        lparams(width = 250, height = wrapContent)

                        defenseHome = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        leftPadding = dip(8)
                        rightPadding = dip(8)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Away
                        lparams(width = 250, height = wrapContent)

                        defenseAway = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }
                // midfield
                textView {
                    topPadding = dip(4)
                    gravity = Gravity.CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    text = " midfield "
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = matchParent, height = wrapContent)
                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Home
                        lparams(width = 250, height = wrapContent)

                        midfieldHome = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        leftPadding = dip(8)
                        rightPadding = dip(8)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Away
                        lparams(width = 250, height = wrapContent)

                        midfieldAway = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }
                // Forward
                textView {
                    topPadding = dip(4)
                    gravity = Gravity.CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    text = " forward "
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = matchParent, height = wrapContent)
                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Home
                        lparams(width = 250, height = wrapContent)

                        forwardHome = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        leftPadding = dip(8)
                        rightPadding = dip(8)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Away
                        lparams(width = 250, height = wrapContent)

                        forwardAway = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }
                // Substitutes
                textView {
                    topPadding = dip(4)
                    gravity = Gravity.CENTER
                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    text = " substitutes "
                    setTypeface(null, Typeface.BOLD)
                }.lparams(width = matchParent, height = wrapContent)
                linearLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    orientation = LinearLayout.HORIZONTAL

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Home
                        lparams(width = 250, height = wrapContent)

                        substitutesHome = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER
                        leftPadding = dip(8)
                        rightPadding = dip(8)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL
                        // Away
                        lparams(width = 250, height = wrapContent)

                        substitutesAway = textView {
                            textSize = 14f
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                    }
                }

            }

        }

        val intent = intent
        nameEvent = intent.getStringExtra("name")
        dateEvent.text = intent.getStringExtra("date")

        // Home
        val homeId: String = intent.getStringExtra("homeId")
        teamHome.text = intent.getStringExtra("homeTeam")
        scoreHome.text = intent.getStringExtra("homeScore")
        goalsHome.text = intent.getStringExtra("HomeGoalDetails")
        // Linesup
        goalskeeperHome.text = intent.getStringExtra("HomeLineupGoalkeeper")
        defenseHome.text = intent.getStringExtra("HomeLineupDefense")
        midfieldHome.text = intent.getStringExtra("HomeLineupMidfield")
        forwardHome.text = intent.getStringExtra("HomeLineupForward")
        substitutesHome.text = intent.getStringExtra("HomeLineupSubstitutes")

        // Away
        val awayId: String = intent.getStringExtra("awayId")
        teamAway.text = intent.getStringExtra("awayTeam")
        scoreAway.text = intent.getStringExtra("awayScore")
        goalsAway.text = intent.getStringExtra("AwayGoalDetails")
        // Linesup
        goalskeeperAway.text = intent.getStringExtra("AwayLineupGoalkeeper")
        defenseAway.text = intent.getStringExtra("AwayLineupDefense")
        midfieldAway.text = intent.getStringExtra("AwayLineupMidfield")
        forwardAway.text = intent.getStringExtra("AwayLineupForward")
        substitutesAway.text = intent.getStringExtra("AwayLineupSubstitutes")

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this,request,gson)
        presenter.getTeamList(homeId,awayId)

        setTitle(nameEvent)
//        var mToolbar = findViewById(R.id.toolbar) as Toolbar?
//        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_tab, menu)
        return true
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamList(homeTeam: List<Teams>, awayTeam: List<Teams>) {
        home.clear()
        home.addAll(homeTeam)
        Picasso.get()
            .load(homeTeam[0].strTeamBadge).into(imageHome)

        away.clear()
        away.addAll(awayTeam)
        Picasso.get()
            .load(awayTeam[0].strTeamBadge).into(imageAway)
    }
}
