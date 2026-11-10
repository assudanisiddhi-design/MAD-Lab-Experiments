package com.example.contentproviderapp

import android.content.Context
import android.provider.ContactsContract

data class ContactItem(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val email: String = "mca.student@university.edu"
)

class ContactsHelper(private val context: Context) {

    fun getDeviceContacts(): List<ContactItem> {
        val contactsList = mutableListOf<ContactItem>()

        try {
            val cursor = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
            )

            cursor?.use { c ->
                val idIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
                val nameIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val numberIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                while (c.moveToNext()) {
                    val id = if (idIndex != -1) c.getString(idIndex) else ""
                    val name = if (nameIndex != -1) c.getString(nameIndex) ?: "Unknown" else "Unknown"
                    val number = if (numberIndex != -1) c.getString(numberIndex) ?: "" else ""

                    if (number.isNotBlank()) {
                        contactsList.add(ContactItem(id, name, number))
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // If no device contacts exist (e.g. fresh emulator), provide sample MCA Department Contacts
        if (contactsList.isEmpty()) {
            return getSampleMcaContacts()
        }

        return contactsList
    }

    fun getSampleMcaContacts(): List<ContactItem> {
        return listOf(
            ContactItem("1", "Siddhi Assudani (MCA Student)", "+91 98765 43210", "siddhi.assudani@mca.edu"),
            ContactItem("2", "Dr. H. R. Sharma (MAD Lab HOD)", "+91 98123 45678", "hod.mca@university.edu"),
            ContactItem("3", "Prof. Ankit Verma (Lab Mentor)", "+91 97234 56789", "ankit.v@university.edu"),
            ContactItem("4", "MCA Class Representative", "+91 96345 67890", "cr.mca@university.edu"),
            ContactItem("5", "Placement & Training Cell", "+91 95456 78901", "placements@university.edu"),
            ContactItem("6", "MCA Academic Coordinator", "+91 94567 89012", "academics@university.edu")
        )
    }
}
