package co.micode.brewdogbeerchallenge.beerlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.micode.brewdogbeerchallenge.R
import co.micode.brewdogbeerchallenge.utils.ImageLoader
import co.micode.brewdogbeerchallenge.utils.RATE_PRECISION
import co.micode.brewdogbeerchallenge.utils.format
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class BeerListAdapter
@Inject
constructor(private val imageLoader: ImageLoader) :
    PagingDataAdapter<BeerListItem, BeerListHolder>(BeerListCallback()) {

    var beerListItemClickListener: BeerListItemClickListener? = null

    override fun onBindViewHolder(holder: BeerListHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            beerListItemClickListener?.invoke(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BeerListHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false),
        imageLoader
    )
}

class BeerListHolder(view: View, private val imageLoader: ImageLoader) :
    RecyclerView.ViewHolder(view) {

    val name = view.beer_name
    val abv = view.beer_abv
    val image = view.beer_logo

    fun bind(item: BeerListItem?) {
        item?.let {
            name.text = item.name
            abv.text = item.abv.format(RATE_PRECISION)
            imageLoader.loadImage(item.imageUrl, image)
        }
    }
}

class BeerListCallback : DiffUtil.ItemCallback<BeerListItem>() {

    override fun areItemsTheSame(oldItem: BeerListItem, newItem: BeerListItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BeerListItem, newItem: BeerListItem) =
        oldItem == newItem
}

typealias BeerListItemClickListener = (BeerListItem?) -> Unit

