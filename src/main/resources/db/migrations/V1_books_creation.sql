
CREATE TABLE books(
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    author VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DOUBLE(10, 2) NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    edition VARCHAR(255) NOT NULL,
    dateOfPublish DATE NOT NULL
);





