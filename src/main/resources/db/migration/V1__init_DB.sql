CREATE SEQUENCE IF NOT EXISTS seq_note_id
    START WITH 1
    INCREMENT BY 1;


CREATE TABLE IF NOT EXISTS notes
(
    id BIGINT DEFAULT nextval('seq_note_id'),
    title VARCHAR(255),
    content TEXT,
    CONSTRAINT pk_notes_id PRIMARY KEY(id),
    CONSTRAINT chk_title_length CHECK (LENGTH(title) BETWEEN 3 AND 255)
    );
