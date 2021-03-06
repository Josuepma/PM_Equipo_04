package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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
    private ArrayAdapter<Brand> adapter = null;

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
        if (brands.isEmpty())
            Toast.makeText(getContext(), "There are no Brands", Toast.LENGTH_SHORT).show();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,brands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        Button btn = view.findViewById(R.id.btn_product_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Brand brand = (Brand) spinner.getSelectedItem();
                if(et.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (brand == null)return;
                Product product = new Product(
                        et.getText().toString(),
                        brand
                );
                Product.insert(getContext(), product);
                getActivity().recreate();
            }
        });
        Button btn_list = view.findViewById(R.id.Product_button_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_list);
                ListView lv = dialog.findViewById(R.id.dialog_lv);
                ArrayList<Product> products = Product.getAll(getContext());
                System.out.println(products.size());
                ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(getContext(), android.R.layout.simple_list_item_1,products);
                lv.setAdapter(adapter);
                dialog.show();
            }
        });

        Button btn_graphic = view.findViewById(R.id.btn_product_graphic);
        btn_graphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarChart bchart = view.findViewById(R.id.chart);
                ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
                for (int i = 0; i < 10 + 1; i++) {
                    float val = (float) (Math.random());
                    yVals1.add(new BarEntry(i, val));
                }
                BarDataSet set1;
                set1 = new BarDataSet(yVals1, "The year 2017");
                set1.setColors(ColorTemplate.MATERIAL_COLORS);
                ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
                dataSets.add(set1);
                BarData data = new BarData(dataSets);
                data.setValueTextSize(10f);
                data.setBarWidth(0.9f);
                bchart.setTouchEnabled(true);
                bchart.setData(data);
                bchart.animateXY(2000, 2000);
                bchart.invalidate();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        adapter.notifyDataSetChanged();
    }
}