package com.example.a64thanc

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.model.Document
import kotlinx.android.synthetic.main.fragment_event.*

class EventFragment(private val type: String): Fragment() {

    private lateinit var eventlist: ArrayList<Event>
    private lateinit var db: FirebaseFirestore
    private lateinit var eventAdapter: EventAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_event, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).title = type

        rv_eventlist.layoutManager = LinearLayoutManager(activity)
        rv_eventlist.setHasFixedSize(true)
        eventlist = arrayListOf()
        eventAdapter = EventAdapter(eventlist)
        rv_eventlist.adapter = eventAdapter

        eventChangeListner()
    }
    private fun eventChangeListner() {

        db = FirebaseFirestore.getInstance()




        db.collection(type).addSnapshotListener(object : EventListener<QuerySnapshot> {

            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {
                    Log.e("FireStore Error", error.message.toString())
                    return
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        Log.d(TAG,"${dc.document.id} => ${dc.document.data}")
                        eventlist.add(dc.document.toObject(Event::class.java))
                    }
                }
                eventAdapter.notifyDataSetChanged()
            }
        })
    }

}