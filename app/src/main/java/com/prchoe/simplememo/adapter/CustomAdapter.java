package com.prchoe.simplememo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prchoe.simplememo.R;
import com.prchoe.simplememo.model.MemoData;

import java.util.Collections;
import java.util.List;

/**
 * Created by massivCode on 2015-09-22.
 */
public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<MemoData> mList;

    public CustomAdapter(Context context, List<MemoData> list) {
        mContext = context;

        if(list == null) {
            mList = Collections.EMPTY_LIST;
        } else {
            mList = list;
        }
    }

    public void changeData(List<MemoData> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
            return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_all_memo, parent, false);

            viewHolder.Title = (TextView)convertView.findViewById(R.id.item_title);
            viewHolder.Contents = (TextView)convertView.findViewById(R.id.item_contents);
            viewHolder.Date = (TextView)convertView.findViewById(R.id.item_date);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        MemoData memoData = mList.get(position);

        viewHolder.Title.setText(memoData.getTitle());
        viewHolder.Contents.setText(memoData.getContents());
        viewHolder.Date.setText(memoData.getDate());

        return convertView;
    }
    static class ViewHolder {
        TextView Title;
        TextView Contents;
        TextView Date;
    }
}
