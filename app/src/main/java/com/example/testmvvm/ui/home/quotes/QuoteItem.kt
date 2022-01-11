package com.example.testmvvm.ui.home.quotes

import androidx.databinding.Bindable
import com.example.testmvvm.R
import com.example.testmvvm.database.entities.Quote
import com.example.testmvvm.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote:Quote
) :BindableItem<ItemQuoteBinding>(){
    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
        
    }

    override fun getLayout()= R.layout.item_quote


}