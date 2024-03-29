package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter(
    private var onItemClickListener: ((Article) -> Unit)
) : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {


    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    //dataSetChanged > recyclerview update all item even item that didn't change so we use DiffUtil.
    //DiffUtil calculate different between two lists and enable only update those items that would change,
    // and this happen in background so didn't block main thread(asynchronous)
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    //AsyncListDiffer >> take two list and compare and calculate the differences (run in background)
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article_preview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {

            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvPublishedAt.text = article.publishedAt
            tvTitle.text = article.title
            tvDescription.text = article.description

            //for click to item
            holder.itemView.setOnClickListener {
                onItemClickListener(article)
            }
        }
        
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}