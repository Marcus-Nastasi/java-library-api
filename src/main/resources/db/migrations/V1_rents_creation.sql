
CREATE TABLE rents(
    id BIGINT NOT NULL PRIMARY KEY,
    emitDate DATE NOT NULL,
    returnDate DATE NOT NULL,
    librarianId VARCHAR(255) NOT NULL,
    memberId BIGINT NOT NULL,
    FOREIGN KEY (memberId) REFERENCES members(id),
    FOREIGN KEY (librarianId) REFERENCES librarian(id)
);






