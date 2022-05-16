package com.rumeysaozer.produvtretrofitmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumeysaozer.produvtretrofitmvvm.adapter.Adapter
import com.rumeysaozer.produvtretrofitmvvm.databinding.FragmentProductBinding
import com.rumeysaozer.produvtretrofitmvvm.viewmodel.ProductViewModel


class ProductFragment : Fragment() {
    private lateinit var viewModel : ProductViewModel
    private var _binding: FragmentProductBinding? = null
    private val adapter = Adapter(arrayListOf())
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = androidx.lifecycle.ViewModelProviders.of(this).get(ProductViewModel::class.java)
        viewModel.refreshData()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.products.observe(viewLifecycleOwner, Observer {products ->

            products?.let {
                binding.recyclerView.visibility = View.VISIBLE
                adapter.updateProductList(it)
            }

        })
        viewModel.error.observe(viewLifecycleOwner,Observer{error ->
            error?.let{
                if(it){
                    binding.error.visibility = View.VISIBLE
                }else{
                    binding.error.visibility = View.GONE
                }
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let{
                if(it){
                    binding.productsProgressBar.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }else{
                    binding.productsProgressBar.visibility = View.GONE
                }
            }
        })
    }
}