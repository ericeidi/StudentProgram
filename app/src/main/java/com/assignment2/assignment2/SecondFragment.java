package com.assignment2.assignment2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SecondFragment extends Fragment {

    SQLiteDatabase mDatabase;
    ListAdapter mAdapter;
    RecyclerView mRecyclerView ;
    DatabaseHandler databaseHandler;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        super.onViewCreated(view, savedInstanceState);
        databaseHandler = new DatabaseHandler(getActivity());
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        VerifyData();
        //

        showRecord();
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }


    private void showRecord(){
        ListAdapter listAdapter = new ListAdapter(getActivity(),databaseHandler.getAllData("id"));
        mRecyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void VerifyData(){
        databaseHandler.verifyAllData("id");
        if (databaseHandler.verifyAllData("id") == false){
            Toast.makeText(getActivity(), "No records", Toast.LENGTH_LONG).show();
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        showRecord();
    }
}
