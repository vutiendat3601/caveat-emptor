DELIMITER $$
CREATE TRIGGER `items_BEFORE_INSERT` 
BEFORE INSERT ON `items` FOR EACH ROW 
BEGIN
	SET
		NEW.`last_modified` = current_timestamp;
END $$