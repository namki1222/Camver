package com.rightcode.camver.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rightcode.camver.R
import com.rightcode.camver.databinding.FragmentDetail1Binding
import com.rightcode.camver.databinding.FragmentDetail2Binding
import com.rightcode.camver.databinding.FragmentDetail4Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Detail4Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Detail4Fragment : BaseFragment<FragmentDetail4Binding>() {
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
        return bindView(R.layout.fragment_detail_4, inflater, container)
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
        fun newInstance(): Detail4Fragment {
            val fragment = Detail4Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}