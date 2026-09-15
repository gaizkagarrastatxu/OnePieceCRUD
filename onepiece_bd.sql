-- 1. Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS onepiece_db;
USE onepiece_db;

-- 2. Crear la tabla de piratas
DROP TABLE IF EXISTS piratas;

CREATE TABLE piratas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    banda VARCHAR(100),
    recompensa BIGINT,
    fruta_del_diablo VARCHAR(100)
);

-- 3. Insertar datos iniciales de prueba
INSERT INTO piratas (nombre, banda, recompensa, fruta_del_diablo) VALUES
('Monkey D. Luffy', 'Sombrero de Paja', 3000000000, 'Hito Hito no Mi: Modelo Nika'),
('Roronoa Zoro', 'Sombrero de Paja', 1111000000, NULL),
('Sanji', 'Sombrero de Paja', 1032000000, NULL),
('Trafalgar D. Water Law', 'Piratas del Corazón', 3000000000, 'Ope Ope no Mi'),
('Eustass Kid', 'Piratas de Kid', 3000000000, 'Jiki Jiki no Mi'),
('Shanks', 'Piratas del Pelirrojo', 4048900000, NULL),
('Buggy', 'Cross Guild', 3189000000, 'Bara Bara no Mi'),
('Marshall D. Teach', 'Piratas de Barbanegra', 3996000000, 'Yami Yami no Mi');