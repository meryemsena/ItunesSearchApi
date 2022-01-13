package com.sena.itunes.ui.main;
import android.os.Build;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.tabs.TabLayout;
import com.sena.itunes.data.repository.MobileRepository;
import com.sena.itunes.data.utils.Status;
import com.sena.itunes.model.ResultContent;
import com.sena.itunes.ui.base.BaseNavigator;
import com.sena.itunes.ui.base.BaseViewModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sena KILIÇ on 1/9/2022.
 */

public class MainViewModel extends BaseViewModel<BaseNavigator> {
    private MobileRepository mobileRepository;
    private MutableLiveData<ResultContent> contentMutableLiveData;
    private ResultContent mResultContent;
    private ObservableBoolean pagingMode = new ObservableBoolean(), isPreview = new ObservableBoolean();
    private ObservableField<String> track = new ObservableField<>(), imageUrl = new ObservableField<>(), artist = new ObservableField<>(),
                                    kind = new ObservableField<>(), collection = new ObservableField<>(), previewUrl = new ObservableField<>(),
                                    tab1 = new ObservableField<>(), tab2 = new ObservableField<>(), tab3 = new ObservableField<>();
    private ObservableField<Integer> page = new ObservableField<>();
    static int offset = 0;
    static String mQuery = "";

    @Inject
    public MainViewModel(MobileRepository mobileRepository) {
        this.mobileRepository = mobileRepository;
    }

    public SearchView.OnQueryTextListener getQueryTextListener(){
        return new SearchView.OnQueryTextListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public boolean onQueryTextSubmit(String query) {
                mQuery = query;
                page.set(1);
                getCompositeDisposable().add(mobileRepository.search(query,offset,10).subscribe(searchResultResource -> {
                    if (searchResultResource.status == Status.SUCCESS) {
                        ResultContent resultContent = new ResultContent();
                        List<ResultContent.ItemContent> itemContents = new ArrayList<>();
                        HashSet<String> hashSet = new HashSet<String>();
                        for(int i = 0; i < searchResultResource.data.getResultCount(); i++) {
                            ResultContent.ItemContent itemContent = new ResultContent.ItemContent();
                            itemContent.setArtistName(searchResultResource.data.getResults().get(i).getArtistName());
                            itemContent.setArtworkUrl100(searchResultResource.data.getResults().get(i).getArtworkUrl100());
                            itemContent.setWrapperType(searchResultResource.data.getResults().get(i).getWrapperType());
                            itemContent.setKind(searchResultResource.data.getResults().get(i).getKind());
                            itemContent.setTrackName(searchResultResource.data.getResults().get(i).getTrackName());
                            itemContent.setCollectionName(searchResultResource.data.getResults().get(i).getCollectionName());
                            itemContent.setPreviewUrl(searchResultResource.data.getResults().get(i).getPreviewUrl());
                            itemContents.add(itemContent);
                            hashSet.add(itemContent.getWrapperType());
                        }
                        if (hashSet.size() > 2) {
                            tab3.set(hashSet.toArray()[2].toString());
                            tab2.set(hashSet.toArray()[1].toString());
                            tab1.set(hashSet.toArray()[0].toString());
                        } else if (hashSet.size() > 1) {
                            tab2.set(hashSet.toArray()[1].toString());
                            tab1.set(hashSet.toArray()[0].toString());
                        } else if (hashSet.size() == 1) {
                            tab1.set(hashSet.toArray()[0].toString());
                        }
                        resultContent.setItemContents(itemContents);
                        mResultContent = resultContent;
                        contentMutableLiveData.setValue(mResultContent);
                        pagingMode.set(true);
                    }
                }));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return true;
            }
        };
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public MutableLiveData<ResultContent> getContentMutableLiveData() {
        if (contentMutableLiveData == null) {
            contentMutableLiveData = new MutableLiveData<>();
        }
        return contentMutableLiveData;
    }

    public ObservableField<String> getTrack() {
        return track;
    }

    public ObservableField<String> getArtist() {
        return artist;
    }

    public ObservableField<String> getKind() {
        return kind;
    }

    public ObservableField<String> getCollection() {
        return collection;
    }

    public ObservableField<Integer> getPage() {
        return page;
    }

    public ObservableField<String> getPreviewUrl() {
        return previewUrl;
    }

    public void setDetailContent(ResultContent.ItemContent content){
        track.set(content.getTrackName());
        artist.set(content.getArtistName());
        imageUrl.set(content.getArtworkUrl100());
        kind.set(content.getKind());
        collection.set(content.getCollectionName());
        previewUrl.set(content.getPreviewUrl());
        if (!content.getPreviewUrl().equals("")) {
            isPreview.set(true);
        } else {
            isPreview.set(false);
        }
    }

    public ObservableBoolean getPagingMode() {
        return pagingMode;
    }

    public void setPagingMode(ObservableBoolean pagingMode) {
        this.pagingMode = pagingMode;
    }

    public ObservableBoolean getIsPreview() {
        return isPreview;
    }

    public void setIsPreview(ObservableBoolean isPreview) {
        this.isPreview = isPreview;
    }

    public void onLeftClick(){
        if (page.get() != 1 && offset != 0) {
            offset = offset - 10;
            getCompositeDisposable().add(mobileRepository.search(mQuery,offset,10).subscribe(searchResultResource -> {
                if (searchResultResource.status == Status.SUCCESS) {
                    ResultContent resultContent = new ResultContent();
                    List<ResultContent.ItemContent> itemContents = new ArrayList<>();
                    for(int i = 0; i < searchResultResource.data.getResultCount(); i++) {
                        ResultContent.ItemContent itemContent = new ResultContent.ItemContent();
                        itemContent.setArtistName(searchResultResource.data.getResults().get(i).getArtistName());
                        itemContent.setArtworkUrl100(searchResultResource.data.getResults().get(i).getArtworkUrl100());
                        itemContent.setWrapperType(searchResultResource.data.getResults().get(i).getWrapperType());
                        itemContent.setKind(searchResultResource.data.getResults().get(i).getKind());
                        itemContent.setTrackName(searchResultResource.data.getResults().get(i).getTrackName());
                        itemContent.setCollectionName(searchResultResource.data.getResults().get(i).getCollectionName());
                        itemContent.setPreviewUrl(searchResultResource.data.getResults().get(i).getPreviewUrl());
                        itemContents.add(itemContent);
                    }
                    resultContent.setItemContents(itemContents);
                    mResultContent = resultContent;
                    contentMutableLiveData.setValue(mResultContent);
                    page.set(page.get() - 1);
                } else {
                    offset = offset + 10;
                }
            }));
        }
    }

    public void onRightClick(){
        offset = offset + 10;
        getCompositeDisposable().add(mobileRepository.search(mQuery,offset,10).subscribe(searchResultResource -> {
            if (searchResultResource.status == Status.SUCCESS) {
                ResultContent resultContent = new ResultContent();
                List<ResultContent.ItemContent> itemContents = new ArrayList<>();
                for(int i = 0; i < searchResultResource.data.getResultCount(); i++) {
                    ResultContent.ItemContent itemContent = new ResultContent.ItemContent();
                    itemContent.setArtistName(searchResultResource.data.getResults().get(i).getArtistName());
                    itemContent.setArtworkUrl100(searchResultResource.data.getResults().get(i).getArtworkUrl100());
                    itemContent.setWrapperType(searchResultResource.data.getResults().get(i).getWrapperType());
                    itemContent.setKind(searchResultResource.data.getResults().get(i).getKind());
                    itemContent.setTrackName(searchResultResource.data.getResults().get(i).getTrackName());
                    itemContent.setCollectionName(searchResultResource.data.getResults().get(i).getCollectionName());
                    itemContent.setPreviewUrl(searchResultResource.data.getResults().get(i).getPreviewUrl());
                    itemContents.add(itemContent);
                }
                resultContent.setItemContents(itemContents);
                mResultContent = resultContent;
                contentMutableLiveData.setValue(mResultContent);
                page.set(page.get() + 1);
            } else {
                offset = offset - 10;
            }
        }));
    }

    public TabLayout.OnTabSelectedListener getTabSelectedListener(){
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getCompositeDisposable().add(mobileRepository.search(mQuery,offset,10).subscribe(searchResultResource -> {
                    if (searchResultResource.status == Status.SUCCESS) {
                        ResultContent resultContent = new ResultContent();
                        List<ResultContent.ItemContent> itemContents = new ArrayList<>();
                        for(int i = 0; i < searchResultResource.data.getResultCount(); i++) {
                            ResultContent.ItemContent itemContent = new ResultContent.ItemContent();
                            itemContent.setArtistName(searchResultResource.data.getResults().get(i).getArtistName());
                            itemContent.setArtworkUrl100(searchResultResource.data.getResults().get(i).getArtworkUrl100());
                            itemContent.setWrapperType(searchResultResource.data.getResults().get(i).getWrapperType());
                            itemContent.setKind(searchResultResource.data.getResults().get(i).getKind());
                            itemContent.setTrackName(searchResultResource.data.getResults().get(i).getTrackName());
                            itemContent.setCollectionName(searchResultResource.data.getResults().get(i).getCollectionName());
                            itemContent.setPreviewUrl(searchResultResource.data.getResults().get(i).getPreviewUrl());
                            if (itemContent.getWrapperType().equals(tab.getText())) {
                                itemContents.add(itemContent);
                            }
                        }
                        resultContent.setItemContents(itemContents);
                        mResultContent = resultContent;
                        contentMutableLiveData.setValue(mResultContent);
                    }
                }));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    public ObservableField<String> getTab1() {
        return tab1;
    }

    public ObservableField<String> getTab2() {
        return tab2;
    }

    public ObservableField<String> getTab3() {
        return tab3;
    }
}
