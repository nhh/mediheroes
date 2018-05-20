UPDATE users SET type = 'OWNER' WHERE company_id IS NOT NULL;
UPDATE users SET type = 'EMPLOYEE' WHERE employer_id IS NOT NULL;
