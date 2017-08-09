package com.wpmac.template.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wpmac.template.R;
import com.wpmac.template.bean.GankBean;
import com.wpmac.template.utils.ImgLoadUtil;
import com.wpmac.template.utils.PerfectClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by Administrator on 2017/7/4.
 */
public class EveryDayTwoViewBinder extends ItemViewBinder<GankBean.ResultsBean.ContentBean, EveryDayTwoViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_everyday_two, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull GankBean.ResultsBean.ContentBean everyDayTwo) {
        List<GankBean.ResultsBean.ContentBean.SimpleBean> object = everyDayTwo.getMSimpleBeanList();
        displayRandomImg(2, 0, holder.iv_two_one_one, object);
        displayRandomImg(2, 1, holder.iv_two_one_two, object);
        setDes(object, 0, holder.tv_two_one_one_title);
        setDes(object, 1, holder.tv_two_one_two_title);
        setOnClick(holder.ll_two_one_one, object.get(0));
        setOnClick(holder.ll_two_one_two, object.get(1));

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_two_one_one)
        ImageView iv_two_one_one;
        @BindView(R.id.tv_two_one_one_title)
        TextView tv_two_one_one_title;
        @BindView(R.id.ll_two_one_one)
        LinearLayout ll_two_one_one;

        @BindView(R.id.iv_two_one_two)
        ImageView iv_two_one_two;
        @BindView(R.id.tv_two_one_two_title)
        TextView tv_two_one_two_title;
        @BindView(R.id.ll_two_one_two)
        LinearLayout ll_two_one_two;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setDes(List<GankBean.ResultsBean.ContentBean.SimpleBean> object, int position, TextView textView) {
        textView.setText(object.get(0).getDesc());
    }

    private void displayRandomImg(int imgNumber, int position, ImageView imageView, List<GankBean.ResultsBean.ContentBean.SimpleBean> object) {
//        DebugUtil.error("-----Image_url: "+object.get(position).getImage_url());
        ImgLoadUtil.displayRandom(imgNumber, object.get(position).getImageUrl(), imageView);
    }


    private void setOnClick(final LinearLayout linearLayout, final GankBean.ResultsBean.ContentBean.SimpleBean bean) {
        linearLayout.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
//                WebViewActivity.loadUrl(v.getContext(), bean.getUrl(), "加载中...");
            }
        });

        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                View view = View.inflate(v.getContext(), R.layout.title_douban_top, null);
//                TextView titleTop = (TextView) view.findViewById(R.id.title_top);
//                titleTop.setTextSize(14);
//                String title = TextUtils.isEmpty(bean.getType()) ? bean.getDesc() : bean.getType() + "：  " + bean.getDesc();
//                titleTop.setText(title);
//                builder.setCustomTitle(view);
//                builder.setPositiveButton("查看详情", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        WebViewActivity.loadUrl(linearLayout.getContext(), bean.getUrl(), "加载中...");
//                    }
//                });
//                builder.show();
                return false;
            }

        });

    }
}
