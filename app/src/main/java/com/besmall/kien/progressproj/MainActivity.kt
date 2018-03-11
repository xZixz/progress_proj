package com.besmall.kien.progressproj

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.besmall.kien.progressproj.adapter.TargetAdapter
import com.besmall.kien.progressproj.viewmodel.AppViewModel
import java.util.*
import kotlinx.android.synthetic.main.activity_main.targets_recycler_view as targetsRecyclerView
import kotlinx.android.synthetic.main.activity_main.add_button as addButton
import kotlinx.android.synthetic.main.activity_main.remove_button as removeButton
import kotlinx.android.synthetic.main.activity_main.targets_count as targetsCount

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel

    companion object {
        private val NAMES = listOf("Kien", "Ha", "Phuc", "Nhan", "Trinh")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val targetsAdapter = TargetAdapter(listOf())
        val recyclerViewLayoutManager = LinearLayoutManager(this)
        targetsRecyclerView.apply {
            adapter = targetsAdapter
            layoutManager = recyclerViewLayoutManager
        }

        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)
        viewModel.targetModels.observe(this, Observer {
            it?.let { list -> targetsAdapter.addItems(list) }
        })

        viewModel.targetsCount.observe(this, Observer {
            targetsCount.text = "$it"
        })

        addButton.setOnClickListener {
            viewModel.addTarget(getRandomName())
        }

        removeButton.setOnClickListener {
            viewModel.removeLastTarget()
        }
    }

    private fun getRandomName(): String {
        return NAMES[Random().nextInt(NAMES.size)]
    }
}
