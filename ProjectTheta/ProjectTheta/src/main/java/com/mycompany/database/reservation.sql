CREATE DATABASE IF NOT EXISTS reservation;
USE reservation;
create table if not exists cliente(
    idCliente int auto_increment
        primary key,
    nombre    varchar(25)  not null,
    apellido  varchar(25)  not null,
    dni       varchar(8)   not null,
    telefono  varchar(25)  not null,
    direccion varchar(100) not null
);
create table if not exists personal(
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
create table if not exists servicios(
    idServicio  int auto_increment
        primary key,
    tipo        varchar(25)    not null,
    descripcion varchar(200)   not null,
    precio      double(255, 2) not null
);
create table if not exists tipopago(
    idTipoPago  int         not null,
    descripcion varchar(20) not null,
    primary key (idTipoPago)
);
create table if not exists tipopedido(
    idTipoPedido int         not null,
    descripcion  varchar(20) not null,
    primary key (idTipoPedido)
);
create table if not exists pedidos(
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
create table if not exists detallepedido(
    idPedido       int            not null,
    idServicio     int            not null,
    cantidadPlatos int            not null,
    subtotal       double(255, 2) not null,
    primary key (idPedido, idServicio),
    constraint detallepedido_ibfk_1
        foreign key (idPedido) references pedidos (idPedido),
    constraint detallepedido_ibfk_2
        foreign key (idServicio) references servicios (idServicio)
);

create index if not exists idServicio
    on detallepedido (idServicio);
create index if not exists idCliente
    on pedidos (idCliente);
create index if not exists idPersonal
    on pedidos (idPersonal);
create index if not exists idTipoPago
    on pedidos (idTipoPago);
create index if not exists idTipoPedido
    on pedidos (idTipoPedido);

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

CREATE PROCEDURE updateEmployee(
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
    IN idServicio INT,
    IN cantidadPlatos INT,
    IN subtotal DOUBLE(255, 2)
)
BEGIN
    INSERT INTO detallepedido (idPedido, idServicio, cantidadPlatos, subtotal)
    VALUES (idPedido, idServicio, cantidadPlatos, subtotal);
END;

/*PROCEDURES CRUD MENU*/

CREATE PROCEDURE menuList()
BEGIN
SELECT * FROM servicios;
END;

CREATE PROCEDURE createMenu(
    IN idServicio INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
BEGIN
    INSERT INTO servicios (idServicio, tipo, descripcion, precio)
    VALUES (idServicio, tipo, descripcion, precio);
END;

CREATE PROCEDURE updateMenu(
    IN idServicio INT,
    IN tipo VARCHAR(25),
    IN descripcion VARCHAR(200),
    IN precio DOUBLE(255, 2)
)
BEGIN
    UPDATE servicios
    SET tipo = tipo, descripcion = descripcion, precio = precio
    WHERE idServicio = idServicio;
END;

CREATE PROCEDURE deleteMenu(
    IN idServicio INT
)
BEGIN
    DELETE FROM servicios
    WHERE idServicio = idServicio;
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
