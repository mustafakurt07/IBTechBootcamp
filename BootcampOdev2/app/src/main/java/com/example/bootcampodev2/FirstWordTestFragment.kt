package com.example.bootcampodev2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FirstWordTestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kelime_testi, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var buttonInitial: Button? = view?.findViewById(R.id.buttonInitial)

        var kelime : String = "Visualize"
        val answerArr : Array<String> = arrayOf("Görselleştirmek", "Altında", "Bağış", "Ensülin")

        buttonInitial?.setOnClickListener {
            val intent = Intent(activity, WordTestActivity::class.java)
            intent.putExtra("string", kelime)
            intent.putExtra("answer array", answerArr)
            activity?.startActivity(intent)
            // sending data to activity and starting new activity
        }

    }
}