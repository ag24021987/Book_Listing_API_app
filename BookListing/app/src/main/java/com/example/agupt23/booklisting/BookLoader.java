package com.example.agupt23.booklisting;

import android.content.Context;
import android.content.AsyncTaskLoader;
import java.util.ArrayList;

/**
 * Created by agupt23 on 2/21/17.
 */

public class BookLoader extends AsyncTaskLoader<ArrayList<Book>> {

    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public ArrayList<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        ArrayList<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}
