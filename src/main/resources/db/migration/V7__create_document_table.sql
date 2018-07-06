CREATE TABLE documents (
    id SERIAL PRIMARY KEY,
    documentId VARCHAR(1000) NOT NULL
);

ALTER TABLE documents ADD COLUMN user_id INTEGER REFERENCES users(id);
CREATE INDEX idx_documents_document_id ON documents (documentId);
