package kz.kolesa.contactlistapp.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kz.kolesa.contactlistapp.R
import kz.kolesa.contactlistapp.domain.MappedContact

/**
 * Created by Alikhan on 12/11/2018.
 */
class MainAdapter(
    private val contactList: List<MappedContact>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, type: Int): MainAdapter.ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_main, parent, false)

        return ViewHolder(rootView)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.onBind(contactList[position])
        holder.itemView.setOnClickListener { listener.onItemClick(position) }
    }

    fun setList(newList: List<MappedContact>) {
        contactList.toMutableList().clear()
        contactList.toMutableList().addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName = view.findViewById<TextView>(R.id.item_tv_name)
        private val tvGroupName = view.findViewById<TextView>(R.id.item_tv_contact_group_name)
        private val itemIv = view.findViewById<ImageView>(R.id.item_iv)

        fun onBind(c: MappedContact) {
            tvName.text = c.contact.name
            tvGroupName.text = c.contactGroup.name
        }
    }
}