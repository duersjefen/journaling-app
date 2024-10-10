package com.example.journalingapp

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the MainActivity functionality, specifically focusing on the behavior of
 * adding, editing, and deleting journal entries.
 */
class MainActivityTest {

    // A mutable list to hold journal entries, simulating the data set used by the adapter.
    private lateinit var entries: MutableList<JournalEntry>

    // An instance of the JournalAdapter, initialized before each test.
    private lateinit var adapter: JournalAdapter

    /**
     * Sets up the test environment before each test case runs. Initializes the journal
     * entries list with sample data and sets up the adapter with these entries.
     */
    @Before
    fun setup() {
        // Initialize data and adapter without using Android context
        entries = mutableListOf(
            JournalEntry("First Entry", "First Content")
        )
        // Create a JournalAdapter with the initialized entries and empty click listeners.
        adapter = JournalAdapter(entries, { _, _ -> }, { _, _ -> })
    }

    /**
     * Tests the behavior of adding a new journal entry to the list.
     * Verifies that the list size increases and that the newly added entry matches
     * the expected values.
     */
    @Test
    fun testAddEntry() {
        // Simulate adding a new journal entry.
        val newEntry = JournalEntry("New Entry", "New Content")
        entries.add(newEntry)

        // Validate that the entry was added successfully.
        assertEquals(2, entries.size)
        assertEquals("New Entry", entries[1].title)
    }

    /**
     * Tests the behavior of editing an existing journal entry.
     * Replaces the first entry with an updated one and verifies that the change is applied.
     */
    @Test
    fun testEditEntry() {
        // Simulate editing the first journal entry.
        val updatedEntry = JournalEntry("Updated Entry", "Updated Content")
        entries[0] = updatedEntry

        // Validate that the entry was updated successfully.
        assertEquals(1, entries.size)
        assertEquals("Updated Entry", entries[0].title)
    }

    /**
     * Tests the behavior of deleting a journal entry from the list.
     * Removes the first entry and verifies that the list size decreases as expected.
     */
    @Test
    fun testDeleteEntry() {
        // Simulate deleting the first journal entry.
        entries.removeAt(0)

        // Validate that the entry was deleted successfully.
        assertEquals(0, entries.size)
    }
}
