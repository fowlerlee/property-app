
CREATE EXTENSION citext;
CREATE DOMAIN email AS citext
  CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );
  
CREATE EXTENSION pgcrypto;



CREATE TABLE person (
	id UUID NOT NULL PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	email citext NOT NULL UNIQUE,
	password TEXT NOT NULL
);

CREATE TABLE attachment (
	id UUID NOT NULL PRIMARY KEY,
	filename VARCHAR(100) NOT NULL,
	filetype VARCHAR(100) NOT NULL,
	data BYTEA 
);
