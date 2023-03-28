package com.doda.mdnevnik.ispiti

import android.view.View
import android.widget.TextView
import com.doda.mdnevnik.R
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view: View): ViewContainer(view){
    val textView = view.findViewById<TextView>(R.id.calendar_day_text)


}