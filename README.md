# Bibliotekssystem

Detta är ett enkelt bibliotekssystem som är utvecklat i Java.  
Programmet är en konsolapplikation och är kopplat till en MySQL databas.

# Om projektet

Syftet med projektet är att öva på:
- Java-programmering
- Databasanslutning med JDBC
- Grundläggande SQL
- Strukturering av kod med DAO och modellklasser

Systemet kan hantera användare och böcker i en databas.

# Funktioner

- Logga in som användare
- Lägga till nya böcker
- Visa sparade böcker
- Uppdatera användarinformation
- Koppla programmet till en MySQL-databas

# Tekniker som används

- Java
- MySQL
- JDBC
- Maven
- Git

# Databas

Databasens namn:
bibliotek

Exempel på tabeller:
- users
- books

# Hur man kör programmet
1. Starta MySQL.
2. Skapa databasen:
   CREATE DATABASE bibliotek;
3. Kontrollera användarnamn och lösenord i filen DatabaseConnection.java.
4. Kör Main-klassen för att starta programmet.

# Projektstruktur
src/main/java/org/example/
- dao
- model
- DatabaseConnection.java
- Main.java
