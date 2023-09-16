CREATE TABLE agency (
    id bigint NOT NULL,
    last_updated timestamp(6) without time zone,
    login VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    registered_location VARCHAR(255) NOT NULL
);

CREATE TABLE task (
    event_id bigint NOT NULL,
    task_id bigserial PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    time_created TIMESTAMP NOT NULL,
    status boolean NOT NULL
);

CREATE TABLE event_team (
    event_id bigint,
    team_id bigint
);

CREATE TABLE ask_team (
    task_id bigint,
    team_id bigint
);
----------------------------------DO ONLY TILL THIS FOR NOW-------------------------------------------------------------


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


