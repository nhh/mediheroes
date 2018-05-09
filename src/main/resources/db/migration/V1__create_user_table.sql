CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT false,
    verified BOOLEAN DEFAULT false,
    street VARCHAR(150),
    zip INTEGER,
    state VARCHAR(150),
    city VARCHAR(150),
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE companies (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  active BOOLEAN DEFAULT false,
  verified BOOLEAN DEFAULT false,
  email VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE locations (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  street VARCHAR(150),
  zip INTEGER,
  state VARCHAR(150),
  city VARCHAR(150),
  email VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);

CREATE TABLE job_offers (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(1000),
  salary INTEGER NOT NULL,
  job VARCHAR(100),
  created_at TIMESTAMP DEFAULT now(),
  updated_at TIMESTAMP DEFAULT now()
);


ALTER TABLE locations ADD COLUMN company_id INTEGER REFERENCES companies(id);

ALTER TABLE users ADD COLUMN company_id INTEGER REFERENCES companies(id);

CREATE UNIQUE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_company_id ON users (company_id);

ALTER TABLE job_offers ADD COLUMN company_id INTEGER REFERENCES companies(id);
ALTER TABLE job_offers ADD COLUMN location_id INTEGER REFERENCES locations(id);

CREATE INDEX idx_job_offers_company_id ON job_offers(company_id);
CREATE INDEX idx_job_offers_location_id ON job_offers(location_id);
