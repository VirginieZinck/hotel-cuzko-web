CREATE TABLE accessory (
    id integer PRIMARY KEY,
    room_id integer,
    type varchar,
    description varchar,
    CONSTRAINT room_fk FOREIGN KEY (room_id) REFERENCES Room (id)
);