package com.example.bookingcardriver.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookingcardriver.R
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

}
/*
fun main () {

    val dateStart = "2020-03-25T07:23:15.954223Z"
    var dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    dateFormatter.timeZone = TimeZone.getTimeZone("UTC")
    val d1 = dateFormatter.parse(dateStart)
    val d2 = Date()
    //in milliseconds
    val diff = d2.time - d1!!.time

    val diffMinutes: Long = diff / (60 * 1000)
    val diffHours: Long = diff / (60 * 60 * 1000)
    val diffDays: Long = diff / (24 *60 *60 * 1000)
    val diffWeek: Long = diff / (604800 * 1000 )
    val diffMonth: Long = diff/ (2592000000)
    val diffYear: Long = diff/ (31536000000)
    if(diffYear > 0){
        println("$diffYear years ago")
    }
    else if (diffMonth > 0){
        println("$diffMonth months ago")
    }
    else if (diffWeek > 0 ){
        println("$diffWeek week ago")
    }
    else if (diffDays > 0){
        println("$diffDays days ago" )
    }
    else if (diffHours > 0) {
        println("$diffHours hours ago")
        //return "$diffHours hours ago"
    }
    else if (diffMinutes > 0) {
        println( "$diffMinutes minute ago")
        // return "Just now"
    }
    else {
        println( "$diff second ago")
    }



}*/
