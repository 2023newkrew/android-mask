package com.survivalcoding.maskinfo

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.survivalcoding.maskinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val recyclerAdapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter

        viewModel.infoListLiveData.observe(this){ infoList ->
            title = "마스크 재고 있는 곳: ${infoList.size}"
        }

        // TODO delete
        viewModel.loadInfoList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
        inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
            val addressTextView: TextView = itemView.findViewById(R.id.address_text_view)
            val distanceTextView: TextView = itemView.findViewById(R.id.distance_text_view)
            val enoughTextView: TextView = itemView.findViewById(R.id.enough_text_view)
            val stockTextView: TextView = itemView.findViewById(R.id.stock_text_view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_info, parent, false))
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
            holder.nameTextView.text = viewModel.infoList[position].name
            holder.addressTextView.text = viewModel.infoList[position].address
            holder.distanceTextView.text = "${viewModel.infoList[position].distance}km"
            when (viewModel.infoList[position].stock) {
                in 0 until 30 -> {
                    holder.enoughTextView.text = "소진 임박"
                    holder.stockTextView.text = "30개 미만"
                    holder.enoughTextView.setTextColor(Color.RED)
                    holder.stockTextView.setTextColor(Color.RED)
                }
                in 30 until 100 -> {
                    holder.enoughTextView.text = "여유"
                    holder.stockTextView.text = "30개 이상"
                    holder.enoughTextView.setTextColor(Color.YELLOW)
                    holder.stockTextView.setTextColor(Color.YELLOW)
                }
                else -> {
                    holder.enoughTextView.text = "충분"
                    holder.stockTextView.text = "100개 이상"
                    holder.enoughTextView.setTextColor(Color.GREEN)
                    holder.stockTextView.setTextColor(Color.GREEN)
                }
            }
        }

        override fun getItemCount(): Int {
            return viewModel.infoList.size
        }
    }
}