
package com.example.foodlist;
import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.room.Room;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteFragment extends Fragment implements BottomsheetClickListnr {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<Category> remoteList = new ArrayList<>();

    RemoteAdapter adapter;
    AppDatabase database ;
    private ItemDao itemDao;

    public RemoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RemoteAdapter((Context) getActivity(), remoteList, (BottomsheetClickListnr) this);
        recyclerView.setAdapter(adapter);
//        database = AppDatabase.getInstance(requireContext());
//        itemDao = database.itemDao();
//        database = ((MyApp) requireActivity().getApplication()).getDatabase();
//        itemDao = database.itemDao();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        database= Room.databaseBuilder(getActivity(),AppDatabase.class,"app_database")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        itemDao = database.itemDao();
//       database = ((MyApp) requireActivity().getApplication()).getDatabase();
//        itemDao = database.itemDao();
        handleRetrofit();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote, container, false);
        return view;
    }

    private void handleRetrofit() {
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
        descriptionTextView.setText(description); // show remoteList.get(position).getStrCategoryDescription()

        Button addButton = bottomSheetView.findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItemToDatabase(description); // pass remoteList.get(position)

                Toast.makeText(getActivity(), "Items added successfully", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.show();
    }

    private void insertItemToDatabase(String itemName) {
        ItemEntity item = new ItemEntity(itemName); // remoteList.get(position)
//        AppDatabase database = ((MyApp) requireActivity().getApplication()).getDatabase();
//        itemDao = database.itemDao();
        itemDao.insertItem(item);
    }
}


