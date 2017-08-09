//package com.wpmac.androidnougatframework.adapter;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.wpmac.androidnougatframework.R;
//import com.wpmac.androidnougatframework.base.RecyclerAdapter;
//import com.wpmac.androidnougatframework.base.RecyclerViewHolder;
//
//
///**
// * Created by jingbin on 2016/12/24.
// */
//
//public class EmptyAdapter extends RecyclerAdapter<String> {
//
//
//
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(parent, R.layout.item_empty);
//    }
//
//
//    class ViewHolder extends RecyclerViewHolder<String> {
//
//        public TextView mTextView;
//        public ViewHolder(View view){
//            super(view);
//            mTextView = (TextView) view.findViewById(R.id.text);
//        }
//
//        @Override
//        public void onBindViewHolder(String object, int position) {
//            binding.setString(object);
//        }
//    }
//}
