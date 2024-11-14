CREATE TABLE reposts (
                         id SERIAL PRIMARY KEY,
                         original_post_id BIGINT NOT NULL,
                         user_id BIGINT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (original_post_id) REFERENCES posts(id),
                         FOREIGN KEY (user_id) REFERENCES users(id)
);
