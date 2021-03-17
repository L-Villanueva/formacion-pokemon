package com.example.pokemon.home_activity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.commons.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : BaseFragment() {

    private val presenter: ProfileViewModel by sharedViewModel()

    override fun loadObservers() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        presenter.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}