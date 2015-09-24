package com.prchoe.simplememo.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prchoe.simplememo.R;
import com.prchoe.simplememo.database.contract.MemoContract;
import com.prchoe.simplememo.database.facade.MemoFacade;
import com.prchoe.simplememo.model.MemoData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by massivCode on 2015-09-22.
 */
public class AddMemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonCancel, mButtonSave;
    private EditText mEditTextTitle, mEditTextContents;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");

    private MemoFacade mFacade;
    private Intent flag;
    private boolean isModifyProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        mButtonCancel = (Button) findViewById(R.id.button_cancel);
        mButtonSave = (Button) findViewById(R.id.button_save);

        mButtonSave.setOnClickListener(this);
        mButtonCancel.setOnClickListener(this);

        mEditTextContents = (EditText) findViewById(R.id.edit_text_contents);
        mEditTextTitle = (EditText) findViewById(R.id.edit_text_title);

        flag = getIntent();
        isModifyProcess = flag.getBooleanExtra("isModifyProcess", false);

        if(isModifyProcess) {
            MemoData oldData = flag.getParcelableExtra("oldData");
            mEditTextTitle.setText(oldData.getTitle());
            mEditTextContents.setText(oldData.getContents());
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cancel:
                finish();
                break;
            case R.id.button_save:


                if(isModifyProcess == false) {

                    if (mEditTextContents.getText().toString().isEmpty() == false) {

                        mFacade = new MemoFacade(AddMemoActivity.this);

                        String title = mEditTextTitle.getText().toString();
                        String contents = mEditTextContents.getText().toString();
                        String date = mFormat.format(new Date(System.currentTimeMillis()));

                        ContentValues values = new ContentValues();
                        values.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE, title);
                        values.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS, contents);
                        values.put(MemoContract.MemoEntry.COLUMN_NAME_DATE, date);

                        boolean result = mFacade.addMemo(values);

                        if (result) {
                            finish();
                        }
                    } else {
                        Toast.makeText(AddMemoActivity.this, "저장할 내용이 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mFacade = new MemoFacade(AddMemoActivity.this);

                    MemoData memoData = flag.getParcelableExtra("oldData");

                    memoData.setTitle(mEditTextTitle.getText().toString());
                    memoData.setContents(mEditTextContents.getText().toString());

                    boolean result = mFacade.updateMemo(memoData);

                    if(result) {
                        Toast.makeText(AddMemoActivity.this, "수정하였습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }



                break;
        }
    }
}
