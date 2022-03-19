package com.example.todo.ui.addtask

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentAddEditBinding
import com.example.todo.util.DatePickerFragment

import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditTaskFragment : Fragment(R.layout.fragment_add_edit) {

    private val viewModel: AddEditTaskViewModel by viewModels()
    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            editTextTaskName.setText(viewModel.taskName)
            checkBoxImportant.isChecked = viewModel.taskImportance
            checkBoxImportant.jumpDrawablesToCurrentState()


            editTextTaskName.addTextChangedListener {
                viewModel.taskName = it.toString()
            }

            checkBoxImportant.setOnCheckedChangeListener { _, isChecked ->
                viewModel.taskImportance = isChecked
            }

            date.setOnClickListener {
                val datePickerFragment = DatePickerFragment {
                    deadline.text = it
                }
                datePickerFragment.show(childFragmentManager, "datePicker")
            }

            fabSaveTask.setOnClickListener {
                showProgressBar()
                viewModel.onSaveClick()
                hideProgressBar()
            }

        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditTaskEvent.collect { event ->
                when (event) {
                    is AddEditTaskEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }
                    is AddEditTaskEvent.NavigateBackWithResult -> {
                        binding.editTextTaskName.clearFocus()
                        setFragmentResult(
                            "add_edit_request",
                            bundleOf("add_edit" to event.result)
                        )
                        //wstecz
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = GONE
    }

    private fun showProgressBar() {
        binding.progressbar.visibility = VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
















