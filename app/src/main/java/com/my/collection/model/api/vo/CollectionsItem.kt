package com.my.collection.model.api.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CollectionsItem(
    @SerializedName("assets")
    val assets: ArrayList<CollectionItem>
): Serializable

