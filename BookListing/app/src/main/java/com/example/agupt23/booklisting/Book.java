package com.example.agupt23.booklisting;

/**
 * Created by agupt23 on 2/21/17.
 */

public class Book {

    private String mBookTitle;

    private String mBookAuthors;

    private String mPublishedDate;

    private String mWebLink;

    public Book(String bookTitle, String bookAuthors, String publishedDate, String webLink) {
        mBookTitle = bookTitle;
        mBookAuthors = bookAuthors;
        mPublishedDate = publishedDate;
        mWebLink = webLink;
    }

    public String getmBookTitle() {
        return mBookTitle;
    }

    public String getmBookAuthors() {
        return mBookAuthors;
    }

    public String getmPublishedDate() {
        return mPublishedDate;
    }

    public String getmWebLink() {
        return mWebLink;
    }
}

