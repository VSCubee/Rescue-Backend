BEGIN;

SET client_encoding = 'LATIN1';

CREATE TABLE organization (
    id bigserial PRIMARY KEY,
    admin_email varchar(255) NOT NULL,
    name varchar(255)  NOT NULL,
    locations double precision[][]  NOT NULL
);

CREATE TABLE users (
    id bigserial PRIMARY KEY,
    email varchar(255) NOT NULL,
    api_key varchar(255)  NOT NULL,
    org_id bigint NOT NULL
);

ALTER TABLE users
ADD constraint fkey_users_organization FOREIGN KEY(org_id) REFERENCES organization(id);


CREATE TABLE events (
    id bigserial PRIMARY KEY,
    status boolean NOT NULL,
    priority int  NOT NULL,
    is_deleted boolean NOT NULL
);

CREATE TABLE event_organization(
    id bigserial PRIMARY KEY,
    event_id bigint NOT NULL,
    org_id bigint NOT NULL,
    status boolean NOT NULL
);

ALTER TABLE event_organization
ADD constraint fkey1_event_organization FOREIGN KEY(org_id) REFERENCES organization(id);

ALTER TABLE event_organization
ADD constraint fkey2_event_organization FOREIGN KEY(event_id) REFERENCES events(id);

COMMIT;

