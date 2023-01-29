package com.survivalcoding.maskinfo.presentation.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.survivalcoding.maskinfo.R
import com.survivalcoding.maskinfo.databinding.ActivityMainBinding
import com.survivalcoding.maskinfo.di.MyApplication
import com.survivalcoding.maskinfo.presentation.adapter.MaskStockAdapter
import com.survivalcoding.maskinfo.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    @Inject
    lateinit var viewModel: MainViewModel

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            this
        )
    }

    private val load_data: () -> Unit = {
        loadMyLocation()
        viewModel.load()
    }

    override fun onStart() {
        super.onStart()
        locationPermissionRequest(load_data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        load_data()

        val maskStockRecyclerView = binding.maskStockRecyclerView
        val maskStockAdapter = MaskStockAdapter()
        maskStockRecyclerView.adapter = maskStockAdapter


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainUiState.collectLatest {
                    maskStockAdapter.submitList(it.maskStocks.toMutableList()) {
                        binding.toolbar.title =
                            getString(R.string.title, maskStockAdapter.itemCount)
                    }
                }
            }
        }

        binding.toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.menu_search) load_data()
            true
        }
    }

    private fun loadMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                viewModel.myLat = it.latitude.toFloat()
                viewModel.myLng = it.longitude.toFloat()
            }
        }
    }

    private fun locationPermissionRequest(job: () -> Unit) {
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                        permissions.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            false
                        ) -> job()
                else -> {
                    // No location access granted.
                }
            }
        }.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

}