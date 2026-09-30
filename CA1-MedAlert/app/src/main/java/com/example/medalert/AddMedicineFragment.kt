package com.example.medalert

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medalert.databinding.FragmentAddMedicineBinding

private const val TAG = "MedAlert"

class AddMedicineFragment : Fragment() {
    private var _binding: FragmentAddMedicineBinding? = null
    private val binding get() = _binding!!
    private lateinit var notificationHelper: NotificationHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "AddMedicineFragment: onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "AddMedicineFragment: onCreate()")
        notificationHelper = NotificationHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "AddMedicineFragment: onCreateView()")
        _binding = FragmentAddMedicineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "AddMedicineFragment: onViewCreated()")

        binding.btnSaveMedicine.setOnClickListener {
            validateAndSave()
        }

        binding.btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
            (activity as? MedicineDashboardActivity)?.onMedicineAdded(
                // Just to restore FAB visibility
                Medicine("", "", "", "", false)
            ).also {
                // Remove the dummy if it's just for FAB
            }
            // Better approach: Popping and direct callback
            activity?.findViewById<View>(R.id.add_medicine_fab)?.visibility = View.VISIBLE
        }
    }

    private fun validateAndSave() {
        val name = binding.editMedicineName.text.toString().trim()
        val dosage = binding.editDosage.text.toString().trim()
        
        val typeId = binding.radioGroupType.checkedRadioButtonId
        val frequencyId = binding.radioGroupFrequency.checkedRadioButtonId
        
        if (name.isEmpty()) {
            showToast("Please enter medicine name")
            return
        }
        if (dosage.isEmpty()) {
            showToast("Please enter dosage")
            return
        }
        if (typeId == -1) {
            showToast("Please select medicine type")
            return
        }
        if (frequencyId == -1) {
            showToast("Please select frequency")
            return
        }

        val type = view?.findViewById<RadioButton>(typeId)?.text.toString()
        val frequency = view?.findViewById<RadioButton>(frequencyId)?.text.toString()
        val isReminder = binding.checkReminder.isChecked

        val medicine = Medicine(name, dosage, type, frequency, isReminder)

        if (isReminder) {
            notificationHelper.showReminderNotification(name)
        }

        (activity as? MedicineDashboardActivity)?.onMedicineAdded(medicine)
        parentFragmentManager.popBackStack()
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "AddMedicineFragment: onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "AddMedicineFragment: onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "AddMedicineFragment: onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "AddMedicineFragment: onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "AddMedicineFragment: onDestroyView()")
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "AddMedicineFragment: onDetach()")
    }
}
