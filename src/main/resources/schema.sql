CREATE TABLE Forecast (
   id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
   date DATE,
   temperature INT,
   winddirection VARCHAR(50),
   UNIQUE (date)
);
