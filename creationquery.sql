CREATE DATABASE IF NOT EXISTS stockearte;
USE stockearte;
CREATE TABLE IF NOT EXISTS tienda (
    codigo BIGINT PRIMARY KEY,  -- Código único de tienda
    direccion VARCHAR(255) NOT NULL, -- Dirección de la tienda
    ciudad VARCHAR(100) NOT NULL,    -- Ciudad de la tienda
    provincia VARCHAR(100) NOT NULL, -- Provincia de la tienda
    habilitada BOOLEAN NOT NULL      -- Estado de habilitación
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,      -- ID único autoincremental
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE, -- Nombre de usuario único
    contrasena VARCHAR(255) NOT NULL,          -- Contraseña del usuario
    tienda_id BIGINT,                     -- Relación con la tienda 
    nombre VARCHAR(100) NOT NULL,              -- Nombre del usuario
    apellido VARCHAR(100) NOT NULL,            -- Apellido del usuario
	rol VARCHAR(100) NOT NULL,				   -- Rol del usuario
    habilitado BOOLEAN NOT NULL,               -- Estado de habilitación
    FOREIGN KEY (tienda_id) REFERENCES tienda(codigo) 
);

CREATE TABLE IF NOT EXISTS producto (
    codigo BIGINT PRIMARY KEY,   -- Código único del producto
    nombre VARCHAR(255) NOT NULL,     -- Nombre del producto
    talle VARCHAR(10) NOT NULL,       -- Talle del producto
    foto VARCHAR(255),                -- Ruta de la foto del producto
    color VARCHAR(50) NOT NULL        -- Color del producto
);

CREATE TABLE IF NOT EXISTS catalogo (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- ID único autoincremental
    tienda_id BIGINT(10) NOT NULL,	-- Relación con la tienda 
    CONSTRAINT fk_catalogo_tienda FOREIGN KEY (tienda_id) REFERENCES tienda(codigo)
);

CREATE TABLE IF NOT EXISTS catalogo_producto (
    catalogo_id BIGINT(20) NOT NULL,	-- Relación con el catalogo
    producto_codigo BIGINT(10) NOT NULL,	-- Relación con el producto
    PRIMARY KEY (catalogo_id, producto_codigo),
    CONSTRAINT fk_catalogo_producto_catalogo FOREIGN KEY (catalogo_id) REFERENCES catalogo(id),
    CONSTRAINT fk_catalogo_producto_producto FOREIGN KEY (producto_codigo) REFERENCES producto(codigo)
);

CREATE TABLE IF NOT EXISTS orden_de_compra (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- ID único autoincremental
    fecha DATE,		-- Fecha de la compra
    estado VARCHAR(255),	-- Estado de la compra
    tienda_codigod BIGINT(10) NOT NULL,	-- Relación con la tienda
    CONSTRAINT fk_ordencompra_tienda FOREIGN KEY (tienda_codigod) REFERENCES tienda(codigo)
);

CREATE TABLE IF NOT EXISTS item_orden_compra (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- ID único autoincremental
    cantidad INT NOT NULL,	-- Cantidad del producto
    producto_codigo BIGINT(10) NOT NULL,	-- Relación con el producto
    id_orden_compra BIGINT(20) NOT NULL,	-- Relación con la orden de compra
    CONSTRAINT fk_itemordencompra_producto FOREIGN KEY (producto_codigo) REFERENCES producto(codigo),
    CONSTRAINT fk_itemordencompra_ordencompra FOREIGN KEY (id_orden_compra) REFERENCES orden_de_compra(id)
);

CREATE TABLE IF NOT EXISTS filtro (
    nombre VARCHAR(255) NOT NULL UNIQUE,
    usuario_id BIGINT(20) NOT NULL,
    tienda_codigo BIGINT(20) NOT NULL,
    producto_codigo BIGINT(20) NOT NULL,
    PRIMARY KEY (nombre),
    CONSTRAINT fk_filtro_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_filtro_tienda FOREIGN KEY (tienda_codigo) REFERENCES tienda(codigo),
    CONSTRAINT fk_filtro_producto FOREIGN KEY (producto_codigo) REFERENCES producto(codigo)
);