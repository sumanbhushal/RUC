package com.ruc.bhushalsuman.ruc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bhushal Suman on 4/14/2016.
 */
public class ScheduleListAdapter extends ArrayAdapter<FetchRecords> {

    private LayoutInflater inflater;

    public ScheduleListAdapter(Context context, List<FetchRecords> fRecords) {
        super(context, R.layout.class_schedule, fRecords);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if(view == null){
            view = inflater.inflate(R.layout.class_schedule,parent,false);
            holder = new ViewHolder();
            holder.tvTime = (TextView) view.findViewById(R.id.tv_time);
            holder.tvModule = (TextView) view.findViewById(R.id.tv_module);
            holder.tvLecturer = (TextView) view.findViewById(R.id.tv_lecturer);
            holder.tvBlock = (TextView) view.findViewById(R.id.tv_block);
            holder.tvClass = (TextView) view.findViewById(R.id.tv_class);
            view.setTag(holder);
        }
        //getting the data item associated with specific positon
        FetchRecords fetchRecords = getItem(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvTime.setText(fetchRecords.getTime());
        viewHolder.tvModule.setText(fetchRecords.getModule());
        viewHolder.tvLecturer.setText(fetchRecords.getLecturer());
        viewHolder.tvBlock.setText(fetchRecords.getBlock());
        viewHolder.tvClass.setText(fetchRecords.getClass_Room());
        return view;
    }

    static class ViewHolder{
        TextView tvTime;
        TextView tvModule;
        TextView tvLecturer;
        TextView tvBlock;
        TextView tvClass;
    }
}

