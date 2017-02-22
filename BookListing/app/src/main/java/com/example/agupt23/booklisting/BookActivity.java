package com.example.agupt23.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {

    private static final int BOOK_LOADER_ID = 1;
    BookAdapter bookAdapter;
    private String booksApifinalString;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        emptyView = (TextView) findViewById(R.id.empty_state_textview);

        Intent intent = getIntent();
        String booksApiString = "https://www.googleapis.com/books/v1/volumes";
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        booksApifinalString = booksApiString + "?q=" + message + "&maxResults=10";

        if (isNetworkAvailable()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, BookActivity.this);
        } else {
            ProgressBar spinning_progress = (ProgressBar) findViewById(R.id.loading_spinner);
            spinning_progress.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet_connection);
        }
        final ListView bookListView = (ListView) findViewById(R.id.listview);
        bookListView.setEmptyView(emptyView);

        bookAdapter = new BookAdapter(this, new ArrayList<Book>());

        bookListView.setAdapter(bookAdapter);

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book currentBook =  bookAdapter.getItem(position);
                if (currentBook.getmWebLink() != null) {
                    Uri webUrl = Uri.parse(currentBook.getmWebLink());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, webUrl);
                    startActivity(websiteIntent);
                }

            }
        });
    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, booksApifinalString);
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> books) {

        ProgressBar spinning_progress = (ProgressBar) findViewById(R.id.loading_spinner);
        spinning_progress.setVisibility(View.GONE);
        emptyView.setText(R.string.no_results_found);
        bookAdapter.clear();

        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {
        bookAdapter.clear();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
