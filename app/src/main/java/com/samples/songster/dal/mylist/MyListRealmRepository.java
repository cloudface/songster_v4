package com.samples.songster.dal.mylist;

import android.content.Context;

import com.samples.songster.dal.mylist.events.LoadMyListEvent;
import com.samples.songster.dal.mylist.events.LoadedMyListEvent;
import com.samples.songster.dal.mylist.realm.RealmSong;
import com.samples.songster.dal.search.dto.SongDto;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by chrisbraunschweiler1 on 27/04/16.
 */
public class MyListRealmRepository implements MyListRepository {
    private static final EventBus EVENT_BUS = EventBus.getDefault();
    private final RealmConfiguration mRealmConfig;

    public MyListRealmRepository(Context context){
        mRealmConfig = new RealmConfiguration.Builder(context).build();
    }

    @Override
    public void start() {
        EVENT_BUS.register(this);
    }

    @Override
    public void pause() {
        EVENT_BUS.unregister(this);
    }

    @Override
    public void loadMyList(MyListRepositoryListener listener) {
        EVENT_BUS.post(new LoadMyListEvent(listener));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onLoadMyList(LoadMyListEvent event){
        try {
            //Create some latency
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Realm realm = Realm.getInstance(mRealmConfig);
        RealmResults<RealmSong> allSongs = realm.allObjects(RealmSong.class);
        List<SongDto> songDtos = new ArrayList<>();
        for(RealmSong realmSong : allSongs){
            SongDto songDto = new SongDto();
            songDto.setAlbum(realmSong.getAlbum());
            songDto.setArtist(realmSong.getArtist());
            songDto.setName(realmSong.getName());
            songDtos.add(songDto);
        }

        EVENT_BUS.post(new LoadedMyListEvent(songDtos, event.getListener()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadedMyList(LoadedMyListEvent event){
        event.getListener().onLoadedMyList(event.getPayload());
    }
}
