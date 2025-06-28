CREATE TABLE budgets (
                         uuid UUID PRIMARY KEY,
                         year INT NOT NULL,
                         month VARCHAR(20),
                         amount DECIMAL(19,2) NOT NULL,
                         currency VARCHAR(3) NOT NULL,
                         locked BOOLEAN NOT NULL,
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL,
                         created_by VARCHAR(255) NOT NULL,
                         modified_by VARCHAR(255) NOT NULL
);

CREATE TABLE spendings (
                           uuid UUID PRIMARY KEY,
                           name VARCHAR(255),
                           amount DECIMAL(19,2) NOT NULL,
                           currency VARCHAR(3) NOT NULL,
                           budget_uuid UUID NOT NULL,
                           created_at TIMESTAMP NOT NULL,
                           updated_at TIMESTAMP NOT NULL,
                           created_by VARCHAR(255) NOT NULL,
                           modified_by VARCHAR(255) NOT NULL,
                           CONSTRAINT fk_budget
                               FOREIGN KEY (budget_uuid)
                               REFERENCES budgets(uuid)
                               ON DELETE CASCADE
);