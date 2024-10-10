package com.example.journalingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity for adding or editing a journal entry.
 * This activity allows users to input a title and content for a journal entry,
 * and save the data to be passed back to the MainActivity.
 */
class AddEntryActivity : AppCompatActivity() {

    /**
     * Called when the activity is first created.
     * Sets up the UI elements and handles saving the journal entry.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down, this Bundle contains the most recent data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        // Reference to the title and content input fields.
        val titleEditText: EditText = findViewById(R.id.edit_text_title)
        val contentEditText: EditText = findViewById(R.id.edit_text_content)
        // Reference to the save button.
        val saveButton: Button = findViewById(R.id.button_save)

        // Retrieve the existing journal entry and its position from the intent (if editing an entry).
        val entry = intent.getSerializableExtra("journal_entry") as? JournalEntry
        val position = intent.getIntExtra("position", -1)

        // If an existing entry is passed, pre-fill the input fields with its data.
        entry?.let {
            titleEditText.setText(it.title)
            contentEditText.setText(it.content)
        }

        // Set up the click listener for the save button.
        saveButton.setOnClickListener {
            // Get the title and content entered by the user.
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()

            // Ensure that both the title and content are not empty.
            if (title.isNotEmpty() && content.isNotEmpty()) {
                // Create a new or updated JournalEntry with the user's input.
                val updatedEntry = JournalEntry(title, content)
                // Prepare an intent to send the updated entry back to MainActivity.
                val resultIntent = Intent()
                resultIntent.putExtra("journal_entry", updatedEntry)
                resultIntent.putExtra("position", position)
                // Set the result as RESULT_OK and finish the activity.
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
