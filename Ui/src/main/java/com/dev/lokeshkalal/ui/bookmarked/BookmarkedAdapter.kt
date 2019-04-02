package com.dev.lokeshkalal.ui.bookmarked

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

class BookmarkedAdapter @Inject constructor() : RecyclerView.Adapter<BookmarkedAdapter.ViewHolder>() {

    var projects: List<Project> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bookmark_project, parent, false)

        return ViewHolder(item)
    }

    override fun getItemCount(): Int {
        return projects.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]

        holder.ownerNameText.text = project.ownerName
        holder.projectNameText.text = project.fullName

        Glide.with(holder.itemView.context)
            .load(project.ownerAvatar)
            .apply(RequestOptions().centerCrop())
            .into(holder.avatarImage)

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var avatarImage: ImageView;
        var ownerNameText: TextView;
        var projectNameText: TextView;

        init {
            avatarImage = view.findViewById(R.id.image_owner_avatar)
            ownerNameText = view.findViewById(R.id.text_owner_name)
            projectNameText = view.findViewById(R.id.text_project_name)
        }
    }
}