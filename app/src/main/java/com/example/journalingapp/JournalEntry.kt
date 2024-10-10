package com.example.journalingapp

import java.io.Serializable

/**
 * Data class representing a journal entry.
 *
 * @property title The title of the journal entry.
 * @property content The main content or body of the journal entry.
 * @property date The timestamp of when the journal entry was created or last updated, in milliseconds.
 * Defaults to the current system time when the entry is created.
 *
 * Implements [Serializable] to allow passing objects between activities.
 */
data class JournalEntry(
    val title: String,
    val content: String,
    val date: Long = System.currentTimeMillis()
) : Serializable
