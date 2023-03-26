CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SCHEMA IF NOT EXISTS demo;

CREATE TABLE IF NOT EXISTS tasks (
    task_id uuid default uuid_generate_v4(),
    name VARCHAR(255),
      description VARCHAR(255),
        PRIMARY KEY (task_id)
                                    );
