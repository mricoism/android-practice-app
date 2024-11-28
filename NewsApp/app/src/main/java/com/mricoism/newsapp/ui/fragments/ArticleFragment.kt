package com.mricoism.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mricoism.newsapp.R
import com.mricoism.newsapp.databinding.FragmentArticleBinding
import com.mricoism.newsapp.ui.NewsActivity
import com.mricoism.newsapp.ui.NewsViewModel


// Default when created file
//class ArticleFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_article, container, false)
//    }
//}


class ArticleFragment: Fragment(R.layout.fragment_article) {

    lateinit var newsViewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        newsViewModel = (activity as NewsActivity).newsViewModel
        val article = args.article

        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url.let {
                loadUrl(it)
            }
        }

        binding.fab.setOnClickListener {
            newsViewModel.addToFavourites(article)
            Snackbar.make(view, "Added to favourites", Snackbar.LENGTH_SHORT).show()
        }

    }
}