package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BrandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BrandFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BrandFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BrandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BrandFragment newInstance(String param1, String param2) {
        BrandFragment fragment = new BrandFragment();
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
        View view = inflater.inflate(R.layout.fragment_brand, container, false);
        Button btn = view.findViewById(R.id.Brand_button);
        EditText et = view.findViewById(R.id.Brand_name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), et.getText().toString(), Toast.LENGTH_SHORT).show();
                DataBase usdbh = new DataBase(getContext(), getString(R.string.Database), null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();
                if(db != null){
                    ContentValues values = new ContentValues();
                    values.put("name",et.getText().toString());
                    db.insert("Brand",null,values);
                    Toast.makeText(getContext(), "dato " + et.getText().toString() + " insertado", Toast.LENGTH_SHORT).show();
                }
                Cursor cursor;
                cursor = db.rawQuery("select name from Brand ", null);
                String cadena,Fin="";
                if (cursor.getCount() != 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            cadena = cursor.getString(cursor
                                    .getColumnIndexOrThrow("name"));
                            Fin += cadena + "-" + "-" + "\n";

                        } while (cursor.moveToNext());
                    }
                    //TV2.setText(Fin);
                }
                cursor.close();
                Toast.makeText(getContext(), Fin, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}