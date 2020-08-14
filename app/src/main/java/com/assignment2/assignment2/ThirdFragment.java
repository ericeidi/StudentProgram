package com.assignment2.assignment2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ThirdFragment extends Fragment {

    SQLiteDatabase mDatabase;
    ListAdapter mAdapter;
    RecyclerView mRecyclerView ;
    DatabaseHandler databaseHandler;
    Context context;


    String course;
    Spinner courseListView;
    RadioButton radId, radProgram;
    EditText textId;
    Button btnSubmit;
    String cList[] = {"PROG 8480", "PROG 8470", "PROG 8460", "PROG 8450"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third,container,false);
        super.onViewCreated(view, savedInstanceState);
        databaseHandler = new DatabaseHandler(getActivity());
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        courseListView = (Spinner) view.findViewById(R.id.courseListView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_list_view, R.id.textView, cList);
        courseListView.setAdapter(arrayAdapter);

        courseListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (courseListView.getSelectedItemPosition()){
                    case 0:
                        course = "PROG 8480";
                        showRecordCourseList(course);
                        VerifyDataByCourseList(course);
                        break;
                    case 1:
                        course = "PROG 8470";
                        showRecordCourseList(course);
                        VerifyDataByCourseList(course);
                        break;
                    case 2:
                        course = "PROG 8460";
                        showRecordCourseList(course);
                        VerifyDataByCourseList(course);
                        break;
                    case 3:
                        course = "PROG 8450";
                        showRecordCourseList(course);
                        VerifyDataByCourseList(course);

                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RadioGroup radSelect = (RadioGroup) view.findViewById(R.id.radSelectF3);
        radId = (RadioButton) view. findViewById(R.id.radIdF3);
        radProgram = (RadioButton) view.findViewById(R.id.radProgramF3);
        courseListView = (Spinner) view.findViewById(R.id.courseListView);
        textId = (EditText) view.findViewById(R.id.txtIdF3);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmitF3);


        radSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                if (rb.getId() == R.id.radIdF3) {
                    courseListView.setVisibility(View.INVISIBLE);
                    btnSubmit.setVisibility(View.VISIBLE);
                    textId.setVisibility(View.VISIBLE);
                }
                if (rb.getId() == R.id.radProgramF3) {
                    courseListView.setVisibility(View.VISIBLE);
                    btnSubmit.setVisibility(View.INVISIBLE);
                    textId.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecordId(textId.getText().toString());
                VerifyDataById(textId.getText().toString());
            }
        });

        showRecord();
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    private void showRecordId(String id){
        ListAdapter listAdapter = new ListAdapter(getActivity(),databaseHandler.getDatabyId("id", Integer.parseInt(id)));
        mRecyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showRecordCourseList(String course){
        ListAdapter listAdapter = new ListAdapter(getActivity(),databaseHandler.getDatabyCourse("id", course));
        mRecyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void VerifyDataByCourseList(String course){
        databaseHandler.verifyDataByCourse("id", course);
        if (databaseHandler.verifyDataByCourse("id", course) == false){
            Toast.makeText(getActivity(), "No records", Toast.LENGTH_LONG).show();
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    private void VerifyDataById(String id){
        databaseHandler.verifyDataById("id", Integer.parseInt(id));
        if (databaseHandler.verifyDataById("id", Integer.parseInt(id)) == false){
            Toast.makeText(getActivity(), "No records", Toast.LENGTH_LONG).show();
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
    }


    private void showRecord(){
        ListAdapter listAdapter = new ListAdapter(getActivity(),databaseHandler.getAllData("id"));
        mRecyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();
        showRecord();
    }
}
