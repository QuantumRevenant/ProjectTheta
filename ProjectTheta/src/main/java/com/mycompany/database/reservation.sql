CREATE DATABASE IF NOT EXISTS `reservation`;
USE `reservation`;

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS`categoria` (
  `idCategoria` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `nombre` varchar(25) NOT NULL
);

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `idMenu` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
   `idCategoria` int(11) NOT NULL,
   `tipo` varchar(25) NOT NULL,
   `descripcion` varchar(200) NOT NULL,
   `precio` double(255,2) NOT NULL,
   KEY `menus_ibfk_1` (`idCategoria`),
   CONSTRAINT `menus_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`)
);

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `nombre` varchar(25) NOT NULL,
  `apellido` varchar(25) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `direccion` varchar(100) NOT NULL
);

DROP TABLE IF EXISTS `tipopago`;
CREATE TABLE `tipopago` (
                            `idTipoPago` int(11) NOT NULL,
                            `descripcion` varchar(20) NOT NULL,
                            PRIMARY KEY (`idTipoPago`)
);

DROP TABLE IF EXISTS `tipopedido`;
CREATE TABLE `tipopedido` (
                              `idTipoPedido` int(11) NOT NULL,
                              `descripcion` varchar(20) NOT NULL,
                              PRIMARY KEY (`idTipoPedido`)
);

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
);

DROP TABLE IF EXISTS `pedidos`;
CREATE TABLE `pedidos` (
                           `idPedido` int(11) NOT NULL AUTO_INCREMENT,
                           `descripcion` varchar(200) NOT NULL,
                           `total` double(255,2) NOT NULL,
                           `fechaPedido` varchar(25) NOT NULL,
                           `status` varchar(20) NOT NULL,
                           `idPersonal` int(11) NOT NULL,
                           `idTipoPedido` int(11) NOT NULL,
                           `idCliente` int(11) NOT NULL,
                           `idTipoPago` int(11) NOT NULL,
                           `igv` double(255,2) DEFAULT NULL,
                           PRIMARY KEY (`idPedido`),
                           KEY `idCliente` (`idCliente`),
                           KEY `idPersonal` (`idPersonal`),
                           KEY `idTipoPago` (`idTipoPago`),
                           KEY `idTipoPedido` (`idTipoPedido`),
                           CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`idPersonal`) REFERENCES `personal` (`idPersonal`),
                           CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`idTipoPedido`) REFERENCES `tipopedido` (`idTipoPedido`),
                           CONSTRAINT `pedidos_ibfk_3` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idCliente`),
                           CONSTRAINT `pedidos_ibfk_4` FOREIGN KEY (`idTipoPago`) REFERENCES `tipopago` (`idTipoPago`)
);


DROP TABLE IF EXISTS `detallepedido`;
CREATE TABLE `detallepedido` (
  `idPedido` int NOT NULL,
  `idMenu`   int NOT NULL,
  `cantidadPlatos` int(11) NOT NULL,
  `subtotal` double(255,2) NOT NULL,
  primary key (idPedido, idMenu),
  constraint detallepedido_ibfk_1
      foreign key (idPedido) references pedidos (idPedido),
  constraint detallepedido_ibfk_2
      foreign key (idMenu) references menu (idMenu)
);




insert  into `personal`(`idPersonal`,`nombre`,`apellidos`,`telefono`,`usuario`,`password`,`horaInicio`,`horaFin`,`diaDescanso`,`nombreCargo`) values 
(1,'Admin','Admin','999999999','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918','12:00','23:00','Lunes','ADMIN'),
(2,'user','user','987654321',NULL,'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb','09:00','18:00','Miercoles','EMPLEADO');





DROP PROCEDURE IF EXISTS  `createCustomer`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCustomer`(
    IN nombreCliente VARCHAR(25),
    IN apellidoCliente VARCHAR(25),
    IN dniCliente VARCHAR(8),
    IN telefonoCliente VARCHAR(25),
    IN direccionCliente VARCHAR(100)
)
INSERT INTO cliente (nombre, apellido, dni, telefono, direccion)
VALUES (nombreCliente, apellidoCliente, dniCliente, telefonoCliente, direccionCliente);

DROP PROCEDURE IF EXISTS  `createEmployee`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createEmployee`(
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
VALUES (nombreEmpleado, apellidoEmpleado, telefonoEmpleado, usernameEmpleado, passwordEmpleado, horaInicioEmpleado, horaFinEmpleado, diaDescansoEmpleado, nombreCargoEmpleado);

DROP PROCEDURE IF EXISTS  `createMenu`;
 CREATE DEFINER=`root`@`localhost` PROCEDURE `createMenu`(
    IN idMenu INT,
    IN idCategoria INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
INSERT INTO menu (idMenu, idCategoria, tipo, descripcion, precio)
    VALUES (idMenu, idCategoria, tipo, descripcion, precio);

 DROP PROCEDURE IF EXISTS  `createOrderDetails`;
 CREATE DEFINER=`root`@`localhost` PROCEDURE `createOrderDetails`(
    IN idPedido INT,
    IN idMenu INT,
    IN cantidadPlatos INT,
    IN subtotal DOUBLE(255, 2)
)
INSERT INTO detallepedido (idPedido, idMenu, cantidadPlatos, subtotal)
    VALUES (idPedido, idMenu, cantidadPlatos, subtotal);

 DROP PROCEDURE IF EXISTS  `customerList`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `customerList`()
SELECT * FROM cliente;

DROP PROCEDURE IF EXISTS  `deleteCustomer`;

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCustomer`(IN idClient INT)
DELETE FROM cliente
WHERE idCliente = idClient;


DROP PROCEDURE IF EXISTS  `deleteEmployeeById`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteEmployeeById`(IN p_idPersonal INT)
DELETE FROM personal
    WHERE idPersonal=p_idPersonal;

DROP PROCEDURE IF EXISTS  `deleteOrder`;
 CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOrder`(IN idPedido INT)
DELETE FROM pedidos
    WHERE idPedido=idPedido;

DROP PROCEDURE IF EXISTS  `employeesList`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `employeesList`()
SELECT idPersonal, nombre, apellidos, telefono, usuario, password, horaInicio, horaFin, diaDescanso, nombreCargo
    FROM personal;

DROP PROCEDURE IF EXISTS  `menuList`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `menuList`()
SELECT * FROM menu;

DROP PROCEDURE IF EXISTS  `createCategory`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `createCategory`(
    IN nuevoNombre VARCHAR(25)
)
INSERT INTO categoria(nombre)
	VALUES(nuevoNombre);

DROP PROCEDURE IF EXISTS  `categoryList`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `categoryList`()
SELECT * FROM categoria;

DROP PROCEDURE IF EXISTS  `orderDetailsList`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `orderDetailsList`()
SELECT * FROM detallepedido;

DROP PROCEDURE IF EXISTS  `saveOrder`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `saveOrder`(
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
INSERT INTO pedidos (idPedido, descripcion, total, fechaPedido , idPersonal, idTipoPedido, idCliente, idTipoPago, igv, status)
    VALUES (idPedido, descripcion, total, fechaPedido, idPersonal, idTipoPedido, idCliente, idTipoPago, igv, status);

DROP PROCEDURE IF EXISTS  `updateCustomer`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCustomer`(
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
    WHERE idCliente = idClient;

DROP PROCEDURE IF EXISTS  `updateEmployee`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEmployee`(
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
    WHERE idPersonal = p_idPersonal;
DROP PROCEDURE IF EXISTS  `updateMenu`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateMenu`(
    IN idMenu INT,
    IN idCategoria INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
UPDATE menu
    SET idCategoria = idCategoria, tipo = tipo, descripcion = descripcion, precio = precio
    WHERE idMenu = idMenu;

DROP PROCEDURE IF EXISTS  `updateOrder`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateOrder`(
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
    WHERE idPedido = idPedido;
