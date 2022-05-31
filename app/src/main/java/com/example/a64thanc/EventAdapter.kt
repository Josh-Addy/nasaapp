package com.example.a64thanc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.event.view.*


class EventAdapter(private val events: MutableList<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.event, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val temp: Event = events[position]

        holder.itemView.apply {
            tvSpeaker.text = if ( temp.speaker!= null)  temp.speaker else " "
            tvTopic.text= if ( temp.topic!= null) "Topic : " + temp.topic else " "
            tvTime.text=  if ( temp.time!= null)  "Time : " + temp.time else " "
            tvVenue.text  = if ( temp.venue!= null) "Venue : " + temp.venue else " "
            tvDate.text =  if ( temp.date!= null)"Date : " + temp.date else " "
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

}