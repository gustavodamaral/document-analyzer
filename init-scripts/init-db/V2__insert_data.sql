INSERT INTO teams (name)
VALUES
('Team A'),
('Team B'),
('Team C');

INSERT INTO users (email, registration_date)
VALUES
('john@example.com', NOW()),
('bob@example.com', NOW()),
('bill@example.com', NOW());

INSERT INTO user_teams (user_id, team_id)
VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO documents (name, s3_key, upload_date, user_id)
VALUES
('Document 1', 'document1.txt', '2023-01-01 00:00:00', 1),
('Document 2', 'document2.txt', NOW(), 2);