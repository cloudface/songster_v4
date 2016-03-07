package com.samples.songster.mylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisbraunschweiler1 on 04/03/16.
 */
public class MyListViewModel {
    private List<MyListItemModel> myItems;

    public MyListViewModel(){
        myItems = new ArrayList<>();
    }

    public List<MyListItemModel> getMyItems() {
        return myItems;
    }

    public void setMyItems(List<MyListItemModel> myItems) {
        this.myItems = myItems;
    }
}
