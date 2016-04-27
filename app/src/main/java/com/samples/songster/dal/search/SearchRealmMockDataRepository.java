package com.samples.songster.dal.search;

import android.content.Context;

import com.samples.songster.dal.mylist.realm.RealmSong;
import com.samples.songster.dal.search.dto.SongDto;
import com.samples.songster.dal.search.events.AddSongToMyListEvent;
import com.samples.songster.dal.search.events.AddedSongToMyListEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by chrisbraunschweiler1 on 27/04/16.
 */
public class SearchRealmMockDataRepository extends SearchMockDataRepository {
    private final RealmConfiguration mRealmConfig;

    public SearchRealmMockDataRepository(Context context) {
        mRealmConfig = new RealmConfiguration.Builder(context).build();
    }

    @Override
    public void addSongToMyList(final SongDto song, final SearchListener listener) {
        EVENT_BUS.post(new AddSongToMyListEvent(song, listener));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAddSongToMyList(AddSongToMyListEvent event) {
        SongDto songDto = event.getPayload();
        Realm realm = Realm.getInstance(mRealmConfig);
        RealmSong songToSave = new RealmSong();
        songToSave.setAlbum(songDto.getAlbum());
        songToSave.setArtist(songDto.getArtist());
        songToSave.setName(songDto.getName());
        realm.beginTransaction();
        realm.copyToRealm(songToSave);
        realm.commitTransaction();

        EVENT_BUS.post(new AddedSongToMyListEvent(event.getPayload(), event.getListener()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadedMyList(AddedSongToMyListEvent event) {
        event.getListener().onAddSongSuccess(event.getPayload());
    }
}
