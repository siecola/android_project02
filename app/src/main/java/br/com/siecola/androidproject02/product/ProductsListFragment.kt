package br.com.siecola.androidproject02.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import br.com.siecola.androidproject02.databinding.FragmentProductsListBinding

private const val TAG = "ProductsListFragment"

class ProductsListFragment : Fragment() {

    private val productListViewModel: ProductListViewModel by lazy {
        ViewModelProviders.of(this).get(ProductListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "Creating ProductsListFragment")

        val binding = FragmentProductsListBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.productListViewModel = productListViewModel

        val itemDecor = DividerItemDecoration(getContext(), VERTICAL);
        binding.rcvProducts.addItemDecoration(itemDecor);

        binding.rcvProducts.adapter = ProductAdapter(ProductAdapter.ProductClickListener {
            Log.i(TAG, "Product selected: ${it.name}")
            this.findNavController()
                .navigate(ProductsListFragmentDirections.actionShowProductDetail(it.code))
        })

        binding.productsRefresh.setOnRefreshListener {
            Log.i(TAG, "Refreshing products list")
            productListViewModel.refreshProducts()

            binding.productsRefresh.isRefreshing = false
        }

        return binding.root
    }
}