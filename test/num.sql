SELECT id, course, semester, Numerical_Grade, CASE
	WHEN Numerical_Grade >=90 THEN "A"
	WHEN Numerical_Grade <90 AND Numerical_Grade >= 85 THEN "B+"
	WHEN Numerical_Grade <85 AND Numerical_Grade >= 80 THEN "B"
	WHEN Numerical_Grade <80 AND Numerical_Grade >= 75 THEN "C+"
	WHEN Numerical_Grade <75 AND Numerical_Grade >= 70 THEN "C"
	WHEN Numerical_Grade <70 AND Numerical_Grade >= 65 THEN "D+"
	WHEN Numerical_Grade <65 AND Numerical_Grade >= 60 THEN "D"
	ELSE 'F' 
	END AS  Letter_Grade
FROM Student 

