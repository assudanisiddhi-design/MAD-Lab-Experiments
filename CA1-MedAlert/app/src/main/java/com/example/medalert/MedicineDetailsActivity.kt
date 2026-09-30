package com.example.medalert

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.medalert.databinding.ActivityMedicineDetailsBinding

private const val TAG = "MedAlert"

class MedicineDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicineDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "MedicineDetailsActivity: onCreate()")
        binding = ActivityMedicineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val medicine = intent.getParcelableExtra<Medicine>("SELECTED_MEDICINE")

        medicine?.let {
            binding.tvDetailName.text = it.name
            binding.tvDetailDosage.text = it.dosage
            binding.tvDetailType.text = it.type
            binding.tvDetailFrequency.text = it.frequency
            binding.tvDetailReminder.text = if (it.reminderRequired) "Enabled" else "Disabled"
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "MedicineDetailsActivity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "MedicineDetailsActivity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MedicineDetailsActivity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MedicineDetailsActivity: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MedicineDetailsActivity: onDestroy()")
    }
}
