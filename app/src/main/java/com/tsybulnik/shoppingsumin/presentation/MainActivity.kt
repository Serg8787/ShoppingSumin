package com.tsybulnik.shoppingsumin.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tsybulnik.shoppingsumin.R
import com.tsybulnik.shoppingsumin.di.DaggerApplicationComponent
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFInishListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as ShopApplication).component
    }

    private var shopItemContainer:FragmentContainerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shopItemContainer = findViewById(R.id.shopItemContainer)

        setupRecyclerView()

        viewModel = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            adapter.submitList(it)
        }

        val buttonAddShopItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddShopItem.setOnClickListener {
            if(isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode() : Boolean{
     return shopItemContainer == null
    }
    fun onEditingFinished(){


    }

    private fun launchFragment(fragment: Fragment){
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(R.id.shopItemContainer,fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_PULL_SIZE
        )
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISEBLAD,
            ShopListAdapter.MAX_PULL_SIZE
        )
        setupLongClickListener()
        setupClickListener()

        setupSwipeListener(rvShopList)

    }

    private fun setupSwipeListener(rvShopList: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
                    or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteShopItem(adapter.currentList[viewHolder.adapterPosition])
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setupClickListener() {
        adapter.onShopItemClickListener = {
            if (isOnePaneMode()){
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }

        }
    }

    private fun setupLongClickListener() {
        adapter.onShopItemLongClickListener = {
            viewModel.changeEnabledState(it)

        }
    }

    override fun onEditingFinish() {
        Toast.makeText(this,"sdf",Toast.LENGTH_LONG).show()
        supportFragmentManager.popBackStack()
    }

}