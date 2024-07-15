CREATE TABLE members(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL UNIQUE,
    type VARCHAR(255) NOT NULL,
    date_of_membership DATE NOT NULL,
    books_issued INT NOT NULL,
    books_limit INT NOT NULL,
    phone VARCHAR(255) NOT NULL
);





