package com.library.app.Enums.Books;

public enum BookType {

    REGULAR("regular"), STUDY("study"), JOURNAL("jornal"), MAGAZINE("magazine");

    private final String bookStatus;

    BookType(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookStatus() {
        return bookStatus;
    }
}



