package com.samples.songster.dal.mylist;

import com.samples.songster.dal.search.dto.SongDto;

import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 07/03/16.
 */
public interface MyListRepository {
    void start();

    void pause();

    void loadMyList(MyListRepositoryListener listener);

    interface MyListRepositoryListener {

        void onLoadedMyList(List<SongDto> songs);

    }

    abstract class MyListRepositoryListenerAdapter implements MyListRepositoryListener {

        @Override
        public void onLoadedMyList(List<SongDto> songs) {
        }
    }
}
