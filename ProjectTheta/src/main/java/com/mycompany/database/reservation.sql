/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.21-MariaDB : Database - reservation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`reservation` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `reservation`;

/*Table structure for table `categoria` */

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `categoria` */

insert  into `categoria`(`idCategoria`,`nombre`) values 
(1,'Ensaladas'),
(2,'Guarniciones'),
(3,'Sopas'),
(4,'Carnes'),
(5,'Pollo'),
(6,'Criollos'),
(7,'Mariscos'),
(8,'Infantil'),
(9,'Postres'),
(10,'Bebidas');

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `cliente` */

insert  into `cliente`(`idCliente`,`nombre`,`apellido`,`dni`,`telefono`,`direccion`) values 
(1,'Invitado','-','-','-','-');

/*Table structure for table `detallepedido` */

DROP TABLE IF EXISTS `detallepedido`;

CREATE TABLE `detallepedido` (
  `idPedido` int(11) NOT NULL,
  `idMenu` int(11) NOT NULL,
  `cantidadPlatos` int(11) NOT NULL,
  `subtotal` double(255,2) NOT NULL,
  PRIMARY KEY (`idPedido`,`idMenu`),
  KEY `detallepedido_ibfk_2` (`idMenu`),
  CONSTRAINT `detallepedido_ibfk_1` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`idPedido`),
  CONSTRAINT `detallepedido_ibfk_2` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `detallepedido` */

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `idMenu` int(11) NOT NULL AUTO_INCREMENT,
  `idCategoria` int(11) NOT NULL,
  `tipo` varchar(25) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `precio` double(255,2) NOT NULL,
  PRIMARY KEY (`idMenu`),
  KEY `menus_ibfk_1` (`idCategoria`),
  CONSTRAINT `menus_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `menu` */

insert  into `menu`(`idMenu`,`idCategoria`,`tipo`,`descripcion`,`precio`) values 
(1,5,'Pollo con Papas','1 Pollo Entero\n1 Porcion Familiar de Papas',50.00);

/*Table structure for table `pedidos` */

DROP TABLE IF EXISTS `pedidos`;

CREATE TABLE `pedidos` (
  `idPedido` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `fechaPedido` varchar(25) NOT NULL,
  `idTipoPedido` int(11) NOT NULL,
  `idPersonal` int(11) NOT NULL,
  `idCliente` int(11) NOT NULL,
  `idTipoPago` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `igv` double(255,2) DEFAULT NULL,
  `total` double(255,2) NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `idCliente` (`idCliente`),
  KEY `idPersonal` (`idPersonal`),
  KEY `idTipoPago` (`idTipoPago`),
  KEY `idTipoPedido` (`idTipoPedido`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`idPersonal`) REFERENCES `personal` (`idPersonal`),
  CONSTRAINT `pedidos_ibfk_3` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `pedidos_ibfk_4` FOREIGN KEY (`idTipoPago`) REFERENCES `tipopago` (`idTipoPago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `pedidos` */

/*Table structure for table `personal` */

DROP TABLE IF EXISTS `personal`;

CREATE TABLE `personal` (
  `idPersonal` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) NOT NULL,
  `apellidos` varchar(25) NOT NULL,
  `telefono` varchar(12) NOT NULL,
  `usuario` varchar(25) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `horaInicio` varchar(25) DEFAULT NULL,
  `horaFin` varchar(25) DEFAULT NULL,
  `diaDescanso` varchar(10) DEFAULT NULL,
  `nombreCargo` varchar(30) NOT NULL,
  PRIMARY KEY (`idPersonal`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `personal` */

insert  into `personal`(`idPersonal`,`nombre`,`apellidos`,`telefono`,`usuario`,`password`,`horaInicio`,`horaFin`,`diaDescanso`,`nombreCargo`) values 
(1,'Admin','Admin','999999999','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','12:00','23:00','Lunes','ADMIN'),
(2,'user','user','987654321',NULL,'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb','09:00','18:00','Miercoles','EMPLEADO');

/*Table structure for table `tipopago` */

DROP TABLE IF EXISTS `tipopago`;

CREATE TABLE `tipopago` (
  `idTipoPago` int(11) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  PRIMARY KEY (`idTipoPago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tipopago` */

insert  into `tipopago`(`idTipoPago`,`descripcion`) values 
(1,'Efectivo'),
(2,'Tarjeta'),
(3,'Billetera_Digital');

/*Table structure for table `tipopedido` */

DROP TABLE IF EXISTS `tipopedido`;

CREATE TABLE `tipopedido` (
  `idTipoPedido` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(20) NOT NULL,
  PRIMARY KEY (`idTipoPedido`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tipopedido` */

insert  into `tipopedido`(`idTipoPedido`,`descripcion`) values 
(1,'Salon'),
(2,'Reserva'),
(3,'PickUp'),
(4,'Delivery'),
(5,'Especial'),
(6,'Otro');

/* Procedure structure for procedure `categoryList` */

/*!50003 DROP PROCEDURE IF EXISTS  `categoryList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `categoryList`()
SELECT * FROM categoria */$$
DELIMITER ;

/* Procedure structure for procedure `createCategory` */

/*!50003 DROP PROCEDURE IF EXISTS  `createCategory` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createCategory`(
    IN nuevoNombre VARCHAR(25)
)
INSERT INTO categoria(nombre)
	VALUES(nuevoNombre) */$$
DELIMITER ;

/* Procedure structure for procedure `createCustomer` */

/*!50003 DROP PROCEDURE IF EXISTS  `createCustomer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createCustomer`(
    IN nombreCliente VARCHAR(25),
    IN apellidoCliente VARCHAR(25),
    IN dniCliente VARCHAR(8),
    IN telefonoCliente VARCHAR(25),
    IN direccionCliente VARCHAR(100)
)
INSERT INTO cliente (nombre, apellido, dni, telefono, direccion)
VALUES (nombreCliente, apellidoCliente, dniCliente, telefonoCliente, direccionCliente) */$$
DELIMITER ;

/* Procedure structure for procedure `createEmployee` */

/*!50003 DROP PROCEDURE IF EXISTS  `createEmployee` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createEmployee`(
    IN nombreEmpleado VARCHAR(25),
    IN apellidoEmpleado VARCHAR(25),
    IN telefonoEmpleado VARCHAR(12),
    IN usernameEmpleado VARCHAR(25),
    in passwordEmpleado VARCHAR(100),
    IN horaInicioEmpleado VARCHAR(25),
    IN horaFinEmpleado VARCHAR(25),
    IN diaDescansoEmpleado VARCHAR(10),
    IN nombreCargoEmpleado VARCHAR(30)
)
INSERT INTO personal (nombre, apellidos, telefono, usuario, password, horaInicio, horaFin, diaDescanso, nombreCargo)
VALUES (nombreEmpleado, apellidoEmpleado, telefonoEmpleado, usernameEmpleado, passwordEmpleado, horaInicioEmpleado, horaFinEmpleado, diaDescansoEmpleado, nombreCargoEmpleado) */$$
DELIMITER ;

/* Procedure structure for procedure `createMenu` */

/*!50003 DROP PROCEDURE IF EXISTS  `createMenu` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createMenu`(
    IN idMenu INT,
    IN idCategoria INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
INSERT INTO menu (idMenu, idCategoria, tipo, descripcion, precio)
    VALUES (idMenu, idCategoria, tipo, descripcion, precio) */$$
DELIMITER ;

/* Procedure structure for procedure `createOrderDetails` */

/*!50003 DROP PROCEDURE IF EXISTS  `createOrderDetails` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `createOrderDetails`(
    IN idPedido INT,
    IN idMenu INT,
    IN cantidadPlatos INT,
    IN subtotal DOUBLE(255, 2)
)
INSERT INTO detallepedido (idPedido, idMenu, cantidadPlatos, subtotal)
    VALUES (idPedido, idMenu, cantidadPlatos, subtotal) */$$
DELIMITER ;

/* Procedure structure for procedure `customerList` */

/*!50003 DROP PROCEDURE IF EXISTS  `customerList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `customerList`()
SELECT * FROM cliente */$$
DELIMITER ;

/* Procedure structure for procedure `deleteCustomer` */

/*!50003 DROP PROCEDURE IF EXISTS  `deleteCustomer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCustomer`(IN idClient INT)
DELETE FROM cliente
WHERE idCliente = idClient */$$
DELIMITER ;

/* Procedure structure for procedure `deleteEmployeeById` */

/*!50003 DROP PROCEDURE IF EXISTS  `deleteEmployeeById` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteEmployeeById`(IN p_idPersonal INT)
DELETE FROM personal
    WHERE idPersonal=p_idPersonal */$$
DELIMITER ;

/* Procedure structure for procedure `deleteOrder` */

/*!50003 DROP PROCEDURE IF EXISTS  `deleteOrder` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOrder`(IN idPedido INT)
DELETE FROM pedidos
    WHERE idPedido=idPedido */$$
DELIMITER ;

/* Procedure structure for procedure `employeesList` */

/*!50003 DROP PROCEDURE IF EXISTS  `employeesList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `employeesList`()
SELECT idPersonal, nombre, apellidos, telefono, usuario, password, horaInicio, horaFin, diaDescanso, nombreCargo
    FROM personal */$$
DELIMITER ;

/* Procedure structure for procedure `menuList` */

/*!50003 DROP PROCEDURE IF EXISTS  `menuList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `menuList`()
SELECT * FROM menu */$$
DELIMITER ;

/* Procedure structure for procedure `orderDetailsList` */

/*!50003 DROP PROCEDURE IF EXISTS  `orderDetailsList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `orderDetailsList`()
SELECT * FROM detallepedido */$$
DELIMITER ;

/* Procedure structure for procedure `saveOrder` */

/*!50003 DROP PROCEDURE IF EXISTS  `saveOrder` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `saveOrder`(
    IN descripcion VARCHAR(200),
    IN total DOUBLE(255, 2),
    IN fechaPedido VARCHAR(50),
    IN status VARCHAR(50),
    IN idPersonal INT,
    IN idTipoPedido INT,
    IN idCliente INT,
    IN idTipoPago INT,
    IN igv DOUBLE(255, 2)
)
INSERT INTO pedidos (descripcion, total, fechaPedido , idPersonal, idTipoPedido, idCliente, idTipoPago, igv, status)
    VALUES (descripcion, total, fechaPedido, idPersonal, idTipoPedido, idCliente, idTipoPago, igv, status) */$$
DELIMITER ;

/* Procedure structure for procedure `tipoPagoList` */

/*!50003 DROP PROCEDURE IF EXISTS  `tipoPagoList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoPagoList`()
SELECT * FROM tipoPago */$$
DELIMITER ;

/* Procedure structure for procedure `tipoPedidoList` */

/*!50003 DROP PROCEDURE IF EXISTS  `tipoPedidoList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `tipoPedidoList`()
SELECT * FROM tipoPedido */$$
DELIMITER ;

/* Procedure structure for procedure `updateCustomer` */

/*!50003 DROP PROCEDURE IF EXISTS  `updateCustomer` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCustomer`(
    IN idClient INT,
    IN nombreCliente VARCHAR(25),
    IN apellidoCliente VARCHAR(25),
    IN dniCliente VARCHAR(8),
    IN telefonoCliente VARCHAR(25),
    IN direccionCliente VARCHAR(100)
)
UPDATE cliente
    SET nombre = nombreCliente, apellido = apellidoCliente, dni = dniCliente,
        telefono = telefonoCliente, direccion = direccionCliente
    WHERE idCliente = idClient */$$
DELIMITER ;

/* Procedure structure for procedure `updateEmployee` */

/*!50003 DROP PROCEDURE IF EXISTS  `updateEmployee` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEmployee`(
    IN p_idPersonal INT,
    IN nombreEmpleado VARCHAR(25),
    IN apellidoEmpleado VARCHAR(25),
    IN telefonoEmpleado VARCHAR(12),
    IN usernameEmpleado VARCHAR(25),
    IN passwordEmpleado VARCHAR(100),
    IN horaInicioEmpleado VARCHAR(25),
    IN horaFinEmpleado VARCHAR(25),
    IN diaDescansoEmpleado VARCHAR(10),
    IN nombreCargoEmpleado VARCHAR(30))
UPDATE personal
    SET nombre = nombreEmpleado,apellidos = apellidoEmpleado,telefono = telefonoEmpleado,usuario = usernameEmpleado,password = passwordEmpleado,horaInicio = horaInicioEmpleado,horaFin = horaFinEmpleado,diaDescanso = diaDescansoEmpleado,nombreCargo = nombreCargoEmpleado
    WHERE idPersonal = p_idPersonal */$$
DELIMITER ;

/* Procedure structure for procedure `updateMenu` */

/*!50003 DROP PROCEDURE IF EXISTS  `updateMenu` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMenu`(
    IN idMenu INT,
    IN idCategoria INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
UPDATE menu
    SET idCategoria = idCategoria, tipo = tipo, descripcion = descripcion, precio = precio
    WHERE idMenu = idMenu */$$
DELIMITER ;

/* Procedure structure for procedure `updateOrder` */

/*!50003 DROP PROCEDURE IF EXISTS  `updateOrder` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateOrder`(
    IN idPedido INT,
    IN descripcion VARCHAR(200),
    IN total DOUBLE(255, 2),
    IN fechaPedido VARCHAR(50),
    IN status VARCHAR(50),
    IN idPersonal INT,
    IN idTipoPedido INT,
    IN idCliente INT,
    IN idTipoPago INT,
    IN igv DOUBLE(255, 2)
)
UPDATE pedidos
    SET descripcion = descripcion, total = total, fechaPedido = fechaPedido, idPersonal = idPersonal,
        idTipoPedido = idTipoPedido, idCliente = idCliente, idTipoPago = idTipoPago, igv = igv, Status = status
    WHERE idPedido = idPedido */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
