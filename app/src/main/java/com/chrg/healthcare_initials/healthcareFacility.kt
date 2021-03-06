package com.chrg.healthcare_initials

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_search_doctor.*
import kotlinx.android.synthetic.main.healthcare_info.view.*

class healthcareFacility : AppCompatActivity() {

    var listOfhealthcareFacility = ArrayList<Healthcare>()
    private var adapter:HealthcareAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_doctor)

        listOfhealthcareFacility.add(Healthcare("Nursing Aid", "This healthcare facility focuses on providing good nursing aid/Care not First-aid.", R.drawable.nc))
        listOfhealthcareFacility.add(Healthcare("Nutritional Support", "This facility allows general public to have proper nutritional support", R.drawable.ns))
        listOfhealthcareFacility.add(Healthcare("Home Delivery Meals", "This feature focuses on delivering healthy meals at your door step ", R.drawable.hdm))
        listOfhealthcareFacility.add(Healthcare("Tracking Services", "Tracking of both patient & healthcare services", R.drawable.ts))


        adapter = HealthcareAdapter(this, listOfhealthcareFacility)
        docTypes.adapter = adapter
    }

    class HealthcareAdapter: BaseAdapter {
        var listOfhealthcareFacility = ArrayList<Healthcare>()
        var context: Context?=null
        constructor(context: Context, listOfHealthcare: ArrayList<Healthcare>):super() {
            this.listOfhealthcareFacility = listOfHealthcare
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var healthcare = listOfhealthcareFacility[position]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflater.inflate(R.layout.healthcare_ticket, null)
            myView.tvHealthcareName.text = healthcare.name!!
            myView.tvHealthcareDes.text = healthcare.des!!
            myView.ivHealthcareImage.setImageResource(healthcare.image!!)
            myView.ivHealthcareImage.setOnClickListener {
                val intent = Intent(context, healthcareInfo::class.java)
                intent.putExtra("HCname", healthcare.name!!)
                intent.putExtra("HCdes", healthcare.des!!)
                intent.putExtra("HCimage", healthcare.image!!)
                context!!.startActivity(intent)
            }
//            myView.tvHealthcareName.text = Healthcare.name!!

            return myView
        }

        override fun getItem(position: Int): Any {
            return listOfhealthcareFacility[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfhealthcareFacility.size
        }
    }
}