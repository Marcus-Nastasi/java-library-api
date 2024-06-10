
CREATE TABLE members(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(255) NOT NULL,
    dateOfMembership DATE NOT NULL,
    booksIssued INT NOT NULL,
    booksLimit INT NOT NULL,
    phone VARCHAR(255) NOT NULL
);





