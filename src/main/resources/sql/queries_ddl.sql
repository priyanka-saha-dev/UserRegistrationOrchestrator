CREATE TABLE USERS (
    uuid UUID PRIMARY KEY,
    username VARCHAR(10) UNIQUE NOT NULL,
    password VARCHAR(10) NOT NULL,
    type VARCHAR(10) NOT NULL
);

CREATE TABLE CONTACTS (
    id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(10) NOT NULL,
    contact VARCHAR(10) NOT NULL,
    user_uuid UUID NOT NULL,
    FOREIGN KEY (user_uuid) REFERENCES USERS(uuid)
);

CREATE TABLE NOTIFICATIONS (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_uuid UUID NOT NULL,
    user_contact_id INT NOT NULL,
    status BOOLEAN NOT NULL,
    initiator_uuid UUID NOT NULL,
    message VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_uuid) REFERENCES USERS(uuid),
    FOREIGN KEY (initiator_uuid) REFERENCES USERS(uuid), -- Corrected the foreign key name
    FOREIGN KEY (user_contact_id) REFERENCES CONTACTS(id) -- Corrected the foreign key name
);


SELECT * FROM USERS u JOIN CONTACTS c ON u.UUID = c.USER_UUID;
