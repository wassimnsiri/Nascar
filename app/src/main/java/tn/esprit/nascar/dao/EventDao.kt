package tn.esprit.nascar.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import tn.esprit.nascar.models.Events


//TODO 9 Change this interface to a DAO and add 4 methods with the proper annotation:
// insertEvent(events: Events)
// deleteEvent(events: Events)
// getAllEvent() : MutableList<Events>
// getFindEventById(id: Int) : Events

@Dao
interface EventDao {

    @Insert
    fun insertEvent(event: Events)

    @Delete
    fun deleteEvent(event: Events)

    @Query("SELECT * FROM Events")
    fun getAllEvents(): MutableList<Events>

    @Query("SELECT * FROM Events WHERE id = :id")
    fun findEventById(id: Int): Events
}