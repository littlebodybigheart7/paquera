CREATE TABLE IF NOT EXISTS paquera(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    idade VARCHAR(11) NOT NULL,
    signo VARCHAR(20),
    gostade VARCHAR(255),
    naogostade VARCHAR(255),
    foto VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS usuario(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(150),
    password VARCHAR(100) NOT NULL
);
