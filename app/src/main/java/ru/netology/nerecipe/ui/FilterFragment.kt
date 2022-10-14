package ru.netology.nerecipe.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.component1
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.R
import ru.netology.nerecipe.adapter.FiltersAdapter
import ru.netology.nerecipe.databinding.FilterFragmentBinding
import ru.netology.nerecipe.viewModel.RecipeViewModel

class FilterFragment : Fragment() {

    private val viewModel: RecipeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FilterFragmentBinding.inflate(
        layoutInflater, container, false
    ).also { binding ->

        val categoriesList = resources.getStringArray(R.array.category_names).toMutableList()

        val adapterFilter = FiltersAdapter(viewModel)
        binding.filterRecycleView.adapter = adapterFilter
        adapterFilter.submitList(categoriesList)

        binding.selectFilter.setOnClickListener {
            val oia = RecipeViewModel(Application()).checkingFilters()
            if (oia == true) {
                Toast.makeText(activity, "Вы не можете снять все фильтры", Toast.LENGTH_LONG).show()
            } else
                findNavController().popBackStack()

            return@setOnClickListener

        }

    }.root

}