CREATE DATABASE IF NOT EXISTS catalogoearte;
USE catalogoearte;
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
    tienda_id BIGINT,                     -- Relación con la tienda (puede ser null)
    nombre VARCHAR(100) NOT NULL,              -- Nombre del usuario
    apellido VARCHAR(100) NOT NULL,            -- Apellido del usuario
	rol VARCHAR(100) NOT NULL,				   -- Rol del usuario
    habilitado BOOLEAN NOT NULL,               -- Estado de habilitación
    FOREIGN KEY (tienda_id) REFERENCES tienda(codigo) -- Clave foránea a tienda
);

CREATE TABLE IF NOT EXISTS producto (
    codigo BIGINT PRIMARY KEY,   -- Código único del producto
    nombre VARCHAR(255) NOT NULL,     -- Nombre del producto
    talle VARCHAR(10) NOT NULL,       -- Talle del producto
    foto VARCHAR(255),                -- Ruta de la foto del producto
    color VARCHAR(50) NOT NULL        -- Color del producto
);

CREATE TABLE catalogo (
    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,	-- ID único autoincremental
    tienda_id BIGINT(10) NOT NULL,	-- Relación con la tienda (puede ser null)
    CONSTRAINT fk_catalogo_tienda FOREIGN KEY (tienda_id) REFERENCES Tienda(codigo)
);

CREATE TABLE catalogo_producto (
    catalogo_id BIGINT(20) NOT NULL,	-- Relación con el catalogo
    producto_codigo BIGINT(10) NOT NULL,	-- Relación con el producto
    PRIMARY KEY (catalogo_id, producto_codigo),
    CONSTRAINT fk_catalogo_producto_catalogo FOREIGN KEY (catalogo_id) REFERENCES Catalogo(id),
    CONSTRAINT fk_catalogo_producto_producto FOREIGN KEY (producto_codigo) REFERENCES Producto(codigo)
);