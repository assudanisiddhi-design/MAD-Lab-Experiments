package com.example.medalert

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medalert.databinding.ActivityMedicineDashboardBinding

private const val TAG = "MedAlert"

class MedicineDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicineDashboardBinding
    private val medicineList = mutableListOf<Medicine>()
    private lateinit var adapter: MedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Dashboard Activity: onCreate()")
        binding = ActivityMedicineDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        updateEmptyState()

        binding.addMedicineFab.setOnClickListener {
            showAddMedicineFragment()
        }
    }

    private fun setupRecyclerView() {
        adapter = MedicineAdapter(medicineList) { medicine ->
            val intent = Intent(this, MedicineDetailsActivity::class.java).apply {
                putExtra("SELECTED_MEDICINE", medicine)
            }
            startActivity(intent)
        }
        binding.medicineRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.medicineRecyclerView.adapter = adapter
    }

    private fun showAddMedicineFragment() {
        val fragment = AddMedicineFragment()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
            )
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
        
        binding.addMedicineFab.visibility = View.GONE
    }

    fun onMedicineAdded(medicine: Medicine) {
        medicineList.add(medicine)
        adapter.updateData(medicineList)
        updateEmptyState()
        binding.addMedicineFab.visibility = View.VISIBLE
    }

    private fun updateEmptyState() {
        if (medicineList.isEmpty()) {
            binding.emptyStateLayout.visibility = View.VISIBLE
            binding.medicineRecyclerView.visibility = View.GONE
        } else {
            binding.emptyStateLayout.visibility = View.GONE
            binding.medicineRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Dashboard Activity: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Dashboard Activity: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Dashboard Activity: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Dashboard Activity: onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Dashboard Activity: onDestroy()")
    }
}
