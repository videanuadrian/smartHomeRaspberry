
-- rename temp_view and change definer as the script will be executed with smarthome user
--
-- Drop view temperature_log_view
--
DROP VIEW temperature_log_view CASCADE;

--
-- Create view sh_temperature_log_view
--
CREATE VIEW sh_temperature_log_view AS SELECT
  `smart_home`.`sh_temperature_log`.`id` AS `id`, `smart_home`.`sh_temperature_log`.`sensor_id` AS `sensor_id`, `smart_home`.`sh_temperature_log`.`temperature` AS `temperature`, FROM_UNIXTIME((`smart_home`.`sh_temperature_log`.`timestamp` / 1000)) AS `ts`
FROM
  `smart_home`.`sh_temperature_log`;
