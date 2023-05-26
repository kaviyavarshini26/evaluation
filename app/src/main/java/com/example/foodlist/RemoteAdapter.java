package com.example.foodlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RemoteAdapter extends RecyclerView.Adapter<RemoteAdapter.ViewHolder> {



    private List<Category> remoteList;
    BottomsheetClickListnr bottomListner;
//    private List<Category> foodItems;
//    private ApiInterface apiInterface;
    Context context;
//    private AdapterView.OnItemClickListener listener;


    public RemoteAdapter(Context context, List<Category> remoteList, BottomsheetClickListnr listner) {
        this.context=context;
        this.remoteList = remoteList;
        this.bottomListner = listner;
//        this.foodItems=foodItems;

//        this.apiInterface=apiInterface;
    }

    public RemoteAdapter(List<Category> remotelist) {
    }

    void setData(List<Category> remoteList){
        this.remoteList = remoteList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

//        holder.StrId.setText(remoteList.get(position).getIdCategory());
        //   holder.StrThumb.setImageResource(remoteList.get(position).getStrCategoryThumb());
        holder.StrName.setText(remoteList.get(position).getStrCategory());
      holder.StrDesc.setText(remoteList.get(position).getStrCategoryDescription());


        Glide.with(context)

                .load(remoteList.get(position).getStrCategoryThumb())

                .into(holder.StrThumb);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open bottom sheet with description
                bottomListner.onItemClicked(remoteList.get(position).getStrCategoryDescription());
            }
        });
    }



    @Override
    public int getItemCount() {
        return remoteList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView StrThumb;
        TextView StrDesc, StrName, StrId;
        TextView FoodDesc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            StrThumb = itemView.findViewById(R.id.imageview1thumb);
           StrDesc = itemView.findViewById(R.id.textview3desc);
            StrName = itemView.findViewById(R.id.strname);
//            StrId = itemView.findViewById(R.id.strid);

        }


        }

    }

