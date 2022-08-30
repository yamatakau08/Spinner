package com.example.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        // spinner 1
        // items are specified by "entries" in xml
        val sp1 = findViewById<Spinner>(R.id.sp1)

        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            val func = "sp1 onItemSelectedListener"
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                Log.d(TAG,"$func onItemSelected is called!")
                val selected = sp1.selectedItem
                Log.d(TAG, "$func onItemSelected: $selected pos: $pos id: $id")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG,"$func onNothingSelected called!")
            }
        }

        //
        // spinner 2
        // items are specified by code
        val sp2 = findViewById<Spinner>(R.id.sp2)

        // Adapter
        val spinnerItems = arrayOf("Main Zone", "Zone 2", "Zone 3", "Zone 4")
        val adapter2 = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,spinnerItems)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Adapter を spinner にセット
        sp2.adapter = adapter2

        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            val func = "sp2 onItemSelectedListener"
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                Log.d(TAG,"$func onItemSelected is called!")
                val selected = sp2.selectedItem
                Log.d(TAG, "$func onItemSelected: $selected pos: $pos id: $id")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG,"$func onNothingSelected called!")
            }
        }

        //
        // spinner 3
        // items are specified by code and modifiable
        //
        val sp3 = findViewById<Spinner>(R.id.sp3)

        // Adapter
        val adapter3 = ArrayAdapter<String>(applicationContext,android.R.layout.simple_spinner_item) // need to specify type <String> in this case, and don't specify the the 3rd argument
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Adapter を spinner にセット
        sp3.adapter = adapter3

        sp3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            val func = "sp3 onItemSelectedListener"
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                Log.d(TAG,"$func onItemSelected is called!")
                val selected = sp2.selectedItem
                Log.d(TAG, "$func onItemSelected: $selected pos: $pos id: $id")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG,"$func onNothingSelected called!")
            }
        }

        // update the spinner items
        listOf("Main Zone","Zone 1","Zone 2").forEach {
            adapter3.add(it) // update the spinner items
        }
        adapter3.notifyDataSetChanged() // seems to need to call after changed the items of spinner ?

        //
        // spinner 4
        // refer https://shrayanbajracharya.medium.com/how-to-create-a-spinner-with-custom-object-1df80476ecc7
        //
        data class TunerPresetClass(
            val id: Int = 0,
            val name: String = ""
        ){
            override fun toString(): String {
                //return name
                return "$id $name"
            }
        }

        val spTunerList = findViewById<Spinner>(R.id.sp4)
        //spTunerList.visibility = View.INVISIBLE // firstly set invisible
        // Init Adapter
        val spAdapterTunerList = ArrayAdapter<TunerPresetClass>(this, android.R.layout.simple_spinner_item)
        spAdapterTunerList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spTunerList.adapter = spAdapterTunerList

        // Item Select Listener
        spTunerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // You can define you actions as you want
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, id: Long) {
                val func = "spTunerList onItemSelected"
                Log.d(TAG, "$func is called")
                val selected = spTunerList.selectedItem as TunerPresetClass
                Log.d(TAG, "$func onItemSelected: $selected selected id: ${selected.id} pos: $pos id: $id")
            }
        }

        listOf<TunerPresetClass>(
            TunerPresetClass(1,"80.00MHz"),
            TunerPresetClass(2,"81.30MHz"),
            TunerPresetClass(3,"89.70MHz"),
            TunerPresetClass(4,"80.00MHz")
        ).forEach {
            spAdapterTunerList.add(it)
        }
        spAdapterTunerList.notifyDataSetChanged() // seems to need to call after changed the items of spinner ?

    }

    companion object {
        const val TAG = "MainActivity"
    }

}