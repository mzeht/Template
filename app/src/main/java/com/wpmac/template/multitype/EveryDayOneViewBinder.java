package com.wpmac.template.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wpmac.template.R;
import com.wpmac.template.bean.GankBean;
import com.wpmac.template.utils.ImgLoadUtil;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/7/4.
 */
public class EveryDayOneViewBinder extends ItemViewBinder<GankBean.ResultsBean.ContentBean, EveryDayOneViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_everyday_one, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GankBean.ResultsBean.ContentBean everyDayOne) {
        if (everyDayOne.getMSimpleBeanList().get(0).getType().equals("福利")) {
            holder.iv_one_photo.setVisibility(View.GONE);
            holder.iv_one_photo.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                ImgLoadUtil.displayEspImage(object.get(0).getUrl(), binding.ivOnePhoto, 1);
            Glide.with(holder.iv_one_photo.getContext())
                    .load(everyDayOne.getMSimpleBeanList().get(0).getUrl())
                    .crossFade(1500)
                    .placeholder(R.drawable.img_two_bi_one)
                    .error(R.drawable.img_two_bi_one)
                    .into(holder.iv_one_photo);
        }else{
            holder.tv_one_photo_title.setVisibility(View.VISIBLE);
//            setDes(object, 0, binding.tvOnePhotoTitle);
            displayRandomImg(1, 0, holder.iv_one_photo, everyDayOne);
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_one_photo;
        private View ll_one_photo_line;
        private TextView tv_one_photo_title;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_one_photo = (ImageView) itemView.findViewById(R.id.iv_one_photo);
            this.ll_one_photo_line = itemView.findViewById(R.id.ll_one_photo_line);
            this.tv_one_photo_title = (TextView) itemView.findViewById(R.id.tv_one_photo_title);

        }
    }

    private void displayRandomImg(int imgNumber, int position, ImageView imageView, GankBean.ResultsBean.ContentBean object) {
//        DebugUtil.error("-----Image_url: "+object.get(position).getImage_url());
        ImgLoadUtil.displayRandom(imgNumber, object.getMSimpleBeanList().get(position).getImageUrl(), imageView);
    }
}
