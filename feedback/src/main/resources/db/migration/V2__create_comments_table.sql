CREATE TABLE comments (
                          id SERIAL PRIMARY KEY,
                          post_id BIGINT NOT NULL,
                          user_id BIGINT NOT NULL,
                          content TEXT NOT NULL,
                          created_at TIMESTAMP NOT NULL
);
