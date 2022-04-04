package com.space.conquestofspace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.space.conquestofspace.data.remote.TheSpaceDevApi
import com.space.conquestofspace.databinding.ActivityMainBinding
import com.space.core.util.HelperMethods.parseCurrentTimeToSimpleDateFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var api: TheSpaceDevApi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    override fun onResume() {
        super.onResume()

        _binding!!.button.setOnClickListener {

            lifecycleScope.launch {
                api.getLaunches(
                    parseCurrentTimeToSimpleDateFormat()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
