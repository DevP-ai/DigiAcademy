package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.adapter.NewsAdapter
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.FragmentNewsBinding
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.viewmodel.NewsViewModel
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.utilities.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
   private lateinit var binding: FragmentNewsBinding

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(emptyList())
    }

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentNewsBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEntertainment.setOnClickListener {
            newsViewModel.getNews("in","entertainment","")
        }
        binding.btnGeneral.setOnClickListener {
            newsViewModel.getNews("in","general","")
        }
        binding.btnHealth.setOnClickListener {
            newsViewModel.getNews("in","health","")
        }
        binding.btnScience.setOnClickListener {
            newsViewModel.getNews("in","science","")
        }
        binding.btnSports.setOnClickListener {
            newsViewModel.getNews("in","sports","")
        }
        binding.btnTechnology.setOnClickListener {
            newsViewModel.getNews("in","technology","")
        }

        binding.btnBusiness.setOnClickListener {
            newsViewModel.getNews("in","business","")
        }

        newsViewModel.getNews("in","business","")

        newsViewModel.news.observe(viewLifecycleOwner){event->
            event.getContentIfNotHandle()?.let {result->
                when(result.status){

                    Status.LOADING->{
                        binding.newsRecyclerView.visibility=View.GONE
                        binding.progressBar.visibility=View.VISIBLE
                    }

                    Status.SUCCESS->{
                        result.data?.let {newsResponse->
                            binding.newsRecyclerView.visibility=View.VISIBLE
                            binding.progressBar.visibility=View.GONE
                            newsAdapter.newsList=newsResponse.articles
                            newsAdapter.notifyDataSetChanged()
                        }
                    }

                    Status.ERROR->{
                        binding.newsRecyclerView.visibility=View.GONE
                        binding.progressBar.visibility=View.GONE
                        Toast.makeText(requireContext(),"Failed..", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        newsRecyclerView()

    }

    private fun newsRecyclerView() {
        binding.newsRecyclerView.apply {
            layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter=newsAdapter
        }
    }

}