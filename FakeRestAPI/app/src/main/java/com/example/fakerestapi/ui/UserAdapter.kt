package com.example.fakerestapi.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.modal.User
import kotlinx.android.synthetic.main.cvuser.view.*

class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.cvuser, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            with(itemView) {
                tvName.text = user.name
                tvUsername.text = user.username
                tvEmail.text = "Email:" + user.email
                tvPhone.text = "Phone:" + user.phone
                tvWebsite.text = user.website
                tvStreets.text = "Street:" + user.address.street
                tvSuite.text = "Suite:" + user.address.suite
                tvCity.text = "City:" + user.address.city
                tvZipCode.text = "Zipcode:" + user.address.zipcode
                tvLat.text = "Lat:" + user.address.geo.lat.toString()
                tvLng.text = "Long:" + user.address.geo.lng.toString()
                tvNameC.text = "Name:" + user.company.name
                tvCP.text = "Catch Pharse:" + user.company.catchPhrase
                tvBS.text = "BS:" + user.company.bs
            }
        }
    }
}