CREATE TABLE teams (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    registration_date TIMESTAMP NOT NULL
);

CREATE TABLE user_teams (
    user_id BIGINT,
    team_id BIGINT,
    PRIMARY KEY (user_id, team_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

CREATE TABLE documents (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    s3_key VARCHAR(255) NOT NULL,
    upload_date TIMESTAMP NOT NULL,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
