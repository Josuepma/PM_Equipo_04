package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PriceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PriceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PriceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PriceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PriceFragment newInstance(String param1, String param2) {
        PriceFragment fragment = new PriceFragment();
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
        View view = inflater.inflate(R.layout.fragment_price, container, false);
        EditText et = view.findViewById(R.id.Price_price);
        Spinner spinner_products = view.findViewById(R.id.spinner_price_product);
        ArrayList<Product> products = Product.getAll(getContext());
        ArrayAdapter<Product> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_products.setAdapter(adapter);
        Spinner spinner_stores = view.findViewById(R.id.spinner_price_store);
        ArrayList<Store> stores = Store.getAll(getContext());
        ArrayAdapter<Store> adapter_store = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,stores);
        adapter_store.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_stores.setAdapter(adapter_store);
        if (products.isEmpty())
            Toast.makeText(getContext(), "There are no Products", Toast.LENGTH_SHORT).show();
        if (stores.isEmpty())
            Toast.makeText(getContext(), "There are no Stores", Toast.LENGTH_SHORT).show();
        Button btn = view.findViewById(R.id.btn_price_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spinner_stores.getSelectedItem() == null || spinner_products.getSelectedItem() == null)
                    return ;
                Price.insert(getContext(), new Price(
                        Double.parseDouble(et.getText().toString()),
                        (Store)spinner_stores.getSelectedItem(),
                        null,
                        (Product)spinner_products.getSelectedItem()
                ));
            }
        });

        Button btn_list = view.findViewById(R.id.Price_button_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_list);
                ListView lv = dialog.findViewById(R.id.dialog_lv);
                ArrayList<Price> prices = Price.getAll(getContext());
                if (prices.isEmpty()){
                    Toast.makeText(getContext(), "There are no prices", Toast.LENGTH_SHORT).show();
                    return ;
                }
                ArrayAdapter<Price> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,prices);
                lv.setAdapter(adapter);
                dialog.show();
            }
        });
        return view;
    }
}