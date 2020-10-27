package co.micode.brewdogbeerchallenge.beerdetails.detailslist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class DetailsListAdapter
@Inject
constructor(private val manager: ViewHolderFactoryManager) :
    RecyclerView.Adapter<DetailsListViewHolder<*>>() {

    private val items = arrayListOf<BeerParameters>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        manager.createViewHolderForSelectedType(parent, viewType)

    override fun onBindViewHolder(holder: DetailsListViewHolder<*>, position: Int) =
        holder.bind(items[position])

    override fun getItemViewType(position: Int) = items[position].type

    override fun getItemCount() = items.size

    fun setItems(newItems: List<BeerParameters>) {
        val diffResult = DiffUtil.calculateDiff(DetailsListUtil(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}

class DetailsListUtil(
    private val oldItems: List<BeerParameters>,
    private val newItems: List<BeerParameters>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition].type == newItems[newItemPosition].type

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition] == newItems[newItemPosition]

}
