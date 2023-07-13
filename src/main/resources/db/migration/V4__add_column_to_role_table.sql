ALTER TABLE role ADD COLUMN description VARCHAR(255) DEFAULT NULL;

UPDATE role set role.description = CONCAT(role.name, ' ', 'resource')