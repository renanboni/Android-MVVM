package com.boni.neon.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.boni.neon.NeonApplication
import com.boni.neon.R
import com.boni.neon.ext.hide
import com.boni.neon.ext.show
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        private const val EMAIL = "renan.boni@usp.br"
        private const val NAME = "Renan Boni"
    }

    private lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var factory: HomeVMFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_home,
        container,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as NeonApplication).createHomeComponent()?.inject(this)
        homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        homeViewModel.authenticate(
            EMAIL,
            NAME
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        send.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }

        sendMoney.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sendMoneyFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.viewState.observe(this, Observer {
            it?.let { state -> handleState(state) }
        })

        homeViewModel.error.observe(this, Observer {
            it?.let { err ->
                Toast.makeText(activity, err.message, Toast.LENGTH_LONG).show()
                progress.hide()
            }
        })
    }

    private fun handleState(homeViewState: HomeViewState) {
        if(homeViewState.isLoading) {
            progress.show()
        } else {
            progress.hide()
        }

        homeViewState.userView?.let { user ->
            user_name.text = user.name
            email.text = user.email
        }
    }
}