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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        EditText et = view.findViewById(R.id.Product_name);
        Spinner spinner = view.findViewById(R.id.Product_spinner);
        ArrayList<Brand> brands = Brand.getAll(getContext());
        ArrayAdapter<Brand> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,brands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button btn = view.findViewById(R.id.btn_product_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Brand brand = (Brand) spinner.getSelectedItem();
                //System.out.println(brand.getClass());
                Product product = new Product(
                        et.getText().toString(),
                        brand
                );
                //System.out.println(product.getBrand().getClass());
                Product.insert(getContext(), product);
            }
        });
        Button btn_list = view.findViewById(R.id.Product_button_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_list_product);
                ListView lv = dialog.findViewById(R.id.lv_product);
                ArrayList<Product> products = Product.getAll(getContext());
                System.out.println(products.size());
                ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(getContext(), android.R.layout.simple_list_item_1,products);
                lv.setAdapter(adapter);
                dialog.show();
            }
        });
        return view;
    }
}