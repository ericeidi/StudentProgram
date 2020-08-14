package com.assignment2.assignment2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    private List<ItemsAdapter> mList;
    Cursor cursor;

    public ListAdapter(Context context, List<ItemsAdapter> mList) {
        this.context = context;
        this.mList =  mList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        //ViewHolder viewHolder = new ViewHolder(v);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);

        //return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ItemsAdapter model = mList.get(position);
        Integer id = model.getId();
        String name = model.getName();
        String lastName = model.getLastName();
        String marks = model.getMarks();
        String course = model.getCourse();
        String credit = model.getCredit();

       ViewHolder.mTextId.setText(id.toString());
        ViewHolder.mTextName.setText(name);
        ViewHolder.mTextLastName.setText(lastName);
        ViewHolder.mTextMarks.setText(marks);
        ViewHolder.mTextCourse.setText(course);
        ViewHolder.mTextCredit.setText(credit);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public static TextView mTextId;
        public static TextView mTextName;
        public static TextView mTextLastName;
        public static TextView mTextMarks;
        public static TextView mTextCourse;
        public static TextView mTextCredit;
        public ViewHolder(View itemView){
            super(itemView);
            mTextId = (TextView) itemView.findViewById(R.id.txtId);
            mTextName = (TextView) itemView.findViewById(R.id.txtNamer);
            mTextLastName = (TextView) itemView.findViewById(R.id.txtLastNamer);
            mTextMarks = (TextView) itemView.findViewById(R.id.txtMarksr);
            mTextCourse = (TextView) itemView.findViewById(R.id.txtCourser);
            mTextCredit = (TextView) itemView.findViewById(R.id.txtCreditr);
        }
    }
}
