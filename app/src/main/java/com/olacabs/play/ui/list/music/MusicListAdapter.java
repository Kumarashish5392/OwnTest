package com.olacabs.play.ui.list.music;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.olacabs.play.R;
import com.olacabs.play.StreamingMp3Player;
import com.olacabs.play.databinding.ItemMusicBinding;
import com.olacabs.play.databinding.ItemMusicEmptyBinding;
import com.olacabs.play.repo.model.Music;
import com.olacabs.play.ui.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private List<Music> mBlogResponseList;

    private BlogAdapterListener mListener;

    public MusicListAdapter(List<Music> blogResponseList) {
        this.mBlogResponseList = blogResponseList;
    }

    public void setListener(BlogAdapterListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemMusicBinding blogViewBinding =
                        ItemMusicBinding.inflate(LayoutInflater.from(parent.getContext()),
                                parent, false);
                return new MusicViewHolder(blogViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemMusicEmptyBinding emptyViewBinding = ItemMusicEmptyBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mBlogResponseList != null && mBlogResponseList.size() > 0) {
            return mBlogResponseList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Music> blogList) {
        mBlogResponseList.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mBlogResponseList.clear();
    }

    public class MusicViewHolder extends BaseViewHolder implements
            MusicItemViewModel.BlogItemViewModelListener {

        private ItemMusicBinding mBinding;

        private MusicItemViewModel mBlogItemViewModel;

        public MusicViewHolder(ItemMusicBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final Music blog = mBlogResponseList.get(position);
            mBlogItemViewModel = new MusicItemViewModel(blog, this);
            mBinding.setViewModel(mBlogItemViewModel);
//            Picasso.with(itemView.getContext()).load(blog.getCover_image()).resize(200, 200).into
//                    (mBinding.imgCover);
            Picasso.with(itemView.getContext()).load(blog.getCover_image()).placeholder(itemView.getContext()
                    .getResources().getDrawable(R.drawable.olaplay_logo)).error(itemView
                    .getContext().getResources().getDrawable(R.drawable.olaplay_logo)).into(mBinding.imgCover);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();

        }

        @Override
        public void onItemClick(String blogUrl) {
            if (blogUrl != null) {
                try {
                    Intent intent = new Intent(itemView.getContext(), StreamingMp3Player.class);
                    intent.putExtra("url", blogUrl);
                    //intent.setData(Uri.parse(blogUrl));
                    itemView.getContext().startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements
            MusicEmptyItemViewModel.MusicEmptyItemViewModelListener {

        private ItemMusicEmptyBinding mBinding;

        public EmptyViewHolder(ItemMusicEmptyBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            MusicEmptyItemViewModel emptyItemViewModel = new MusicEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }

    public interface BlogAdapterListener {
        void onRetryClick();
    }


}