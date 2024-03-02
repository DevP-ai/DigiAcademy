package com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.R
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.adapter.ResultAdapter
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.databinding.FragmentHomeBinding
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.activity.QuizGenerateActivity
import com.compose.android.dev.edu.devpost.happyhacksv.digiacademy.ui.viewmodel.quiz.ResultViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val resultViewModel: ResultViewModel by viewModels()
    private var totalMarks: Int = 0
    private var quizAttend: Int = 0
    private val resultAdapter: ResultAdapter by lazy {
        ResultAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnTakeQuiz.setOnClickListener {
            startActivity(Intent(requireContext(), QuizGenerateActivity::class.java))
        }

        observeResult()
        setUpRecyclerView()
        observeQuizAttend()
        observeTotalMarks()


        val itemTouchHelper=object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            )=true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.absoluteAdapterPosition
                resultViewModel.deleteQuizResult(resultAdapter.differ.currentList[position])

                Snackbar.make(requireView(),"Item Deleted", Snackbar.LENGTH_SHORT).show()

            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.resultRecyclerView)


    }

    @SuppressLint("SetTextI18n")
    private fun observeTotalMarks() {
        var attempt=0
        resultViewModel.totalMarks.observe(viewLifecycleOwner) { total ->
            if (total != null) {
                totalMarks = total
                binding.total.text = "${totalMarks}/${attempt}"
            }
        }
        resultViewModel.marks.observe(viewLifecycleOwner){
            if(it!=null){
                attempt=it
                binding.total.text = "${totalMarks}/${attempt}"
            }
        }
    }

    private fun observeQuizAttend() {
        resultViewModel.quizAttend.observe(viewLifecycleOwner) { attendQuiz ->
            quizAttend = attendQuiz
            binding.numberOfQuiz.text = quizAttend.toString()
        }
    }

    private fun observeResult() {
        resultViewModel.quizResult.observe(viewLifecycleOwner) { quizResult ->
            if(quizResult.isEmpty()){
                binding.noDataTxt.visibility=View.VISIBLE
                binding.resultRecyclerView.visibility=View.GONE
            }else{
                binding.noDataTxt.visibility=View.GONE
                binding.resultRecyclerView.visibility=View.VISIBLE
                val reversedList = quizResult.reversed()
                resultAdapter.differ.submitList(reversedList)
                resultAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.resultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = resultAdapter
        }
    }

}