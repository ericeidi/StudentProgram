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

public class MainFragment extends Fragment {
    private OnFragmentItemSelectedListener listener;
    Context thiscontext;

    DatabaseHandler myDb;
    ListAdapter mAdapter;
    SQLiteDatabase mDatabase;


    EditText edtName, edtLastName, edtMark;
    Button buttonSubmit, buttonShow;
    String creditValue, randomCourse = "tchau";
    String course;

    RadioButton rad1, rad2,rad3,rad4;

    Spinner courseList;
    String cList[] = {"PROG 8480", "PROG 8470", "PROG 8460", "PROG 8450"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main,container,false);
        RadioGroup radCredit = (RadioGroup) view.findViewById(R.id.radGroupCredit);
        rad1 = (RadioButton) view. findViewById(R.id.rad1);
        rad2 = (RadioButton) view.findViewById(R.id.rad2);
        rad3 = (RadioButton) view.findViewById(R.id.rad3);
        rad4 = (RadioButton) view.findViewById(R.id.rad4);
        edtName=(EditText)view.findViewById(R.id.edtFirstName);
        edtLastName=(EditText)view.findViewById(R.id.edtLastName);
        edtMark=(EditText)view.findViewById(R.id.edtMark);
        buttonSubmit=(Button)view.findViewById(R.id.btnSubmit);

        final DatabaseHandler dbHelper = new DatabaseHandler(getActivity());

        courseList = (Spinner) view.findViewById(R.id.courseListView);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_list_view, R.id.textView, cList);
        courseList.setAdapter(arrayAdapter);

        courseList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (courseList.getSelectedItemPosition()){
                    case 0:
                        course = "PROG 8480";
                        break;
                    case 1:
                        course = "PROG 8470";
                        break;
                    case 2:
                        course = "PROG 8460";
                        break;
                    case 3:
                        course = "PROG 8450";
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /* ;*/

        radCredit.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                     @Override
                     public void onCheckedChanged(RadioGroup group, int checkedId) {
                         RadioButton rb = (RadioButton) group.findViewById(checkedId);

                         if (rb.getId() == R.id.rad1) {
                             creditValue = "1";
                         }
                         if (rb.getId() == R.id.rad2) {
                             creditValue = "2";
                         }
                         if (rb.getId() == R.id.rad3) {
                             creditValue = "3";
                         }
                         if (rb.getId() == R.id.rad4) {
                             creditValue = "4";
                         }
                     }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.insertData(edtName.getText().toString(), edtLastName.getText().toString(),
                       edtMark.getText().toString(), course, creditValue);

                Toast.makeText(getActivity(), "recorded", Toast.LENGTH_LONG).show();

              /*  Intent intent = new Intent (getActivity(), MainActivity.class);
                startActivity(intent);*/



            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myDb = new DatabaseHandler(context);

        if(context instanceof OnFragmentItemSelectedListener){

            listener = (OnFragmentItemSelectedListener) context;

        }else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface  OnFragmentItemSelectedListener{
        public void onButtonSelected();
    }

}
