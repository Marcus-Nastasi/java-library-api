package com.library.app.Enums.Books;

public enum BookStatus {

    STOCK("stock"), AVAILABLE("available"), UNAVAILABLE("unavailable");

    private final String bookStatus;

    BookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookStatus() {
        return bookStatus;
    }
}



