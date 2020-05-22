-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 22-05-2020 a las 16:57:53
-- Versión del servidor: 10.3.22-MariaDB-0+deb10u1
-- Versión de PHP: 7.3.14-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `juego`
--
CREATE DATABASE IF NOT EXISTS `juego` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `juego`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Champion`
--

CREATE TABLE `Champion` (
  `name` varchar(20) NOT NULL,
  `damage` int(3) NOT NULL,
  `health` int(4) NOT NULL,
  `armor` int(3) NOT NULL,
  `prob_miss` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Champion`
--

INSERT INTO `Champion` (`name`, `damage`, `health`, `armor`, `prob_miss`) VALUES
('Killer', 180, 2500, 60, 20),
('Tank', 120, 3700, 120, 40),
('Wizard', 160, 2800, 50, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Match`
--

CREATE TABLE `Match` (
  `id` int(4) NOT NULL,
  `username_player` varchar(50) NOT NULL,
  `champion_player_name` varchar(50) NOT NULL,
  `champion_IA_name` varchar(50) NOT NULL,
  `skill_champion_player` varchar(50) NOT NULL,
  `skill_champion_IA` varchar(50) NOT NULL,
  `winner` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Match`
--

INSERT INTO `Match` (`id`, `username_player`, `champion_player_name`, `champion_IA_name`, `skill_champion_player`, `skill_champion_IA`, `winner`, `date`, `time`) VALUES
(1, 'luque', 'Wizard', 'Wizard', 'MoreHealth', 'MoreHealth', 'IA', '2020-05-19', '17:34:22'),
(2, 'luque', 'Killer', 'Tank', 'MoreArmor', 'MoreHealth', 'luque', '2020-05-19', '17:35:51'),
(3, 'luque', 'Tank', 'Tank', 'LessProbMiss', 'MoreHealth', 'IA', '2020-05-19', '17:44:08'),
(4, 'luque', 'Tank', 'Wizard', 'MoreDamage', 'MoreArmor', 'luque', '2020-05-19', '17:46:03'),
(5, 'luque', 'Wizard', 'Tank', 'MoreDamage', 'MoreDamage', 'luque', '2020-05-19', '18:13:40'),
(6, 'luque', 'Tank', 'Wizard', 'MoreArmor', 'MoreDamage', 'IA', '2020-05-19', '18:18:36'),
(7, 'luque', 'Tank', 'Wizard', 'LessProbMiss', 'MoreHealth', 'IA', '2020-05-19', '18:19:29'),
(8, 'luque', 'Killer', 'Wizard', 'MoreHealth', 'MoreDamage', 'IA', '2020-05-19', '22:54:24'),
(9, 'luque', 'Killer', 'Wizard', 'MoreHealth', 'MoreHealth', 'luque', '2020-05-19', '22:56:38'),
(10, 'luque', 'Tank', 'Wizard', 'MoreDamage', 'MoreHealth', 'luque', '2020-05-19', '22:59:52'),
(11, 'luque', 'Killer', 'Wizard', 'MoreDamage', 'MoreDamage', 'IA', '2020-05-20', '11:56:53'),
(12, 'luque', 'Killer', 'Wizard', 'MoreHealth', 'MoreArmor', 'luque', '2020-05-20', '11:58:47'),
(13, 'luque', 'Killer', 'Wizard', 'MoreArmor', 'MoreArmor', 'luque', '2020-05-20', '12:01:43'),
(14, 'luque', 'Killer', 'Wizard', 'LessProbMiss', 'MoreArmor', 'luque', '2020-05-20', '12:01:59'),
(15, 'luque', 'Killer', 'Wizard', 'MoreDamage', 'MoreHealth', 'IA', '2020-05-20', '12:02:22'),
(16, 'luque', 'Killer', 'Wizard', 'MoreArmor', 'MoreHealth', 'luque', '2020-05-20', '12:03:16'),
(17, 'luque', 'Tank', 'Wizard', 'MoreDamage', 'MoreHealth', 'luque', '2020-05-20', '12:03:24'),
(18, 'luque', 'Wizard', 'Tank', 'MoreArmor', 'MoreDamage', 'IA', '2020-05-20', '12:03:36'),
(19, 'luque', 'Wizard', 'Killer', 'MoreDamage', 'MoreArmor', 'IA', '2020-05-20', '12:10:30'),
(20, 'luque', 'Wizard', 'Killer', 'MoreDamage', 'LessProbMiss', 'luque', '2020-05-20', '12:10:47'),
(21, 'luque', 'Tank', 'Killer', 'MoreArmor', 'MoreDamage', 'luque', '2020-05-20', '12:15:43'),
(22, 'luque', 'Killer', 'Tank', 'MoreArmor', 'MoreArmor', 'IA', '2020-05-20', '12:19:03'),
(23, 'luque', 'Killer', 'Wizard', 'MoreDamage', 'MoreDamage', 'luque', '2020-05-20', '12:19:22'),
(24, 'luque', 'Tank', 'Killer', 'MoreHealth', 'MoreHealth', 'luque', '2020-05-20', '12:24:45'),
(25, 'luque', 'Killer', 'Wizard', 'MoreDamage', 'MoreHealth', 'luque', '2020-05-20', '13:57:27'),
(26, 'luque', 'Killer', 'Tank', 'MoreHealth', 'LessProbMiss', 'luque', '2020-05-21', '15:02:05'),
(27, 'luque', 'Tank', 'Killer', 'MoreDamage', 'MoreArmor', 'luque', '2020-05-22', '12:25:36'),
(28, 'luque', 'Killer', 'Tank', 'MoreDamage', 'MoreDamage', 'luque', '2020-05-22', '13:47:40'),
(29, 'luque', 'Killer', 'Wizard', 'MoreArmor', 'MoreHealth', 'IA', '2020-05-22', '15:51:06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Player`
--

CREATE TABLE `Player` (
  `name` varchar(60) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(180) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Player`
--

INSERT INTO `Player` (`name`, `last_name`, `email`, `username`, `password`) VALUES
('IA', 'IA', 'IA@IA.com,', 'IA', ''),
('miguel', 'luque', 'luque@gmail.com', 'luque', '36ebf2b896af712184b491160d947f5231b1d652934e7b82ec8b978a4907ee43cbcfb3f95c6e0073115a5c1ce9fdb6968fc1a821bd948072de695709df511ecf'),
('miguel', 'luque', 'luque@gmail.com', 'luque2', '36ebf2b896af712184b491160d947f5231b1d652934e7b82ec8b978a4907ee43cbcfb3f95c6e0073115a5c1ce9fdb6968fc1a821bd948072de695709df511ecf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Score`
--

CREATE TABLE `Score` (
  `id` int(11) NOT NULL,
  `username_player` varchar(30) NOT NULL,
  `victories` int(5) NOT NULL,
  `defeats` int(5) NOT NULL,
  `total_games` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Score`
--

INSERT INTO `Score` (`id`, `username_player`, `victories`, `defeats`, `total_games`) VALUES
(10, 'luque', 47, 33, 80),
(12, 'luque2', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Skill`
--

CREATE TABLE `Skill` (
  `skill` varchar(50) NOT NULL,
  `modifier` float NOT NULL,
  `description` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Skill`
--

INSERT INTO `Skill` (`skill`, `modifier`, `description`) VALUES
('LessProbMiss', 0.95, 'Menos posibilidad de fallar el ataque'),
('MoreArmor', 1.07, 'Mas armadura'),
('MoreDamage', 1.05, 'Mas daño'),
('MoreHealth', 1.04, 'Mas vida');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Champion`
--
ALTER TABLE `Champion`
  ADD PRIMARY KEY (`name`),
  ADD KEY `name` (`name`);

--
-- Indices de la tabla `Match`
--
ALTER TABLE `Match`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username_player` (`username_player`),
  ADD KEY `champion_player_name` (`champion_player_name`),
  ADD KEY `champion_IA_name` (`champion_IA_name`),
  ADD KEY `skill_champion_player` (`skill_champion_player`),
  ADD KEY `skill_champion_IA` (`skill_champion_IA`),
  ADD KEY `winner` (`winner`);

--
-- Indices de la tabla `Player`
--
ALTER TABLE `Player`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `Score`
--
ALTER TABLE `Score`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username_player` (`username_player`);

--
-- Indices de la tabla `Skill`
--
ALTER TABLE `Skill`
  ADD PRIMARY KEY (`skill`),
  ADD KEY `skill` (`skill`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Match`
--
ALTER TABLE `Match`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT de la tabla `Score`
--
ALTER TABLE `Score`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Match`
--
ALTER TABLE `Match`
  ADD CONSTRAINT `Match_ibfk_1` FOREIGN KEY (`champion_player_name`) REFERENCES `Champion` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Match_ibfk_2` FOREIGN KEY (`champion_IA_name`) REFERENCES `Champion` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Match_ibfk_3` FOREIGN KEY (`username_player`) REFERENCES `Player` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Match_ibfk_4` FOREIGN KEY (`skill_champion_player`) REFERENCES `Skill` (`skill`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Match_ibfk_5` FOREIGN KEY (`skill_champion_IA`) REFERENCES `Skill` (`skill`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Match_ibfk_6` FOREIGN KEY (`winner`) REFERENCES `Player` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Score`
--
ALTER TABLE `Score`
  ADD CONSTRAINT `Score_ibfk_1` FOREIGN KEY (`username_player`) REFERENCES `Player` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
