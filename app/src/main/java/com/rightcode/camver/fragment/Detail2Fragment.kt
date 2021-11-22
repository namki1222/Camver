package com.rightcode.camver.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rightcode.camver.R
import com.rightcode.camver.databinding.FragmentDetail1Binding
import com.rightcode.camver.databinding.FragmentDetail2Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Detail2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Detail2Fragment : BaseFragment<FragmentDetail2Binding>() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return bindView(R.layout.fragment_detail_2, inflater, container)
    }

    override fun initBinding() {}
    override fun initFragment() {
    }

    override fun initClickListener() {
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(): Detail2Fragment {
            val fragment = Detail2Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}