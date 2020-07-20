-- get tableName with columnName --
SELECT DISTINCT
	TABLE_NAME
FROM
	INFORMATION_SCHEMA.COLUMNS
WHERE
	COLUMN_NAME IN('column1', 'column2')
	AND TABLE_SCHEMA = 'schema_name';
	
-- unlock liquibase changelog --
UPDATE DATABASECHANGELOGLOCK 
SET LOCKED=0, LOCKGRANTED=null, LOCKEDBY=null where ID=1;

-- disable ONLY_FULL_GROUP_BY sql_mode --
SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));