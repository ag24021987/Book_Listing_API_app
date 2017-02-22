package com.example.agupt23.booklisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;

/**
 * Created by agupt23 on 2/21/17.
 */

public class BookAdapter extends ArrayAdapter<Book>{

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context,0,books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemview = convertView;

        if (listItemview == null) {
            listItemview = LayoutInflater.from(getContext()).inflate(R.layout.list_element_book,parent,false);
        }

        Book currentBook = getItem(position);

        TextView titleView = (TextView) listItemview.findViewById(R.id.book_title);
        titleView.setText(currentBook.getmBookTitle());

        TextView publishedDateView = (TextView) listItemview.findViewById(R.id.published_date);
        if (currentBook.getmPublishedDate() == null) {
            publishedDateView.setVisibility(GONE);
        } else {
            publishedDateView.setText(currentBook.getmPublishedDate());
        }

        TextView authorView = (TextView) listItemview.findViewById(R.id.book_authors);
        if (currentBook.getmBookAuthors() == null) {
            authorView.setVisibility(GONE);
        } else {
            authorView.setText(currentBook.getmBookAuthors());
        }
        return listItemview;
    }
}
