-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 14, 2022 at 02:19 AM
-- Server version: 10.5.12-MariaDB-cll-lve
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u584847502_mzmotors`
--
CREATE DATABASE IF NOT EXISTS `u584847502_mzmotors` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `u584847502_mzmotors`;

-- --------------------------------------------------------

--
-- Table structure for table `publicaciones`
--

CREATE TABLE IF NOT EXISTS `publicaciones` (
  `id_post` int(4) NOT NULL AUTO_INCREMENT,
  `email_user` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photos` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `titulo` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `marca` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `modelo` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `año` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` double NOT NULL,
  `ubicacion` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `features` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `condicion` tinyint(2) NOT NULL,
  `descripcion` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `autorizada` tinyint(2) NOT NULL,
  `vendida` tinyint(2) NOT NULL,
  PRIMARY KEY (`id_post`),
  KEY `email_user` (`email_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `publicaciones`
--

INSERT INTO `publicaciones` (`id_post`, `email_user`, `photos`, `titulo`, `marca`, `modelo`, `año`, `precio`, `ubicacion`, `features`, `condicion`, `descripcion`, `autorizada`, `vendida`) VALUES
(3, 'oguzman3@ucol.mx', '0', 'holis', 'nose', 'quiensabe', '1234', 3444.4, 'Manzanillo', 'AC, Bolsas, ', 0, 'Hola mundo', 0, 0),
(4, 'fiso@gmail.com', '0', 'prueba', 'este', 'aquel', '2002', 123456.8, 'alla', 'Bluetooth, Alarma,', 1, 'hola mundo', 0, 0),
(5, 'fiso@gmail.com', '0', 'prueba', 'este', 'aquel', '2002', 123456.8, 'alla', 'Bluetooth, Alarma,', 1, 'hola mundo cómo están', 0, 0),
(6, 'oguzman3@ucol.mx', '0', 'Jajdjakkekdfkkdjkwskwkdkkfjskssjfjjjejsjsjsfjdjsjd', 'hshdhddhdhdhdhshshdhdhdhshshdh', 'shdhdyzhsjfkgkckcmggkxkxkkdjdj', '5666464656', 15000, 'ksjfkfkcgkfkdkdkdjfjcjfjfjskskskskdkdkfk', 'Bluetooth, A/C, Alarma, AplecarPlay/AndroidAuto, Frenos ABS, Camara de Reversa, Sensores de estacionamiento, Bolsas de Aire, Sistema electrico,', 0, 'Uwu', 0, 0),
(7, 'oguzman3@ucol.mx', '0', 'Carro Viejisimoo', 'Pontiac', 'Gtx2300', '2008', 2000, 'Manzanillo', 'Bolsas de Aire, Sistema electrico,', 1, 'No sirve', 0, 0),
(8, 'UniCol@gmail.com', '0', 'prueba', 'wze e,d w', 'rceefex', '82205', 123456, 'rcycewxec', 'Bluetooth, AplecarPlay/AndroidAuto,', 0, 'hxejxbdxtby', 0, 0),
(9, 'UniCol@gmail.com', '0', 'Kia Forte GT', 'Kia', 'Forte', '2020', 421000, 'Manzanillo', 'Bluetooth, A/C, Alarma, AplecarPlay/AndroidAuto, Frenos ABS, Camara de Reversa, Sensores de estacionamiento, Bolsas de Aire, Sistema electrico,', 1, 'this is a test', 0, 0),
(10, 'oguzman3@ucol.mx', '0', 'https://ochoarealestateservices.com/mzmotors/image', 'Xbox', 'Microsoft', 'Series S', 2022, '4980', 'Manzanillo', 0, '0', 0, 0),
(11, 'oguzman3@ucol.mx', '0', 'https://ochoarealestateservices.com/mzmotors/image', 'Xbox', 'Microsoft', 'Series S', 2022, '4980', 'Manzanillo', 0, '0', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `fperfil` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contacto` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pwd` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rol` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `email_user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id`, `fperfil`, `nombre`, `email`, `contacto`, `pwd`, `rol`) VALUES
(1, '', 'MZMotors', 'mzmotorsmobile@gmail.com', '3141076334', 'e50d11e94bafcd7c78120d7a6478bce07c60b70207f7aa19e71ebdb37eecccbde475e051a28209fdfb5c9a217ea08d335422244574465e73c39863c8f18874fa', 1),
(2, '', 'Dorian ', 'dorian@mail.com', '3141972359', '0dd3e512642c97ca3f747f9a76e374fbda73f9292823c0313be9d78add7cdd8f72235af0c553dd26797e78e1854edee0ae002f8aba074b066dfce1af114e32f8', 0),
(3, '', 'Axel', 'axeloza123@gmail.com', '3142175357', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 0),
(4, '', 'Pedro ', 'pedro@gmail.com', '3141027774', '7fcf4ba391c48784edde599889d6e3f1e47a27db36ecc050cc92f259bfac38afad2c68a1ae804d77075e8fb722503f3eca2b2c1006ee6f6c7b7628cb45fffd1d', 0),
(6, '', 'Pedro Martinez', 'pedrito@gmail.com', '3141027774', 'e50d11e94bafcd7c78120d7a6478bce07c60b70207f7aa19e71ebdb37eecccbde475e051a28209fdfb5c9a217ea08d335422244574465e73c39863c8f18874fa', 0),
(7, '', 'Daniel ', 'dg891015@gmail.com', '3141147963', 'f054dbe257125cef40f21e69eaf02e44680f2bf94caf824dd3fe915c47dc0a692493e863e43a55a48640f1ac3dee20d75002fccf84158c4b01ad13d7393dc263', 0),
(8, '', 'Enrique de Jesús Ochoa Preciado', 'enriciado@gmail.com', '3141076334', 'e50d11e94bafcd7c78120d7a6478bce07c60b70207f7aa19e71ebdb37eecccbde475e051a28209fdfb5c9a217ea08d335422244574465e73c39863c8f18874fa', 0),
(9, '', 'Enrique de Jesús Ochoa Preciado ', 'enriquej.ochoap@outlook.es', '3141076334', 'cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e', 0),
(10, '', 'Enrique de Jesús Ochoa Preciado ', 'pruebas@correo.com', '3141076334', 'e50d11e94bafcd7c78120d7a6478bce07c60b70207f7aa19e71ebdb37eecccbde475e051a28209fdfb5c9a217ea08d335422244574465e73c39863c8f18874fa', 0),
(11, '', 'Dorian ', 'draygoza@ucol.mx', '3141972359', '55e080468f5c159a34f7b3b8e677e5e4bced352c4d74326668a5dfdeaf887218b7cfc0ee67d9ff8e8c7a37a30d41af7330e9f64cc23984b5fbb971121598465c', 0),
(12, '', 'axel', 'oguzman3@ucol.mx', '3142175357', 'd9e6762dd1c8eaf6d61b3c6192fc408d4d6d5f1176d0c29169bc24e71c3f274ad27fcd5811b313d681f7e55ec02d73d499c95455b6b5bb503acf574fba8ffe85', 0),
(13, '', 'Enrique ', 'prueba@correo.com', '3141076334', '0439434dae91c10c3bc073af1e76addf8f57a30ce0a7de0438b3aaad34b85200d41d01078f2ee786b3130b4ed4e39e3e26090da5d9f87420454dfdd182761cce', 0),
(14, '', 'Fiso', 'fiso21@gmail.com', '314123456', 'e50d11e94bafcd7c78120d7a6478bce07c60b70207f7aa19e71ebdb37eecccbde475e051a28209fdfb5c9a217ea08d335422244574465e73c39863c8f18874fa', 0),
(15, '', 'abc', 'abc@gmail.com', '3141234556', 'cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e', 0),
(16, '', 'yoyo', 'serpiente@gmail.com', '321456789', 'cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e', 0),
(17, '', 'Adriel', 'fiso@gmail.com', '147369258', 'd51ba18f500c2e58f1d91c79e42db27e10d06171408d4c43860ea612d025709a716ad8b6f7215d4af4c8325aaf9519693d97d1768843d5608471b420b9a2468d', 0),
(18, '', 'UdeC', 'UniCol@gmail.com', '159753258', 'ac9e35cb911a9b4a7fd1ff452c646861b5260af936e35640d9e104f802da80de64009f5c59ea0f46516fa9715096482fa7d6ca0dd2d9bbc61d51150d285d3ef2', 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD CONSTRAINT `publicaciones_ibfk_2` FOREIGN KEY (`email_user`) REFERENCES `usuarios` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
