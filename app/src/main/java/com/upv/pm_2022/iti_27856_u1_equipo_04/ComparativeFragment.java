package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComparativeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComparativeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComparativeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComparativeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComparativeFragment newInstance(String param1, String param2) {
        ComparativeFragment fragment = new ComparativeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comparative, container, false);
        Spinner spinner_prices_1 = view.findViewById(R.id.spinner_comparative_price_1);
        ArrayList<Price> prices = Price.getAll(getContext());
        ArrayAdapter<Price> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,prices);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_prices_1.setAdapter(adapter);
        Spinner spinner_prices_2 = view.findViewById(R.id.spinner_comparative_price_2);
        spinner_prices_2.setAdapter(adapter);
        if (prices.isEmpty()){
            Toast.makeText(getContext(), "There are no prices", Toast.LENGTH_SHORT).show();
        }
        Button btn_do = view.findViewById(R.id.btn_comprative_do);
        TextView tv = view.findViewById(R.id.tv_comparative_result);
        btn_do.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner_prices_1.getSelectedItem() == null || spinner_prices_2.getSelectedItem() == null) return;
                Price price1 = (Price) spinner_prices_1.getSelectedItem();
                Price price2 = (Price) spinner_prices_2.getSelectedItem();
                Comparative comparative = new Comparative(price1,price2);
                tv.setText("difference = " + comparative.getDifference());
            }
        });
        Button btn_add = view.findViewById(R.id.btn_comparative_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner_prices_1.getSelectedItem() == null || spinner_prices_2.getSelectedItem() == null) return;
                Price price1 = (Price) spinner_prices_1.getSelectedItem();
                Price price2 = (Price) spinner_prices_2.getSelectedItem();
                Comparative comparative = new Comparative(price1,price2);
                Comparative.insert(getContext(),comparative);
            }
        });

        Button btn_show = view.findViewById(R.id.btn_comparative_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_list);
                ListView lv = dialog.findViewById(R.id.dialog_lv);
                ArrayList<Comparative> comparatives = Comparative.getAll(getContext());
                if (comparatives.isEmpty()){
                    Toast.makeText(getContext(), "There are no Comparatives", Toast.LENGTH_SHORT).show();
                    return ;
                }
                ArrayAdapter<Comparative> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,comparatives);
                lv.setAdapter(adapter);
                dialog.show();
            }
        });
        return view;
    }
}