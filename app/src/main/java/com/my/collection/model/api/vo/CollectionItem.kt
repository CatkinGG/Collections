package com.my.collection.model.api.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CollectionItem(
    @SerializedName("id")
    val id: Long,

    @SerializedName("image_url")
    val image_url: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("permalink")
    val permalink: String? = null,

    @SerializedName("token_id")
    val token_id: String? = null,

    @SerializedName("asset_contract")
    val asset_contract: ContractItem? = null
): Serializable

