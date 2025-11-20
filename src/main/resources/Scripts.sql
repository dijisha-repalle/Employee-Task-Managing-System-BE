-- PostgreSQL version
CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    status VARCHAR(50) DEFAULT 'PENDING',

    assigned_to BIGINT,
    assigned_by BIGINT,

    CONSTRAINT fk_task_assigned_to
        FOREIGN KEY (assigned_to) REFERENCES employees(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_task_assigned_by
        FOREIGN KEY (assigned_by) REFERENCES employees(id)
        ON DELETE SET NULL
);
