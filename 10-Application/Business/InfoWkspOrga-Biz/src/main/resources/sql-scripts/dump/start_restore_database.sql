-- SET GLOBAL general_log_file = '/var/log/mysql_refresh_database.log';
-- SET GLOBAL general_log = 'ON';


SET FOREIGN_KEY_CHECKS = 0;
SET GROUP_CONCAT_MAX_LEN=32768;



SET @tables = NULL;
SELECT GROUP_CONCAT('`', table_name, '`') INTO @tables
  FROM information_schema.tables
  WHERE table_schema = (SELECT DATABASE());
SELECT IFNULL(@tables,'dummy') INTO @tables;

SET @tables = CONCAT('DROP TABLE IF EXISTS ', @tables);
PREPARE stmt_table FROM @tables;
EXECUTE stmt_table;
DEALLOCATE PREPARE stmt_table;



SET @views = NULL;
SELECT GROUP_CONCAT('`', TABLE_NAME, '`') INTO @views
  FROM information_schema.views
  WHERE table_schema = (SELECT DATABASE());
SELECT IFNULL(@views,'dummy') INTO @views;

SET @views = CONCAT('DROP VIEW IF EXISTS ', @views);
PREPARE stmt_view FROM @views;
EXECUTE stmt_view;
DEALLOCATE PREPARE stmt_view; 
