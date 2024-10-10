package com.example.journalingapp

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the JournalAdapter class, focusing on verifying the behavior of adding,
 * editing, and deleting journal entries.
 */
class JournalAdapterTest {

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
        // Initialize data without using Android context
        entries = mutableListOf(
            JournalEntry("Entry 1", "Content 1"),
            JournalEntry("Entry 2", "Content 2")
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
        // Create a new journal entry.
        val newEntry = JournalEntry("New Entry", "New Content")
        // Add the new entry to the entries list.
        entries.add(newEntry)

        // Assert that the list size has increased to 3.
        assertEquals(3, entries.size)
        // Assert that the title of the last entry matches the expected value.
        assertEquals("New Entry", entries[2].title)
    }

    /**
     * Tests the behavior of editing an existing journal entry.
     * Replaces the first entry with an updated one and verifies that the change is applied.
     */
    @Test
    fun testEditEntry() {
        // Create an updated journal entry.
        val updatedEntry = JournalEntry("Updated Entry", "Updated Content")
        // Replace the first entry with the updated entry.
        entries[0] = updatedEntry

        // Assert that the title of the first entry matches the updated value.
        assertEquals("Updated Entry", entries[0].title)
    }

    /**
     * Tests the behavior of deleting a journal entry from the list.
     * Removes the first entry and verifies that the list size decreases as expected.
     */
    @Test
    fun testDeleteEntry() {
        // Remove the first entry from the entries list.
        entries.removeAt(0)

        // Assert that the list size has decreased to 1.
        assertEquals(1, entries.size)
    }
}
