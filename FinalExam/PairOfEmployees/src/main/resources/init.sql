CREATE TABLE IF NOT EXISTS employee (
                                        id INT PRIMARY KEY,
                                        project_id INT,
                                        start_date DATE NOT NULL,
                                        end_date DATE
);