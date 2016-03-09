package com.samples.songster.mylist.repository;

import com.samples.songster.mylist.repository.events.LoadMyListEvent;
import com.samples.songster.mylist.repository.events.LoadedMyListEvent;
import com.samples.songster.search.repository.dto.SongDto;
import com.samples.songster.settings.events.LoadUserSettingsEvent;
import com.samples.songster.settings.events.LoadedUserSettingsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public class MyListMockRepository implements MyListRepository {
    private static final EventBus EVENT_BUS = EventBus.getDefault();

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
        //Create mock songs
        try {
            //Create some latency
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<SongDto> songs = new ArrayList<>();
        SongDto song1 = new SongDto();
        song1.setName("Prayers/Triangle");
        song1.setArtist("Deftones");
        song1.setAlbum("Some album");
        songs.add(song1);

        SongDto song2 = new SongDto();
        song2.setName("Lithium");
        song2.setArtist("Nirvana");
        song2.setAlbum("Some album");
        songs.add(song2);

        SongDto song3 = new SongDto();
        song3.setName("Worse than this");
        song3.setArtist("Zebrahead");
        song3.setAlbum("Walk the Plank");
        songs.add(song3);

        SongDto song4 = new SongDto();
        song4.setName("Falling apart");
        song4.setArtist("Papa Roach");
        song4.setAlbum("Some album");
        songs.add(song4);

        SongDto song5 = new SongDto();
        song5.setName("Zebrahead");
        song5.setArtist("So what");
        song5.setAlbum("Walk the Plank");
        songs.add(song5);

        SongDto song6 = new SongDto();
        song6.setName("Ignorance");
        song6.setArtist("Paramore");
        song6.setAlbum("Paramore");
        songs.add(song6);

        SongDto song7 = new SongDto();
        song7.setName("Everything goes numb");
        song7.setArtist("Streetlight Manifesto");
        song7.setAlbum("Everything Went Numb");
        songs.add(song7);

        SongDto song8 = new SongDto();
        song8.setName("The Rock Show");
        song8.setArtist("Blink 182");
        song8.setAlbum("Take off your pants and jacket");
        songs.add(song8);

        SongDto song9 = new SongDto();
        song9.setName("Darker Side");
        song9.setArtist("Johny Lang");
        song9.setAlbum("Lie To Me");
        songs.add(song9);

        EVENT_BUS.post(new LoadedMyListEvent(songs, event.getListener()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadedMyList(LoadedMyListEvent event){
        event.getListener().onLoadedMyList(event.getPayload());
    }
}
