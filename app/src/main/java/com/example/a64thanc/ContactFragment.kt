package com.example.a64thanc

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_contact.*


class ContactFragment : Fragment() {

    private lateinit var contactlist: ArrayList<Contact>
    private lateinit var db: FirebaseFirestore
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).title = "Contacts"

        rv_contactlist.layoutManager = LinearLayoutManager(activity)
        rv_contactlist.setHasFixedSize(true)
        contactlist = arrayListOf()
        contactAdapter = ContactAdapter(contactlist)
        rv_contactlist.adapter =contactAdapter

        contactChangeListner()
    }
    private fun contactChangeListner() {

        db = FirebaseFirestore.getInstance()

        db.collection("Contacts").addSnapshotListener(object : EventListener<QuerySnapshot> {

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
                        Log.d(ContentValues.TAG,"${dc.document.id} => ${dc.document.data}")
                        contactlist.add(dc.document.toObject(Contact::class.java))

                        Log.d(ContentValues.TAG,"${dc.document.id} SUCCESS")
                    }
                }
                contactAdapter.notifyDataSetChanged()
            }
        })
    }
}