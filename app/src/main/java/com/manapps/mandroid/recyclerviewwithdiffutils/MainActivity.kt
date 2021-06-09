package com.manapps.mandroid.recyclerviewwithdiffutils

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.manapps.mandroid.recyclerviewwithdiffutils.databinding.ActivityMainBinding

//RecyclerView In Kotlin With DiffUtils
class MainActivity : AppCompatActivity(), RecyclerViewItemClickListenerInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewItemAdapter: RecyclerViewItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindings()
        setUpRecyclerView()
        sendRecyclerViewDataRequest()
    }

    private fun initBindings() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.setLayoutManager(linearLayoutManager)
        recyclerViewItemAdapter = RecyclerViewItemAdapter(this, this)
    }

    private fun sendRecyclerViewDataRequest() {
        //assume you are getting data from api or local storage by function getDataList
        val dataModelList = getDataList()
        showRecyclerViewData(dataModelList)
    }

    private fun showRecyclerViewData(dataModelList: MutableList<DataModel>) {
        recyclerViewItemAdapter = RecyclerViewItemAdapter(this, this)
        recyclerViewItemAdapter.submitList(dataModelList)
        binding.recyclerView.setAdapter(recyclerViewItemAdapter)
        recyclerViewItemAdapter.notifyDataSetChanged()
    }

    private fun getDataList(): MutableList<DataModel> {
        val dataModelList: MutableList<DataModel> = mutableListOf()

//this is the format of data we are going to recieve
        // you can edit the data according to your needs
        // fetch dataList from DataResponse(apiResponse)
        // dataModelList=  dataResponse.getDataList();
//        {
//            "dataList": [
//            {
//                "title": "Item1",
//                    "photoUrl": "photoUrl1"
//            },
//            {
//                "title": "Item2",
//                    "photoUrl": "photoUrl2"
//            }
//  ]
//        }
        val dataModel1 = DataModel("Item1", "PhotoUrl1")
        dataModelList.add(dataModel1)
        val dataModel2 = DataModel("Item2", "PhotoUrl2")
        dataModelList.add(dataModel2)
        val dataModel3 = DataModel("Item3", "PhotoUrl3")
        dataModelList.add(dataModel3)
        val dataModel4 = DataModel("Item4", "PhotoUrl4")
        dataModelList.add(dataModel4)
        val dataModel5 = DataModel("Item5", "PhotoUrl5")
        dataModelList.add(dataModel5)
        return dataModelList
    }

    override fun recyclerViewItemClickListener(position: Int) {
        Toast.makeText(this, position.toString().plus(": item clicked!!"), Toast.LENGTH_SHORT)
            .show()
    }

}