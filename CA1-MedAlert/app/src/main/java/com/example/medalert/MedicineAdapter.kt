package com.example.medalert

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medalert.databinding.ItemMedicineBinding

class MedicineAdapter(
    private var medicines: List<Medicine>,
    private val onItemClick: (Medicine) -> Unit
) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

    inner class MedicineViewHolder(private val binding: ItemMedicineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(medicine: Medicine) {
            binding.medicineName.text = medicine.name
            binding.medicineDosage.text = medicine.dosage
            binding.medicineInfo.text = "${medicine.type} • ${medicine.frequency}"
            
            if (medicine.reminderRequired) {
                binding.reminderStatus.text = "Reminder Enabled"
                binding.reminderIcon.visibility = View.VISIBLE
            } else {
                binding.reminderStatus.text = "No Reminder"
                binding.reminderIcon.visibility = View.GONE
            }
            
            binding.root.setOnClickListener { onItemClick(medicine) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = ItemMedicineBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(medicines[position])
    }

    override fun getItemCount(): Int = medicines.size

    fun updateData(newMedicines: List<Medicine>) {
        medicines = newMedicines
        notifyDataSetChanged()
    }
}
