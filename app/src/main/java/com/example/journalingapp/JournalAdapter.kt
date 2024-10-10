package com.example.journalingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * A RecyclerView Adapter for displaying a list of journal entries.
 *
 * @property entries A mutable list of [JournalEntry] objects to be displayed.
 * @property onItemClick A lambda function that handles item click events, passing the selected entry and its position.
 * @property onDeleteClick A lambda function that handles delete button click events, passing the entry to be deleted and its position.
 */
class JournalAdapter(
    private val entries: MutableList<JournalEntry>,
    private val onItemClick: (JournalEntry, Int) -> Unit,
    private val onDeleteClick: (JournalEntry, Int) -> Unit
) : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    /**
     * A ViewHolder that holds references to the UI components for each journal entry item.
     *
     * @param view The view representing an individual item in the RecyclerView.
     */
    inner class JournalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // TextView to display the journal entry's title.
        val titleTextView: TextView = view.findViewById(R.id.text_view_title)

        // TextView to display the journal entry's content.
        val contentTextView: TextView = view.findViewById(R.id.text_view_content)

        // Button to delete the journal entry.
        private val deleteButton: Button = view.findViewById(R.id.button_delete)

        init {
            // Handle item click events by calling the provided onItemClick function.
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(entries[position], position)
                }
            }

            // Handle delete button click events by calling the provided onDeleteClick function.
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClick(entries[position], position)
                }
            }
        }
    }

    /**
     * Inflates the layout for each journal entry item and creates a new ViewHolder.
     *
     * @param parent The parent ViewGroup into which the new view will be added.
     * @param viewType The type of the new view (not used in this simple implementation).
     * @return A new instance of [JournalViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_journal_entry, parent, false)
        return JournalViewHolder(view)
    }

    /**
     * Binds data from a [JournalEntry] to the views in the ViewHolder.
     * Displays the entry's title and a truncated version of the content if it exceeds 50 characters.
     *
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the entry in the list.
     */
    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val entry = entries[position]
        holder.titleTextView.text = entry.title
        holder.contentTextView.text = if (entry.content.length > 50) {
            "${entry.content.take(50)}..."
        } else {
            entry.content
        }
    }

    /**
     * Returns the total number of entries in the list.
     *
     * @return The size of the entries list.
     */
    override fun getItemCount(): Int = entries.size
}
