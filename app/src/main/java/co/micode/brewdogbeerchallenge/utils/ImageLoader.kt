package co.micode.brewdogbeerchallenge.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageLoader
@Inject
constructor() {

    fun loadImage(url: String, imageView: ImageView) = Picasso.get().load(url)
        .into(imageView)
}
