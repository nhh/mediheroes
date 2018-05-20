ALTER TABLE users ADD COLUMN type VARCHAR(50);

UPDATE users SET type = 'EMPLOYEE' WHERE company_id IS NOT NULL;
UPDATE users SET type = 'OWNER' WHERE employer_id IS NOT NULL;
UPDATE users SET type = 'FREELANCER' WHERE company_id IS NULL AND employer_id IS NULL;
