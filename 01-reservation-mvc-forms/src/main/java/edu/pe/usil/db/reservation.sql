CREATE TABLE employees (
                           employee_id INT PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           lastname VARCHAR(50) NOT NULL,
                           username VARCHAR(100) NOT NULL,
                           password VARCHAR(64) NOT NULL
);
INSERT INTO employees (employee_id, name, lastname, username, password)
VALUES (1, 'Andre', 'Martinez', 'andre.martinez', '123');

CREATE PROCEDURE employeesList()
BEGIN
    SELECT * FROM employees;
END;


CREATE PROCEDURE createEmployee(
    IN empId INT,
    IN empName VARCHAR(50),
    IN empLastName VARCHAR(50),
    IN empUsername VARCHAR(100),
    IN empPassword VARCHAR(64)
)
BEGIN
    INSERT INTO employees (employee_id, name, lastname, username, password)
    VALUES (empId, empName, empLastName, empUsername, empPassword);
END;

CREATE PROCEDURE updateUserAndPassEmployee(
    IN empId INT,
    IN empUsername VARCHAR(100),
    IN empPassword VARCHAR(64)
)
BEGIN
    UPDATE employees
    SET username = empUsername, password = empPassword
    WHERE employee_id = empId;
END;

CREATE PROCEDURE deleteEmployeeById(IN empId INT)
BEGIN
    DELETE FROM employees WHERE employee_id = empId;
END;

CREATE PROCEDURE verifyCredentials(
    IN empUsername VARCHAR(100),
    IN empPassword VARCHAR(64),
    OUT isValid BOOLEAN
)
BEGIN
    DECLARE passwordHash VARCHAR(64);

    SELECT password INTO passwordHash
    FROM employees
    WHERE username = empUsername;

    SET isValid = (passwordHash = empPassword);
END;