CREATE TABLE media (
                       id SERIAL PRIMARY KEY,
                       post_id BIGINT REFERENCES posts(id) ON DELETE CASCADE,
                       media_type VARCHAR(10) CHECK (media_type IN ('IMAGE', 'VIDEO')),
                       url VARCHAR(255) NOT NULL
);
