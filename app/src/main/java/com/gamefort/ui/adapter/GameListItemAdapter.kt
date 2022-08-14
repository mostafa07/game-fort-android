package com.gamefort.ui.adapter

import com.gamefort.BR
import com.gamefort.R
import com.gamefort.data.model.domain.GameListItem
import com.gamefort.databinding.ItemGameBinding
import com.gamefort.ui.adapter.base.BaseRecyclerViewAdapter

class GameListItemAdapter(onItemClickListener: OnItemClickListener<GameListItem>) :
    BaseRecyclerViewAdapter<GameListItem, ItemGameBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int = R.layout.item_game

    override fun getViewBindingVariableId(): Int = BR.game

    override fun onViewHolderBinding(
        viewDataBinding: ItemGameBinding?,
        item: GameListItem?,
        position: Int
    ) {
    }
}