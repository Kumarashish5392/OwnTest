package com.olacabs.play.ui.list;

import com.olacabs.play.repo.IDataManager;
import com.olacabs.play.ui.base.BaseViewModel;
import com.olacabs.play.utils.rx.ISchedulerProvider;

/**
 * Generated by Dipendra on 16-12-2017 - 12:52 AM.
 */
public class ListViewModel extends BaseViewModel<IListNavigator> {


    public ListViewModel(IDataManager dataManager,
                         ISchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


}