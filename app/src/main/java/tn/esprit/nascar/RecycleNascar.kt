package tn.esprit.nascar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

    class RecycleNascar(private val nascarList : ArrayList<Nascardata>):
    RecyclerView.Adapter<RecycleNascar.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return nascarList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = nascarList[position]
        holder.Image.setImageResource(currentItem.eventImage)
        holder.Title.text = currentItem.eventTitle
        holder.Description.text = currentItem.newsDescription
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val Image : ShapeableImageView = itemView.findViewById(R.id.eventImage)
        val Title : TextView = itemView.findViewById(R.id.eventTitle)
        val Description : TextView = itemView.findViewById(R.id.newsDescription)
    }
}