package com.example.journalingapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * MainActivity serves as the entry point of the Journaling app.
 * It displays a list of journal entries using a RecyclerView and allows users to add, edit, or delete entries.
 */
class MainActivity : AppCompatActivity() {

    // List to store all journal entries
    private val entries = mutableListOf<JournalEntry>()
    // Adapter for managing RecyclerView items
    private lateinit var adapter: JournalAdapter

    // Launcher for starting the AddEntryActivity to add a new journal entry
    private val addEntryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val entry = result.data?.getSerializableExtra("journal_entry") as? JournalEntry
            entry?.let {
                entries.add(it)
                adapter.notifyItemInserted(entries.size - 1)
            }
        }
    }

    /**
     * Called when the activity is first created. Sets up the UI components and initializes the RecyclerView.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup RecyclerView with a LinearLayoutManager and attach the adapter
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_entries)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = JournalAdapter(entries, ::onEntryClick, ::onDeleteClick)
        recyclerView.adapter = adapter

        // Setup listener for the "Add Entry" button to launch AddEntryActivity
        findViewById<Button>(R.id.button_add_entry).setOnClickListener {
            val intent = Intent(this, AddEntryActivity::class.java)
            addEntryLauncher.launch(intent)
        }
    }

    // Launcher for starting the AddEntryActivity to edit an existing journal entry
    private val editEntryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val entry = result.data?.getSerializableExtra("journal_entry") as? JournalEntry
            val position = result.data?.getIntExtra("position", -1) ?: -1
            if (entry != null && position >= 0) {
                entries[position] = entry
                adapter.notifyItemChanged(position)
            }
        }
    }

    /**
     * Called when a journal entry in the list is clicked.
     * Launches AddEntryActivity to edit the selected entry.
     *
     * @param entry The selected JournalEntry object.
     * @param position The position of the selected entry in the list.
     */
    private fun onEntryClick(entry: JournalEntry, position: Int) {
        val intent = Intent(this, AddEntryActivity::class.java)
        intent.putExtra("journal_entry", entry)
        intent.putExtra("position", position)
        editEntryLauncher.launch(intent)
    }

    /**
     * Called when the delete button for a journal entry is clicked.
     * Removes the entry from the list and updates the adapter.
     *
     * @param entry The JournalEntry to be deleted.
     * @param position The position of the entry to be removed.
     */
    private fun onDeleteClick(entry: JournalEntry, position: Int) {
        entries.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}
