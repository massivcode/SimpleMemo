package com.prchoe.simplememo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.prchoe.simplememo.R;
import com.prchoe.simplememo.adapter.CustomAdapter;
import com.prchoe.simplememo.database.facade.MemoFacade;
import com.prchoe.simplememo.model.MemoData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private FloatingActionButton mAddMemo;
    private ListView mListView;

    private CustomAdapter mAdapter;
    private List<MemoData> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddMemo = (FloatingActionButton) findViewById(R.id.fab_add_memo);
        mAddMemo.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.list_view_all_memo);
        mAdapter = new CustomAdapter(MainActivity.this, mList);

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_memo:
                startActivity(new Intent(MainActivity.this, AddMemoActivity.class));
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MemoFacade facade = new MemoFacade(MainActivity.this);
        mList = facade.showMemo();

        if (mList != null) {
            mAdapter.changeData(mList);
        }

    }

    // 리스트뷰 아이템 클릭 => 해당 메모 상세보기(startActivity(new Intent(MainActivity.this, ViewSelectedMemoActivity.class)))
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MemoData oldData = (MemoData) mAdapter.getItem(position);
        Intent intent = new Intent(MainActivity.this, ViewSelectedMemoActivity.class);
        intent.putExtra("oldData", oldData);
        startActivity(intent);
    }

    // 리스트뷰 아이템 롱클릭 => 삭제여부 묻는 다이얼로그 출력 후 삭제버튼 누르면 삭제
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("삭제하시겠습니까?");
        builder.setNegativeButton("취소", null);
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MemoData remove = (MemoData) mAdapter.getItem(position);
                MemoFacade facade = new MemoFacade(MainActivity.this);
                boolean result = facade.deleteMemo(remove);

                if (result) {
                    Toast.makeText(MainActivity.this, "삭제하였습니다.", Toast.LENGTH_SHORT).show();
                    mList = facade.showMemo();

                    if (mList != null) {
                        mAdapter.changeData(mList);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();

        return true;
    }
}
