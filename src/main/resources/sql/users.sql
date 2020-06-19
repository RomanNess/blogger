insert into users (username, password) values
    ('user', '$2a$10$3VF.Royco.wOg.CJtWW8feo3BadSHWqHja9J/CGV93V6ENlqqVjCG'),
    ('user2', '$2a$10$Tg7dCVvC5C8HK/cwavtnpegI85EBoTd8EzyqGkxEMiMJG9H1M/Zvu')
    ON CONFLICT DO NOTHING;