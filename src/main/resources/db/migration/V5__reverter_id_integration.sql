-- Revertendo coluna id_integration de VARCHAR(36) para INTEGER em SHOW
ALTER TABLE show
DROP CONSTRAINT IF EXISTS show_id_integration_unique;

ALTER TABLE show
ALTER COLUMN id_integration TYPE INTEGER USING id_integration::integer;

ALTER TABLE show
    ALTER COLUMN id_integration SET NOT NULL;

ALTER TABLE show
    ADD CONSTRAINT show_id_integration_unique UNIQUE (id_integration);

-- Revertendo coluna id_integration de VARCHAR(36) para INTEGER em EPISODE
ALTER TABLE episode
DROP CONSTRAINT IF EXISTS episode_id_integration_unique;

ALTER TABLE episode
ALTER COLUMN id_integration TYPE INTEGER USING id_integration::integer;

ALTER TABLE episode
    ALTER COLUMN id_integration SET NOT NULL;

ALTER TABLE episode
    ADD CONSTRAINT episode_id_integration_unique UNIQUE (id_integration);
