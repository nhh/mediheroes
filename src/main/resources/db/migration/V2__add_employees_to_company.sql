ALTER TABLE users ADD COLUMN employer_id INTEGER REFERENCES companies(id);
CREATE INDEX idx_users_employer_id ON users (company_id);
