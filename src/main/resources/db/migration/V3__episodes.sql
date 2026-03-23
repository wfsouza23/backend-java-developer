CREATE TABLE episode (
                         id BIGSERIAL PRIMARY KEY,
                         id_integration INTEGER NOT NULL,
                         fk_show VARCHAR(36) NOT NULL REFERENCES show(id) ON DELETE CASCADE,
                         name VARCHAR(255),
                         season INT,
                         number INT,
                         type VARCHAR(100),
                         airdate DATE,
                         airtime VARCHAR(50),
                         airstamp TIMESTAMP,
                         runtime INT,
                         rating NUMERIC(3,1),
                         summary TEXT,
                         created_at TIMESTAMPTZ DEFAULT NOW(),
                         updated_at TIMESTAMPTZ DEFAULT NOW()
);
