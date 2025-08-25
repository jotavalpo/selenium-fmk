# Introducción
Repositorio para pruebas automatizadas usando Selenium y Java.

URL ambiente: https://demoblaze.com/

# Para ejecutar el proyecto
Por primera vez:
`mvn clean install -Dmaven.test.skip=true`

Para ejecutar los tests modo UI:
`mvn clean test`

Ejecutar tests modo headless:
`mvn clean test -Dheadless=true`

# Stack
- Selenium WebDriver 4.25.0
- Java versión 11
- Maven
- TestNG
