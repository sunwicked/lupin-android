package app.com.application.main

import app.com.application.model.Beer

object Utils {

    fun sort(beers: MutableList<Beer>, ascending: Boolean) {
        beers.sortedBy { beer -> beer.getBeerAlcohol() }
        if (ascending)
        {
            beers.run { reverse() }
        }



    }
}