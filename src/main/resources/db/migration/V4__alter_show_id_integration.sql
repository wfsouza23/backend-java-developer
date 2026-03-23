-- Migration V4: alterar coluna id_integration de INTEGER para VARCHAR(36)

ALTER TABLE SHOW
ALTER COLUMN id_integration TYPE VARCHAR(36) USING id_integration::VARCHAR;

ALTER TABLE SHOW
    ALTER COLUMN id_integration SET NOT NULL;

ALTER TABLE SHOW
    ADD CONSTRAINT show_id_integration_unique UNIQUE (id_integration);


-- alterar coluna id_integration de INTEGER para VARCHAR(36)

ALTER TABLE EPISODE
ALTER COLUMN id_integration TYPE VARCHAR(36) USING id_integration::VARCHAR;

ALTER TABLE EPISODE
    ALTER COLUMN id_integration SET NOT NULL;

ALTER TABLE EPISODE
    ADD CONSTRAINT episode_id_integration_unique UNIQUE (id_integration);
