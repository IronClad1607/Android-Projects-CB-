package com.example.fakerestapi.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fakerestapi.R
import com.example.fakerestapi.allActivities.PostActivity
import com.example.fakerestapi.modal.User
import kotlinx.android.synthetic.main.cvuser.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class UserAdapter(private val users: List<User>, context: Context) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(), CoroutineScope {

    val supervisor = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisor

    val mContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.cvuser, parent, false)
        return ViewHolder(view, mContext)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)

    }

    class ViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
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

                setOnClickListener {

                    val intent = Intent(context, PostActivity::class.java)
                    intent.putExtra("data", "user")
                    intent.putExtra("userId",user.id)
                    startActivity(context, intent, null)
                }


            }
        }
    }
}

