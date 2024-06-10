
CREATE TABLE rents(
    id BIGINT NOT NULL PRIMARY KEY,
    emitDate DATE NOT NULL,
    returnDate DATE NOT NULL,
    memberId BIGINT NOT NULL,
    FOREIGN KEY (memberId) REFERENCES members(id)
);






