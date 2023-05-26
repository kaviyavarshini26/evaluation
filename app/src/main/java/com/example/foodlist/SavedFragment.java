package com.example.foodlist;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class SavedFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Category> savedList;


    public SavedFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


// retrofit
        Call<CategoryResponse1> call = RetrofitClient.getInstance().getApi().fetchAllRemote1();
        call.enqueue(new Callback<CategoryResponse1>() {
            @Override
            public void onResponse(Call<CategoryResponse1> call, Response<CategoryResponse1> response) {
                if (response.isSuccessful()) {
                    savedList = response.body().getSavedList();
                    recyclerView.setAdapter(new SavedAdapter(getActivity(), savedList));

                } else {
                    Toast.makeText((Context) getActivity(), (CharSequence) response.body(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<CategoryResponse1> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
