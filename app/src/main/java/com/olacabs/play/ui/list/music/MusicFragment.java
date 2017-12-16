package com.olacabs.play.ui.list.music;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.olacabs.play.R;
import com.olacabs.play.BR;
import com.olacabs.play.databinding.FragmentMusicBinding;
import com.olacabs.play.repo.model.Music;
import com.olacabs.play.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class MusicFragment extends BaseFragment<FragmentMusicBinding, MusicViewModel>
        implements MusicNavigator, MusicListAdapter.BlogAdapterListener {


    public static final String TAG = "MusicFragment";

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    MusicListAdapter mBlogAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    FragmentMusicBinding mFragmentBlogBinding;
    private MusicViewModel mBlogViewModel;

    public static MusicFragment newInstance() {
        Bundle args = new Bundle();
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBlogViewModel.setNavigator(this);
        mBlogAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentBlogBinding = getViewDataBinding();
        setUp();
        subscribeToLiveData();
    }

    @Override
    public MusicViewModel getViewModel() {
        mBlogViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(MusicViewModel.class);
        return mBlogViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_music;
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentBlogBinding.blogRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentBlogBinding.blogRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentBlogBinding.blogRecyclerView.setAdapter(mBlogAdapter);
    }

    private void subscribeToLiveData() {
        mBlogViewModel.getBlogListLiveData().observe(this, new Observer<List<Music>>() {
            @Override
            public void onChanged(@Nullable List<Music> blogs) {
                mBlogViewModel.addMusicItemsToList(blogs);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void updateBlog(List<Music> blogList) {
        mBlogAdapter.addItems(blogList);
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onRetryClick() {
        mBlogViewModel.fetchMusic();
    }

}
