CREATE TABLE rents(
    id SERIAL PRIMARY KEY,
    book_id BIGINT NOT NULL,
    emit_date DATE NOT NULL,
    return_date DATE NOT NULL,
    librarian_id VARCHAR(255) NOT NULL,
    member_id BIGINT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members(id),
    FOREIGN KEY (librarian_id) REFERENCES librarians(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);






