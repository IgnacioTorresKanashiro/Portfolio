-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-07-2019 a las 17:32:45
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `finalweb1`
--
CREATE DATABASE IF NOT EXISTS `finalweb1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `finalweb1`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id` int(11) NOT NULL,
  `producto_id` text COLLATE utf8_spanish_ci NOT NULL,
  `comentario` text COLLATE utf8_spanish_ci NOT NULL,
  `nombre_usuario` text COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`id`, `producto_id`, `comentario`, `nombre_usuario`) VALUES
(1, '8', 'Muy buen producto', 'pepe'),
(2, '9', 'Muy bueno tu telefono', 'nico');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(10) UNSIGNED NOT NULL,
  `titulo` text COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `usuarioid` int(11) DEFAULT NULL,
  `nombre_usuario` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `comentario_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `titulo`, `descripcion`, `precio`, `usuarioid`, `nombre_usuario`, `comentario_id`) VALUES
(6, 'Mesa ratona', 'Mesa en muy buen estado', '750.00', 3, 'pepe', NULL),
(8, 'Motorolla MotoG5', '4G, WiFi, pantalla tactil', '8000.00', 4, 'hernan', NULL),
(9, 'Motorolla MotoG5', 'tremendo celular', '16000.00', 2, 'cachi', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre_usuario` text COLLATE utf8_spanish_ci NOT NULL,
  `password` text COLLATE utf8_spanish_ci NOT NULL,
  `email` text COLLATE utf8_spanish_ci NOT NULL,
  `type` int(11) DEFAULT NULL,
  `localidad` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `foto` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre_usuario`, `password`, `email`, `type`, `localidad`, `apellido`, `telefono`, `foto`) VALUES
(1, 'admin', 'admin', 'admin@admin', 1, NULL, NULL, NULL, 'https://avatarfiles.alphacoders.com/891/89162.jpg'),
(2, 'cachi', 'cachi', 'cachi@cahi.com', 2, 'Turdera', 'lopez', '44234', 'https://content.invisioncic.com/r273030/monthly_2017_08/89bac06806bea1a582b9f8a963005e64409d303c_full.jpg.7e347c1e21c911fe129e6ec19b127798.jpg'),
(3, 'nico', 'nico', 'nico@nico.com', 2, 'lanus', 'censo', '4234324', 'https://static3.laverdad.es/www/pre2017/multimedia/noticias/201510/09/media/cortadas/peppa--490x490.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
