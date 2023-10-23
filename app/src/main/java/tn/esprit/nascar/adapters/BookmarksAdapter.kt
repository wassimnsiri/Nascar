package tn.esprit.nascar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nascar.databinding.SingleItemBookmarkBinding
import tn.esprit.nascar.models.Events
import tn.esprit.nascar.utils.AppDatabase

class BookmarksAdapter(val eventsList: MutableList<Events>) : RecyclerView.Adapter<BookmarksAdapter.BookmarksHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksHolder {
        val binding = SingleItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarksHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarksHolder, position: Int) {
        with(holder){
            with(eventsList[position]){
                binding.eventTitle.text = title
                binding.eventDescription.text = description
                binding.eventImage.setImageResource(imageRes)
                binding.btnRemoveBookmark.setOnClickListener {
                    //TODO 13 Delete this event from the database and refresh the list
                    val eventDao = AppDatabase.getInstance(holder.itemView.context).eventDao()

                    // Delete the event from the database
                    eventDao.deleteEvent(this)

                    // Remove the event from the list
                    eventsList.removeAt(position)

                    // Notify the adapter that an item is removed at the specified position
                    notifyItemRemoved(position)
                    // Notify the adapter that the data set has changed
                    notifyItemRangeChanged(position, itemCount)
                }
            }
                }
            }


    override fun getItemCount() = eventsList.size

    inner class BookmarksHolder(val binding: SingleItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root)
}