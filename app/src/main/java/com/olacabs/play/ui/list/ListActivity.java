package com.olacabs.play.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.olacabs.play.BR;
import com.olacabs.play.R;
import com.olacabs.play.databinding.ActivityListBinding;
import com.olacabs.play.ui.base.BaseActivity;
import com.olacabs.play.ui.list.music.MusicFragment;
import com.olacabs.play.utils.ViewUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Generated by Dipendra on 16-12-2017.
 */
public class ListActivity extends BaseActivity<ActivityListBinding, ListViewModel>
        implements IListNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ListViewModel listViewModel;

    @Inject
    ViewUtils customAnimation;

    private ActivityListBinding activityListBinding;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ListActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection ConstantConditions
        listViewModel.setNavigator(this);
        activityListBinding = getViewDataBinding();
        setUp();
        showMusicFragment();
    }

    private void setUp() {
    }

    @Override
    protected void onDestroy() {
        onFragmentDetached(MusicFragment.TAG);
        super.onDestroy();
    }


    @Override
    public ListViewModel getViewModel() {
        return listViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list;
    }


    @Override
    public void handleError(Throwable throwable) {

    }

    private void showMusicFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.musicRecyclerView, MusicFragment.newInstance(), MusicFragment.TAG)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }


    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow();
        }
    }

}
