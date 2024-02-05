package com.example.baselistactivity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.baselistactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var array = arrayListOf<String>("Android class", "class", "11-1")
    var baseAdapterClass: BaseAdapterClass = BaseAdapterClass(array)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listView.adapter = baseAdapterClass

       binding.listView.setOnItemClickListener { parent, view, position, id ->
           Toast.makeText(this, "Item clicked $position", Toast.LENGTH_SHORT).show()
           array.set(position, "This is Updated")
           baseAdapterClass.notifyDataSetChanged()
       }
        binding.listView.setOnItemLongClickListener { parent, view, position, id ->

            AlertDialog.Builder(this)
                .setTitle("Are you sure to remove")
                .setPositiveButton("yes"){_,_->
                    array.removeAt(position)
                    baseAdapterClass.notifyDataSetChanged()
                    Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){_,_->
                    Toast.makeText(this, "Can't remove ", Toast.LENGTH_SHORT).show()
                }
                .show()

            return@setOnItemLongClickListener true
        }
        binding.fab.setOnClickListener {
//            array.add("Adapter class practice")
//            baseAdapterClass.notifyDataSetChanged()

            var dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog)
            var tvUpdate: TextView = dialog.findViewById(R.id.tvUpdate)
            var etUpdate: EditText = dialog.findViewById(R.id.etUpdate)
            var btnUpdate: Button = dialog.findViewById(R.id.btnUpdate)

            btnUpdate.setOnClickListener{
                 if (etUpdate.text.toString().trim().isNullOrEmpty()) {
                     etUpdate.error = "Enter Updated Value"
                 }else{

                     array.add(etUpdate.text.toString())
                     baseAdapterClass.notifyDataSetChanged()
                     dialog.cancel()
                 }
            }
            dialog.show()
        }

    }
}