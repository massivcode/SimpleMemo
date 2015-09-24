package com.prchoe.simplememo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.prchoe.simplememo.R;
import com.prchoe.simplememo.model.MemoData;

/**
 * Created by massivCode on 2015-09-23.
 */
public class ViewSelectedMemoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextViewTitle, mTextViewContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_memo);

        mTextViewTitle = (TextView)findViewById(R.id.text_view_title);
        mTextViewContents = (TextView)findViewById(R.id.text_view_contents);

        MemoData oldData = getIntent().getParcelableExtra("oldData");

        mTextViewTitle.setText(oldData.getTitle());
        mTextViewContents.setText(oldData.getContents());

        mTextViewTitle.setOnClickListener(this);
        mTextViewContents.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent flag = new Intent(ViewSelectedMemoActivity.this, AddMemoActivity.class);
        flag.putExtra("isModifyProcess", true);
        flag.putExtra("oldData", getIntent().getParcelableExtra("oldData"));

        startActivity(flag);
        ViewSelectedMemoActivity.this.finish();
    }
}
