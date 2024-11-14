CREATE TABLE posts (
                       id SERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       content TEXT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);
