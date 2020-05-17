package com.salesianostriana.keepdam

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.salesianostriana.keepdam.common.MyApp

import com.salesianostriana.keepdam.models.Nota
import com.salesianostriana.keepdam.viewmodel.NotasViewModel
import javax.inject.Inject

class NotasFragment : Fragment() {

    private var columnCount = 1
    @Inject
    lateinit var notasViewModel: NotasViewModel
    private lateinit var notasAdapter: MyNotasRecyclerViewAdapter
    private var notas: List<Nota> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as MyApp).getApplicationComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notas_list, container, false)
        notasAdapter = MyNotasRecyclerViewAdapter()
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = notasAdapter
            }
        }

        notasViewModel.getAllNotas().observe(viewLifecycleOwner, Observer {
            if(it!=null){
                notas = it
                notasAdapter.setData(notas)
            }
        })
        return view
    }

}
