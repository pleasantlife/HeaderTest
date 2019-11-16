package com.gandan.headertest;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    private ImageView headerImageView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TestAdapter testAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private int scrollAmount = 0;
    private int imageViewinitHeight = 0;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View testView = inflater.inflate(R.layout.fragment_test, container, false);
        headerImageView = testView.findViewById(R.id.headerImageView);
        recyclerView = testView.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        testAdapter = new TestAdapter(getContext(), list);
        recyclerView.setAdapter(testAdapter);
        headerImageView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        Log.e("imageViewInitHeight", imageViewinitHeight+"");

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                Log.e("dy",dy+"");
                imageViewinitHeight = headerImageView.getMeasuredHeight();
                scrollAmount = scrollAmount+dy;
                ViewGroup.LayoutParams lp = headerImageView.getLayoutParams();
                ConstraintLayout.LayoutParams rlp = (ConstraintLayout.LayoutParams) recyclerView.getLayoutParams();
                lp.height = headerImageView.getMeasuredHeight()-dy;
                Log.e("Height", lp.height+"");
                if(scrollAmount > 480){
                    lp.height = 240;
                    rlp.topMargin = 256;
                    headerImageView.setLayoutParams(lp);
                    recyclerView.setLayoutParams(rlp);
                } else if (scrollAmount < 240) {
                    lp.height = 480;
                    rlp.topMargin = 360;
                    headerImageView.setLayoutParams(lp);
                    recyclerView.setLayoutParams(rlp);
                } else {
                    rlp.topMargin = (int) (lp.height * 0.8);
                    headerImageView.setLayoutParams(lp);
                    recyclerView.setLayoutParams(rlp);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        return testView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for(int i =0; i < 1001; i++){
            String str = i+"번째 아이템";
            list.add(str);
        }



    }
}
