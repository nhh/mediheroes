ALTER TABLE users ADD COLUMN image_id VARCHAR(64);

CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    file_id VARCHAR(1000) NOT NULL,
    filename VARCHAR(100)
);

ALTER TABLE files ADD COLUMN user_id INTEGER REFERENCES users(id);
CREATE INDEX idx_files_file_id ON files (file_id);
