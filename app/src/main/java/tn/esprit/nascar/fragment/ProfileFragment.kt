package tn.esprit.nascar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.nascar.adapters.BookmarksAdapter
import tn.esprit.nascar.databinding.FragmentProfileBinding
import tn.esprit.nascar.models.Events

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var eventsList: MutableList<Events>
    private lateinit var bookmarksAdapter: BookmarksAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        //TODO 14 Get all events from database and create the BookmarksAdapter and assign it to the recyclerView rvBookmarks
        eventsList = getAllEventsFromDatabase() // Replace with your code to get all events from the database

        bookmarksAdapter = BookmarksAdapter(eventsList)
        binding.rvBookmarks.adapter = bookmarksAdapter
        binding.rvBookmarks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    private fun getAllEventsFromDatabase(): MutableList<Events> {
        return mutableListOf()
    }

}
