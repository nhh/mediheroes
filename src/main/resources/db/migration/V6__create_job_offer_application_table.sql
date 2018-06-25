CREATE TABLE job_offer_applications (
    id SERIAL PRIMARY KEY,
    description VARCHAR(1000) NOT NULL
);

ALTER TABLE job_offer_applications ADD COLUMN job_offer_id INTEGER REFERENCES job_offers(id);
ALTER TABLE job_offer_applications ADD COLUMN user_id INTEGER REFERENCES users(id);

CREATE INDEX idx_job_offer_application_job_offer_id ON job_offer_applications (job_offer_id);
CREATE INDEX idx_job_offer_application_user_id ON job_offer_applications (user_id);
