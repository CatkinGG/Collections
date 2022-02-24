package com.my.collection

import android.view.View
import com.my.collection.model.api.vo.CollectionItem

class CollectionsFuncItem(
    val onClick: ((CollectionItem) -> Unit) = { item ->  }
)