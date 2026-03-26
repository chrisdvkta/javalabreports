# Advanced Java Programming - Lab Reports

This repo is organized as:

- `unit1_programming_in_java/` - Unit 1 programs (one `.java` file per question)
- `lab2_swing_event_handling/` - Lab 2 Swing programs (one `.java` file per question)
- `lab3_database_connectivity/` - Lab 3 JDBC programs (one `.java` file per question)
- `lab4_network_programming/` - Lab 4 socket programs (one `.java` file per question)
- `lab5_servlets_jsp/` - Lab 5 servlet/JSP examples (one file per question)
- `lab6_rmi_corba/` - Lab 6 RMI programs (one `.java` file per question)

## Compile + run (example)

From the folder containing the `.java` file:

```bash
javac FileName.java
java ClassName
```

Example:

```bash
cd unit1_programming_in_java
javac Unit1Q01WelcomeToJavaProgramming.java
java Unit1Q01WelcomeToJavaProgramming
```

Notes:
- For `Unit1Q16DeserializationDemo`, run `Unit1Q15SerializationDemo` once first to create the `.ser` file.
- Each program prints `done by Krish Devkota` to the console.
- Lab 3 programs require a JDBC driver (e.g., MySQL Connector/J) at runtime and a database URL/user/pass.

## Lab 3 DB (Docker)

Start MySQL (creates DB `ajp` + seeds `students` table):

```bash
docker compose up -d
```

Defaults used by Lab 3 programs:
- Docker publishes MySQL on host port `${AJP_MYSQL_PORT:-3310}` (container port `3306`).
- `DB_URL=jdbc:mysql://localhost:${AJP_MYSQL_PORT:-3310}/ajp`
- `DB_USER=ajp`
- `DB_PASS=ajp`

### Running Lab 3 (JDBC driver)

If you run with plain `java`, you must add the MySQL JDBC driver (Connector/J) to the classpath.

Easiest option (downloads Connector/J via Maven into `lab3_database_connectivity/lib/` and runs):

```bash
cd lab3_database_connectivity
./run_lab3.sh Lab3Q01ScrollableResultSet
./run_lab3.sh Lab3Q02UpdatableResultSet
```

Stop:

```bash
docker compose down
```

## Lab 5 (Servlets + JSP)

`lab5_servlets_jsp/` contains servlet/JSP examples intended to be deployed to a servlet container (e.g., Tomcat).

- These examples use `jakarta.servlet` annotations (Tomcat 10+). If you use Tomcat 9, change imports from `jakarta.*` to `javax.*`.
- For DB-based examples (login + CRUD), start the MySQL Docker service and add MySQL Connector/J to your server/webapp classpath.

## Lab 6 (RMI)

Compile:

```bash
cd lab6_rmi_corba
javac *.java
```

Run (needs 2 terminals):

```bash
# Terminal 1
java Lab6Q01RmiSum server 1099

# Terminal 2
java Lab6Q01RmiSum client 1099 5 7
```
