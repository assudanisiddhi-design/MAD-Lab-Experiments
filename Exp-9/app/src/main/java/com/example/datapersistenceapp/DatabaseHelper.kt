package com.example.datapersistenceapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class CourseItem(
    val id: Int,
    val title: String,
    val code: String,
    val iconType: String,
    val description: String,
    val referenceUrl: String
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MCA_Courses.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_COURSES = "courses"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CODE = "code"
        const val COLUMN_ICON_TYPE = "icon_type"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_REFERENCE_URL = "reference_url"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_COURSES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT NOT NULL,
                $COLUMN_CODE TEXT NOT NULL,
                $COLUMN_ICON_TYPE TEXT NOT NULL,
                $COLUMN_DESCRIPTION TEXT NOT NULL,
                $COLUMN_REFERENCE_URL TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)

        // Seed initial data
        seedInitialData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_COURSES")
        onCreate(db)
    }

    private fun seedInitialData(db: SQLiteDatabase) {
        val defaultCourses = listOf(
            CourseItem(0, "Mobile App Development", "22MCA31", "info", "Build Android apps using Kotlin & Compose.", "https://developer.android.com"),
            CourseItem(0, "Data Science with Python", "22MCA32", "menu", "Analyze and visualize complex datasets.", "https://www.python.org"),
            CourseItem(0, "Cloud Computing", "22MCA33", "settings", "Learn AWS, Azure & cloud deployment.", "https://aws.amazon.com"),
            CourseItem(0, "Cyber Security", "22MCA34", "lock", "Network security & cryptography fundamentals.", "https://www.cisa.gov"),
            CourseItem(0, "AI & Machine Learning", "22MCA35", "star", "Neural networks and predictive models.", "https://www.tensorflow.org"),
            CourseItem(0, "Big Data Analytics", "22MCA36", "list", "Processing data with Hadoop and Spark.", "https://spark.apache.org")
        )

        for (course in defaultCourses) {
            val values = ContentValues().apply {
                put(COLUMN_TITLE, course.title)
                put(COLUMN_CODE, course.code)
                put(COLUMN_ICON_TYPE, course.iconType)
                put(COLUMN_DESCRIPTION, course.description)
                put(COLUMN_REFERENCE_URL, course.referenceUrl)
            }
            db.insert(TABLE_COURSES, null, values)
        }
    }

    // CRUD: CREATE
    fun insertCourse(title: String, code: String, iconType: String, description: String, referenceUrl: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_CODE, code)
            put(COLUMN_ICON_TYPE, iconType)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_REFERENCE_URL, referenceUrl)
        }
        return db.insert(TABLE_COURSES, null, values)
    }

    // CRUD: READ
    fun getAllCourses(): List<CourseItem> {
        val courseList = mutableListOf<CourseItem>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_COURSES ORDER BY $COLUMN_ID DESC", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val code = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CODE))
                val iconType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ICON_TYPE))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
                val referenceUrl = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REFERENCE_URL))

                courseList.add(CourseItem(id, title, code, iconType, description, referenceUrl))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return courseList
    }

    // CRUD: DELETE
    fun deleteCourse(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_COURSES, "$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    fun getCourseCount(): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_COURSES", null)
        var count = 0
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }
        cursor.close()
        return count
    }

    fun resetDatabase() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_COURSES")
        seedInitialData(db)
    }
}
