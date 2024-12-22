CREATE TABLE likes (
                       id SERIAL PRIMARY KEY,
                       post_id BIGINT NOT NULL,
                       user_id BIGINT NOT NULL,
                       created_at TIMESTAMP NOT NULL,
                       UNIQUE (post_id, user_id)
);
