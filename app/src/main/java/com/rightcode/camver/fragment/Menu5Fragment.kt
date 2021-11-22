package com.rightcode.camver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rightcode.camver.R
import com.rightcode.camver.databinding.FragmentMenu5Binding

/**
 * A simple [Fragment] subclass.
 * Use the [Menu5Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Menu5Fragment : BaseFragment<FragmentMenu5Binding>() {
    private var param1: String? = null
    private var param2: String? = null
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(Menu5Fragment.ARG_PARAM1)
            param2 = it.getString(Menu5Fragment.ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return bindView(R.layout.fragment_menu_5, inflater, container)
    }

    override fun initBinding() {}
    override fun initFragment() {}
    override fun initClickListener() {}

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        fun newInstance(): Menu5Fragment {
            val fragment = Menu5Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}