package com.dapoi.memeapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dapoi.memeapp.adapter.MemeAdapter
import com.dapoi.memeapp.databinding.ActivityMainBinding
import com.dapoi.memeapp.viewmodel.MemeViewModel

class MainActivity : AppCompatActivity() {

    /**
     * kenalin variabelnya dulu ya:
     *
     * binding buat hubugnin class ini sama xmlnya
     * viewModel buat hubungin ke viewmodel
     * adapter buat hubungin ke adapter
     *
     */

    private lateinit var binding: ActivityMainBinding
    private lateinit var memeAdapter: MemeAdapter
    private lateinit var memeViewModel: MemeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getViewModel()
        getAdapter()
    }

    private fun getAdapter() {
        // abis dikenalin, jangan lupa dipanggil lagi adapternya
        memeAdapter = MemeAdapter()

        /**
         *
         * nah ini kita panggil id yang ada di acitivy_main.xml, coba kamu pencet
         * ctrl + click di rvMemeList
         *
         */
        binding.rvMemeList.layoutManager = LinearLayoutManager(this)
        binding.rvMemeList.adapter = memeAdapter
        binding.rvMemeList.setHasFixedSize(true)
    }

    private fun getViewModel() {
        // abis dikenalin, jangan lupa dipanggil lagi viewmodelnya
        memeViewModel = ViewModelProvider(this).get(MemeViewModel::class.java)

        // kita panggil fungsi view modelnya yang ada di viewmodel
        memeViewModel.getMeme()

        binding.loading.visibility = View.VISIBLE
        // terakhir, kita panggil variabel yang non private, sekaligus ilangin loadingnya
        memeViewModel.dataMeme.observe(this) {
            binding.loading.visibility = View.GONE
            memeAdapter.setData(it)
        }
    }
}