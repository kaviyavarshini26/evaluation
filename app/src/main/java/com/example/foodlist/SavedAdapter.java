package com.example.foodlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ViewHolder> {



    private List<Category> savedList;
    //    private List<Category> foodItems;
//    private ApiInterface apiInterface;
    Context context;
//    private AdapterView.OnItemClickListener listener;


    public SavedAdapter(Context context,List<Category> savedList) {
        this.context=context;
        this.savedList = savedList;
//        this.foodItems=foodItems;

//        this.apiInterface=apiInterface;
    }

    public SavedAdapter(List<Category> savedlist) {
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.item_design1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.StrId.setText(remoteList.get(position).getIdCategory());
        //   holder.StrThumb.setImageResource(remoteList.get(position).getStrCategoryThumb());
        // holder.StrName.setText(remoteList.get(position).getStrCategory());
        holder.StrDesc.setText(savedList.get(position).getStrCategoryDescription());


        Glide.with(context)

                .load(savedList.get(position).getStrCategoryThumb())

                .into(holder.StrThumb);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open bottom sheet with description
//                openBottomSheet(Category.getStrCategoryDescription());
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return savedList.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView StrThumb;
        TextView StrDesc, StrName, StrId;
        TextView FoodDesc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            StrThumb = itemView.findViewById(R.id.imageview1thumb);
            StrDesc = itemView.findViewById(R.id.textview3desc);
//            StrName = itemView.findViewById(R.id.strname);
//            StrId = itemView.findViewById(R.id.strid);

        }


    }
//    private void openBottomSheet(String description) {
//        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
//        View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.boottom_sheet_layout, null);
//        bottomSheetDialog.setContentView(bottomSheetView);
//
//        TextView descriptionTextView = bottomSheetView.findViewById(R.id.descriptionTextView);
//        descriptionTextView.setText(description);
//
//        bottomSheetDialog.show();
//
//    }
}
