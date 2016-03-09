package com.samples.songster.mylist.repository;

import com.samples.songster.search.repository.dto.SongDto;
import com.samples.songster.settings.UserModel;

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
