INSERT INTO students (id, name, age)
VALUES
  (1, 'Krish', 21),
  (2, 'Sita', 20),
  (3, 'Ram', 22)
ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  age = VALUES(age);

