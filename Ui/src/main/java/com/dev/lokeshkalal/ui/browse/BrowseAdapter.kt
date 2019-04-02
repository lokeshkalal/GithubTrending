package com.dev.lokeshkalal.ui.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dev.lokeshkalal.ui.R
import com.dev.lokeshkalal.ui.model.Project
import javax.inject.Inject

class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var projects: List<Project> = arrayListOf()

    var projectListener : ProjectListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_project, parent, false)

        return ViewHolder(item)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.ownerNameText.text = project.ownerName
        holder.projectNameText.text = project.fullName
        Glide.with(holder.itemView.context)
            .load(project.ownerAvatar)
            .apply(RequestOptions().circleCrop())
            .into(holder.avatarImage)

        if(project.isBookmarked){
            holder.bookmarkImage.setImageResource(android.R.drawable.star_on)
        }else{
            holder.bookmarkImage.setImageResource(android.R.drawable.star_off)
        }

        holder.itemView.setOnClickListener {
            if(project.isBookmarked){
                projectListener?.onBookmarkProjectClicked(project.id)
            }else{
                projectListener?.onProjectClicked(project.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return projects.count()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var avatarImage: ImageView;
        var bookmarkImage: ImageView;
        var ownerNameText: TextView;
        var projectNameText: TextView;

        init {
            avatarImage = view.findViewById(R.id.image_owner_avatar)
            bookmarkImage = view.findViewById(R.id.image_bookmarked)
            ownerNameText = view.findViewById(R.id.text_owner_name)
            projectNameText = view.findViewById(R.id.text_project_name)
        }


    }
}