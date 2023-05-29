create DATABASE reservation;
USE reservation;
CREATE TABLE cargos (
                        idCargo INT NOT NULL AUTO_INCREMENT,
                        nombreCargo VARCHAR(20) NOT NULL,
                        PRIMARY KEY (idCargo)
);

CREATE TABLE personal (
                          idPersonal INT NOT NULL AUTO_INCREMENT,
                          nombre VARCHAR(25) NOT NULL,
                          apellidos VARCHAR(25) NOT NULL,
                          telefono INT(9) NOT NULL,
                          idCargo INT(1) NOT NULL,
                          usuario VARCHAR(25) NULL,
                          password VARCHAR(25) NOT NULL,
                          horaInicio VARCHAR(25) NOT NULL,
                          horaFin VARCHAR(25) NOT NULL,
                          per_dia_des VARCHAR(10) NOT NULL,
                          PRIMARY KEY (idPersonal),
                          FOREIGN KEY (idCargo) REFERENCES cargos(idCargo)
);





CREATE TABLE tipoPedido (
                            idTipoPedido INT NOT NULL,
                            descripcion VARCHAR(20) NOT NULL,
                            PRIMARY KEY (idTipoPedido)
);

CREATE TABLE cliente (
                         idCliente INT NOT NULL AUTO_INCREMENT,
                         nom_cliente VARCHAR(25) NOT NULL,
                         ape_cliente VARCHAR(25) NOT NULL,
                         dni VARCHAR(8) NOT NULL,
                         telefono VARCHAR(25) NOT NULL,
                         direccion VARCHAR(100) NOT NULL,
                         PRIMARY KEY (idCliente)
);

CREATE TABLE tipoPago (
                          idTipoPago INT NOT NULL,
                          descripcion VARCHAR(20) NOT NULL,
                          PRIMARY KEY (idTipoPago)
);

CREATE TABLE servicios (
                           idServicio INT NOT NULL AUTO_INCREMENT,
                           tipo VARCHAR(25) NOT NULL,
                           descripcion VARCHAR(200) NOT NULL,
                           precio DOUBLE(255, 2) NOT NULL,
                           PRIMARY KEY (idServicio)
);

CREATE TABLE pedidos (
                         idPedido INT NOT NULL AUTO_INCREMENT,
                         descripcion VARCHAR(200) NOT NULL,
                         total DOUBLE(255, 2) NOT NULL,
                         fecha VARCHAR(25) NOT NULL,
                         estado VARCHAR(20) NOT NULL,
                         idPersonal INT NOT NULL,
                         idTipoPedido INT NOT NULL,
                         idCliente INT NOT NULL,
                         idTipoPago INT NOT NULL,
                         PRIMARY KEY (idPedido),
                         FOREIGN KEY (idPersonal) REFERENCES personal(idPersonal),
                         FOREIGN KEY (idTipoPedido) REFERENCES tipoPedido(idTipoPedido),
                         FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
                         FOREIGN KEY (idTipoPago) REFERENCES tipoPago(idTipoPago)
);

CREATE TABLE DetallePedido (
                               idPedido INT NOT NULL,
                               idServicio INT NOT NULL,
                               cantidadDetalle INT NOT NULL,
                               subtotal DOUBLE(255, 2) NOT NULL,
                               igv DOUBLE(10, 2) NOT NULL,
                               importeTotal DOUBLE(255, 2) NOT NULL,
                               PRIMARY KEY (idPedido, idServicio),
                               FOREIGN KEY (idPedido) REFERENCES pedidos(idPedido),
                               FOREIGN KEY (idServicio) REFERENCES servicios(idServicio)
);

insert into personal (idPersonal, nombre, apellidos, telefono, usuario, password, horaInicio, horaFin, diaDescanso,
                      nombreCargo)
values (1,'andre','martinez','999999999','andre','123',null,null,null,'ADMIN');
insert into personal (idPersonal, nombre, apellidos, telefono, usuario, password, horaInicio, horaFin, diaDescanso,
                      nombreCargo)
values (2,'richi','ch','111111111','richi','123',null,null,null,'EMPLEADO');