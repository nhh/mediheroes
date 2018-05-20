DROP INDEX idx_users_employer_id;
CREATE INDEX idx_users_employer_id ON users (employer_id);
