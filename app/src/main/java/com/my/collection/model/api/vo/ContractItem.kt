package com.my.collection.model.api.vo

import com.google.gson.annotations.SerializedName

data class ContractItem(
    @SerializedName("address")
    val address: String
)

