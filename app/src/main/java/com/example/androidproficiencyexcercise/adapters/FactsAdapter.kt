package com.example.androidproficiencyexcercise.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidproficiencyexcercise.R
import com.example.androidproficiencyexcercise.R.layout
import com.example.androidproficiencyexcercise.databinding.ItemFactBinding
import com.example.androidproficiencyexcercise.model.Facts


/**
 * Adapter class extending base adapter to populate fact list
 * @param factList List of facts returned from api response
 * @param context Context of activity calling adapter
 */
open class FactsAdapter(
    private var factList: MutableList<Facts>?,
    private val context: Context
) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemFactBinding: ItemFactBinding = ItemFactBinding
            .inflate(LayoutInflater.from(parent!!.context))
        setLayoutVisibility(itemFactBinding, position)
        itemFactBinding.responseModel = factList!![position]
        loadFactImage(itemFactBinding, position)
        return itemFactBinding.root
    }

    /**
     * Load image into each row using Glide library
     * @param itemFactBinding binding for row
     * @param position listView position
     */
    private fun loadFactImage(itemFactBinding: ItemFactBinding, position: Int) {
        Glide.with(context)
            .load(factList!![position].imageHref)
            .apply(RequestOptions().placeholder(R.drawable.ic_placeholder))
            .into(itemFactBinding.imageFact)
    }

    /**
     * Toggle layout visibility according to data
     * @param itemFactBinding binding for row
     * @param position listView position
     */
    private fun setLayoutVisibility(itemFactBinding: ItemFactBinding, position: Int) {
        when {
            factList!![position].title.isNullOrEmpty() &&
                    factList!![position].description.isNullOrEmpty() &&
                    factList!![position].imageHref.isNullOrEmpty() -> {
                deleteRow(position)
            }
            else -> {
                when {
                    factList!![position].title.isNullOrEmpty() ->
                        itemFactBinding.textTitle.visibility = View.GONE
                    else -> itemFactBinding.textTitle.visibility = View.VISIBLE
                }
                when {
                    factList!![position].description.isNullOrEmpty() ->
                        itemFactBinding.textDescription.visibility = View.GONE
                    else -> itemFactBinding.textDescription.visibility = View.VISIBLE
                }
                when {
                    factList!![position].imageHref.isNullOrEmpty() ->
                        itemFactBinding.imageFact.visibility = View.GONE
                    else -> itemFactBinding.imageFact.visibility = View.VISIBLE
                }
            }
        }
    }

    /**
     * Function to update current data
     */
    fun updateFacts(updatedFactList: MutableList<Facts>?) {
        if (!factList.isNullOrEmpty())
            this.factList!!.clear()
        this.factList = updatedFactList
        notifyDataSetChanged()
    }

    /**
     * Function to delete row if its empty
     */
    private fun deleteRow(position: Int) {
        factList!!.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any {
        return factList!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return when {
            factList.isNullOrEmpty() -> 0
            else -> factList!!.size
        }
    }
}