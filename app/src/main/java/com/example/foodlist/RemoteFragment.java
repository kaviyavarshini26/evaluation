
package com.example.foodlist;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteFragment extends Fragment implements BottomsheetClickListnr {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Category> remoteList = new ArrayList<>();
    RemoteAdapter adapter ;


    public RemoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RemoteAdapter(getActivity(), remoteList,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleRetrofit();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote, container, false);
        return view;
    }

    private void handleRetrofit(){
        Call<CategoryResponse> call = RetrofitClient.getInstance().getApi().fetchAllRemote();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    remoteList = response.body().getRemoteList();
                    adapter.setData(remoteList);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText((Context) getActivity(), (CharSequence) response.body(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClicked(String description) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        TextView descriptionTextView = bottomSheetView.findViewById(R.id.desc);
        descriptionTextView.setText(description);

        bottomSheetDialog.show();
    }
}
