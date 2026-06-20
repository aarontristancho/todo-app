CREATE TABLE todo (
                      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      note VARCHAR(255),
                      category VARCHAR(255),
                      status VARCHAR(20) NOT NULL,
                      priority VARCHAR(20) NOT NULL
);