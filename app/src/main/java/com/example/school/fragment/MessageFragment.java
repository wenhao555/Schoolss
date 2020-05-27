package com.example.school.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.school.AddInfoActivity;
import com.example.school.InfoContextActivity;
import com.example.school.R;
import com.example.school.db.InfoDao;
import com.example.school.model.BaseRecyclerAdapter;
import com.example.school.model.Info;
import com.example.school.model.OnItemClickListener;
import com.example.school.model.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源
 */
public class MessageFragment extends Fragment {

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        addac = view.findViewById(R.id.addac);


        addac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddInfoActivity.class)
                        .putExtra("type", "添加资源"));
            }
        });
        recyvle = view.findViewById(R.id.recyvle);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里获取数据的逻辑
                initDb();
                initData();
                recyvle.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        initDb();
        initData();
        recyvle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        recyvle.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {//点击跳转界面
            @Override
            public void onItemClick(@NonNull int position) {
                Info info = infos.get(position);
                startActivity(new Intent(getActivity(), InfoContextActivity.class)
                        .putExtra("title", info.getTitle())
                        .putExtra("context", info.getContext()));
            }
        });
        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {//长按删除
            @Override
            public void onLongItemClick(@NonNull int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示")
                        .setMessage("是否删除信息")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Info info = infos.get(position);
                                InfoDao infoDao = new InfoDao(getActivity());
                                infoDao.delete(DBNAME, info.getTitle());
                                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                                initDb();
                                initData();
                                recyvle.setAdapter(adapter);
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
        return view;
    }

    private ImageView addac;
    private RecyclerView recyvle;
    private List<Info> infos = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private final String DBNAME = "msginfo";

    private void initDb() {
        InfoDao infoDao = new InfoDao(getActivity());
        infos = infoDao.findAll(DBNAME);

    }

    private BaseRecyclerAdapter adapter;

    private void initData() {
        adapter = new BaseRecyclerAdapter() {
            @Override
            protected void onBindView(@NonNull BaseRecyclerAdapter.BaseViewHolder holder, @NonNull final int position) {
                final Info info = infos.get(position);
                TextView item_title = holder.getView(R.id.item_title);
                TextView item_context = holder.getView(R.id.item_context);
                item_title.setText(info.getTitle());
                item_context.setText(info.getContext());
            }

            @Override
            protected int getLayoutResId(int position) {
                return R.layout.iten_adapter;
            }

            @Override
            public int getItemCount() {
                return infos.size();
            }
        };
    }
}
