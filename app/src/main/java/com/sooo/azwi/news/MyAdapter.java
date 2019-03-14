package com.sooo.azwi.news;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<NewsData> mDataset;
    private static View.OnClickListener onClickListener;
    Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_title;
        public TextView TextView_contents;
        public SimpleDraweeView draweeView;

        public View rootView;

        public MyViewHolder(View v) {
            super(v);
            TextView_title = v.findViewById(R.id.TextView_title);
            TextView_contents = v.findViewById(R.id.TextView_contents);
            draweeView = v.findViewById(R.id.ImageView_title);
            rootView = v;

            v.setClickable(true); // 누를수 있다없다
            v.setEnabled(true); // 활성화 상태
            v.setOnClickListener(onClickListener);
        }
    }

    //     Provide a suitable constructo
//r (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset, Context context, View.OnClickListener onClick) {
        mDataset = myDataset;
        Fresco.initialize(context);
        onClickListener = onClick;
    }
//    public MyAdapter(Context context) {
//        this.context = context;
//    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NewsData news = mDataset.get(position);
        holder.TextView_title.setText(news.getTitle());

        String content = news.getContent();
        if (content != null && content.length() > 0) {
            holder.TextView_contents.setText(content);
        }
        Uri uri = Uri.parse(news.getUrlToImage());
        holder.draweeView.setImageURI(uri);

        // tag - label
        holder.rootView.setTag(position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        // 삼항 연산자
        return mDataset == null ? 0 : mDataset.size();
    }

    public NewsData getNews(int position) {
        return mDataset == null ? mDataset.get(position) : null;
    }
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
    //f341abb34e0149c093c09114771a9832
}
