CREATE TABLE notifications (
                               id SERIAL PRIMARY KEY,
                               user_id BIGINT NOT NULL,
                               type VARCHAR(50) NOT NULL CHECK (type IN ('LIKE', 'COMMENT', 'REPOST')),
                               post_id BIGINT,
                               created_at TIMESTAMP NOT NULL
);