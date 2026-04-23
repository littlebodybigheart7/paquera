CREATE TABLE IF NOT EXISTS paquera(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    idade  VARCHAR(11)  NOT NULL 
);

