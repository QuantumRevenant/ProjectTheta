CREATE DATABASE IF NOT EXISTS reservation;
USE reservation;

CREATE TABLE Categoria(
    idCategoria int  NOT NULL AUTO_INCREMENT,
    nombre varchar(25) NOT NULL,
    PRIMARY KEY(idCategoria)
);


create table cliente(
    idCliente int auto_increment
        primary key,
    nombre    varchar(25)  not null,
    apellido  varchar(25)  not null,
    dni       varchar(8)   not null,
    telefono  varchar(25)  not null,
    direccion varchar(100) not null
);
create table  personal(
    idPersonal  int auto_increment
        primary key,
    nombre      varchar(25) not null,
    apellidos   varchar(25) not null,
    telefono    varchar(12) not null,
    usuario     varchar(25) null,
    password    varchar(25) not null,
    horaInicio  varchar(25) null,
    horaFin     varchar(25) null,
    diaDescanso varchar(10) null,
    nombreCargo varchar(30) not null
);
create table  menu(
    idMenu  int auto_increment primary key,
    idCategoria int not null,
    tipo        varchar(25)    not null,
    descripcion varchar(200)   not null,
    precio      double(255, 2) not null,
        constraint menus_ibfk_1
        foreign key (idCategoria) references Categoria (idCategoria)
);
create table  tipopago(
    idTipoPago  int         not null,
    descripcion varchar(20) not null,
    primary key (idTipoPago)
);
create table tipopedido(
    idTipoPedido int         not null,
    descripcion  varchar(20) not null,
    primary key (idTipoPedido)
);
create table pedidos(
    idPedido     int auto_increment primary key,
    descripcion  varchar(200)   not null,
    total        double(255, 2) not null,
    fechaPedido  varchar(25)    not null,
    status       varchar(20)    not null,
    idPersonal   int            not null,
    idTipoPedido int            not null,
    idCliente    int            not null,
    idTipoPago   int            not null,
    igv          double(255, 2) null,
    constraint pedidos_ibfk_1
        foreign key (idPersonal) references personal (idPersonal),
    constraint pedidos_ibfk_2
        foreign key (idTipoPedido) references tipopedido (idTipoPedido),
    constraint pedidos_ibfk_3
        foreign key (idCliente) references cliente (idCliente),
    constraint pedidos_ibfk_4
        foreign key (idTipoPago) references tipopago (idTipoPago)
);
create table detallepedido(
    idPedido       int            not null,
    idMenu     int            not null,
    cantidadPlatos int            not null,
    subtotal       double(255, 2) not null,
    primary key (idPedido, idMenu),
    constraint detallepedido_ibfk_1
        foreign key (idPedido) references pedidos (idPedido),
    constraint detallepedido_ibfk_2
        foreign key (idMenu) references menu (idMenu)
);

create index idMenu
    on detallepedido (idMenu);
create index  idCliente
    on pedidos (idCliente);
create index  idPersonal
    on pedidos (idPersonal);
create index  idTipoPago
    on pedidos (idTipoPago);
create index  idTipoPedido
    on pedidos (idTipoPedido);


-- Crear o reemplazar índices



/*PROCEDURES CRUD CLIENTE*/

CREATE PROCEDURE customerList()
BEGIN
    SELECT * FROM cliente;
END;

CREATE PROCEDURE createCustomer(
    IN nombreCliente VARCHAR(25),
    IN apellidoCliente VARCHAR(25),
    IN dniCliente VARCHAR(8),
    IN telefonoCliente VARCHAR(25),
    IN direccionCliente VARCHAR(100)
)
BEGIN
INSERT INTO cliente (nombre, apellido, dni, telefono, direccion)
VALUES (nombreCliente, apellidoCliente, dniCliente, telefonoCliente, direccionCliente);
END;

CREATE PROCEDURE updateCustomer(
    IN idCliente INT,
    IN nombreCliente VARCHAR(25),
    IN apellidoCliente VARCHAR(25),
    IN dniCliente VARCHAR(8),
    IN telefonoCliente VARCHAR(25),
    IN direccionCliente VARCHAR(100)
)
BEGIN
    UPDATE cliente
    SET nombre = nombreCliente, apellido = apellidoCliente, dni = dniCliente,
        telefono = telefonoCliente, direccion = direccionCliente
    WHERE idCliente = idCliente;
END;

CREATE PROCEDURE deleteCustomer(IN idCliente INT)
BEGIN
DELETE FROM cliente
WHERE idCliente = idCliente;
END;

/*PROCEDURES CRUD DETALLE PEDIDO*/

CREATE PROCEDURE orderDetailsList()
BEGIN
    SELECT * FROM detallepedido;
END;

CREATE PROCEDURE createOrderDetails(
    IN idPedido INT,
    IN idMenu INT,
    IN cantidadPlatos INT,
    IN subtotal DOUBLE(255, 2)
)
BEGIN
    INSERT INTO detallepedido (idPedido, idMenu, cantidadPlatos, subtotal)
    VALUES (idPedido, idMenu, cantidadPlatos, subtotal);
END;

/*PROCEDURES CRUD MENU*/

CREATE PROCEDURE menuList()
BEGIN
SELECT * FROM menu;
END;

CREATE PROCEDURE createMenu(
    IN idMenu INT,
    IN idCategoria INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
BEGIN
    INSERT INTO menu (idMenu, idCategoria, tipo, descripcion, precio)
    VALUES (idMenu, idCategoria, tipo, descripcion, precio);
END;

CREATE PROCEDURE updateMenu(
    IN idMenu INT,
    IN idCategoria INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
BEGIN
    UPDATE menu
    SET idCategoria = idCategoria, tipo = tipo, descripcion = descripcion, precio = precio
    WHERE idMenu = idMenu;
END;

CREATE PROCEDURE deleteMenu(
    IN idMenu INT
)
BEGIN
    DELETE FROM menu
    WHERE idMenu = idMenu;
END;

/*PROCEDURE CRUD ORDER*/

CREATE PROCEDURE ordersList()
BEGIN
    SELECT * FROM pedidos;
END;

CREATE PROCEDURE saveOrder(
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
BEGIN
    INSERT INTO pedidos (idPedido, descripcion, total, fechaPedido , idPersonal, idTipoPedido, idCliente, idTipoPago, igv, status)
    VALUES (idPedido, descripcion, total, fechaPedido, idPersonal, idTipoPedido, idCliente, idTipoPago, igv, status);
END;

CREATE PROCEDURE updateOrder(
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
BEGIN
    UPDATE pedidos
    SET descripcion = descripcion, total = total, fechaPedido = fechaPedido, idPersonal = idPersonal,
        idTipoPedido = idTipoPedido, idCliente = idCliente, idTipoPago = idTipoPago, igv = igv, Status = status
    WHERE idPedido = idPedido;
END;

CREATE PROCEDURE deleteOrder(
    IN idPedido INT
)
BEGIN
    DELETE FROM pedidos
    WHERE idPedido = idPedido;
END;

/*PROCEDURE CRUD PERSONAL*/

CREATE PROCEDURE employeesList()
BEGIN
    SELECT idPersonal, nombre, apellidos, telefono, usuario, password, horaInicio, horaFin, diaDescanso, nombreCargo
    FROM personal;
END;

CREATE PROCEDURE updateEmployee(IN p_idPersonal INT, IN p_usuario VARCHAR(25), IN p_password VARCHAR(25))
BEGIN
    UPDATE personal
    SET usuario = p_usuario, password = p_password
    WHERE idPersonal = p_idPersonal;
END;

CREATE PROCEDURE deleteEmployeeById(IN p_idPersonal INT)
BEGIN
    DELETE FROM personal
    WHERE idPersonal = p_idPersonal;
END;
