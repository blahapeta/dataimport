CREATE TABLE Company(
ico INT,
name VARCHAR2,
adresa VARCHAR2,
lastupdate TIMESTAMP,
lastupdaterecord TIMESTAMP,
PRIMARY KEY (name)
);
ALTER TABLE Company ALTER COLUMN lastupdaterecord SET DEFAULT CURRENT_TIMESTAMP;
CREATE TABLE Employee(
counter INT AUTO_INCREMENT,
  name VARCHAR2,
  surname VARCHAR2,
  lastupdate TIMESTAMP,
  lastupdaterecord TIMESTAMP,
  email VARCHAR2,
  companyname VARCHAR2,
  FOREIGN KEY (companyname) REFERENCES Company(name),
  PRIMARY KEY (name,surname,email)
);
ALTER TABLE Employee ALTER COLUMN lastupdaterecord SET DEFAULT CURRENT_TIMESTAMP;

