package tn.esprit.nascar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import tn.esprit.nascar.databinding.SingleItemEventsBinding
import tn.esprit.nascar.models.Events
import tn.esprit.nascar.utils.AppDatabase

class EventsAdapter(val eventsList: MutableList<Events>) : RecyclerView.Adapter<EventsAdapter.EventsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val binding = SingleItemEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventsHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        with(holder){
            with(eventsList[position]){
                binding.eventTitle.text = title
                binding.eventDescription.text = description
                binding.eventImage.setImageResource(imageRes)
                binding.btnAddBookmark.setOnClickListener {
                    //TODO 12 Check if this event is already added in the database(Show Snackbar) otherwise add it
                    val eventDao = AppDatabase.getInstance(holder.itemView.context).eventDao()

                    // Check if the event is already added in the database
                    val existingEvent = eventDao.findEventById(id)
                    if (existingEvent != null) {
                        // Show a Snackbar indicating that the event is already added
                        Snackbar.make(holder.itemView, "Event is already added", Snackbar.LENGTH_SHORT).show()
                    } else {
                        // Add the event to the database
                        eventDao.insertEvent(this)
                        // Show a Snackbar indicating that the event is added
                        Snackbar.make(holder.itemView, "Event added", Snackbar.LENGTH_SHORT).show()
                    }
                }

                }
            }
        }


    override fun getItemCount() = eventsList.size

    inner class EventsHolder(val binding: SingleItemEventsBinding) : RecyclerView.ViewHolder(binding.root)
}