package id.sch.smktelkom_mlg.project2.xirpl303131527.mynote;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import id.sch.smktelkom_mlg.project2.xirpl303131527.mynote.db.DatabaseAccess;


/**
 * A simple {@link Fragment} subclass.
 */
public class my extends Fragment {


    public my() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my,
                container, false);


        return rootView;
    }


}
