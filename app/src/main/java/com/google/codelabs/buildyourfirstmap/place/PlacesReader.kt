
package com.google.codelabs.buildyourfirstmap.place

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.codelabs.buildyourfirstmap.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import java.io.InputStream
import java.io.InputStreamReader

class PlacesReader(private val context: Context) {

    private val gson = Gson()

    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type
        val reader = InputStreamReader(inputStream)
        return gson.fromJson<List<PlaceResponse>>(reader, itemType).map {
            it.toPlace()
        }
    }
}