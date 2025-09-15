package com.example.finalapp.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalapp.R
import com.example.finalapp.data.models.Entity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val entity = arguments?.getParcelable<Entity>("entity")

        val txtField = view.findViewById<TextView>(R.id.txtField)
        val txtConcept = view.findViewById<TextView>(R.id.txtConcept)
        val txtScientist = view.findViewById<TextView>(R.id.txtScientist)

        txtField.text = entity?.field ?: ""
        txtConcept.text = entity?.concept ?: ""
        txtScientist.text = entity?.scientist ?: ""
    }
}
