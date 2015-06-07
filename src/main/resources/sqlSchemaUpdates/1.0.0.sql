
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `smart_home`
--

-- --------------------------------------------------------

--
-- Table structure for table `sh_sensors`
--

CREATE TABLE IF NOT EXISTS `sh_sensors` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(255) CHARACTER SET latin2 NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sh_temperature_log`
--

CREATE TABLE IF NOT EXISTS `sh_temperature_log` (
  `id` int(10) unsigned NOT NULL,
  `sensor_id` int(10) unsigned NOT NULL,
  `timestamp` bigint(10) unsigned NOT NULL,
  `temperature` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

-- --------------------------------------------------------

--
-- Table structure for table `sh_temperature_log_backup`
--

CREATE TABLE IF NOT EXISTS `sh_temperature_log_backup` (
  `id` int(10) unsigned NOT NULL,
  `sensor_id` int(10) unsigned NOT NULL,
  `timestamp` bigint(10) unsigned NOT NULL,
  `temperature` float NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=135663 DEFAULT CHARSET=latin2;

-- --------------------------------------------------------

--
-- Stand-in structure for view `temperature_log_view`
--
CREATE TABLE IF NOT EXISTS `temperature_log_view` (
`id` int(10) unsigned
,`sensor_id` int(10) unsigned
,`temperature` float
,`ts` datetime(4)
);

-- --------------------------------------------------------

--
-- Structure for view `temperature_log_view`
--
DROP TABLE IF EXISTS `temperature_log_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`smartHome`@`%` SQL SECURITY DEFINER VIEW `temperature_log_view` AS select `sh_temperature_log`.`id` AS `id`,`sh_temperature_log`.`sensor_id` AS `sensor_id`,`sh_temperature_log`.`temperature` AS `temperature`,from_unixtime((`sh_temperature_log`.`timestamp` / 1000)) AS `ts` from `sh_temperature_log`;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sh_sensors`
--
ALTER TABLE `sh_sensors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sh_temperature_log`
--
ALTER TABLE `sh_temperature_log`
  ADD PRIMARY KEY (`id`), ADD KEY `timestamp` (`timestamp`);

--
-- Indexes for table `sh_temperature_log_backup`
--
ALTER TABLE `sh_temperature_log_backup`
  ADD PRIMARY KEY (`id`), ADD KEY `timestamp` (`timestamp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sh_sensors`
--
ALTER TABLE `sh_sensors`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=0;
--
-- AUTO_INCREMENT for table `sh_temperature_log`
--
ALTER TABLE `sh_temperature_log`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `sh_temperature_log_backup`
--
ALTER TABLE `sh_temperature_log_backup`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=0;