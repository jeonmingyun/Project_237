package com.coram.spy237.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentSearchResultBinding
import com.coram.spy237.db.DbOpenHelper

class SearchResultFragment : Fragment() {
    // view binding
    private var mBinding: FragmentSearchResultBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

    val BUNDLE_FLAG = "BUNDLE_FLAG"
    val BUNDLE_NAME = "BUNDLE_NAME"
    lateinit var bundleDataFlag: String
    lateinit var bundleDataName: String

    fun newInstance(flag: String, name: String): SearchResultFragment {
        val args = Bundle()
        args.putString(BUNDLE_FLAG, flag)
        args.putString(BUNDLE_NAME, name)

        val fragment = SearchResultFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentSearchResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //db
        dbHelper = DbOpenHelper(context)

        bundleDataFlag = arguments?.getString(BUNDLE_FLAG).toString()
        bundleDataName = arguments?.getString(BUNDLE_NAME).toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }
}