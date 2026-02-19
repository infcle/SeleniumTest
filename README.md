# SeleniumTest

Proyecto de pruebas UI con Selenium + TestNG y Maven.

## Requisitos

- Java 17
- Maven
- Navegador Chrome y/o Firefox instalados

## Estructura del proyecto

- `src/test/java/proyecto/testng` contiene las clases de pruebas.
- `pom.xml` define dependencias (Selenium, TestNG, WebDriverManager, Allure) y el plugin de
  Surefire.
- `target/allure-results` se genera con los resultados para Allure al ejecutar los tests.

## Descripcion de las pruebas

- `PruebaSeleniumTest`: abre `https://google.com` y muestra el titulo.
- `Laboratorio1Test`: abre `https://www.toolsqa.com/` con Firefox.
- `LoginSauceDemoTest`: login en `https://www.saucedemo.com/`.
- `LoginBlazeDemoTest`: registro en `https://www.demoblaze.com/` y valida el mensaje del alert.
- `LoginDemoClaseTest`:
    - `primeraPruebaSelenium`: registro en Demoblaze y validacion con Allure.
    - `test2` y `test3`: ejemplos simples con TestNG.

## Comandos utiles

Ejecutar todos los tests:

```bash
mvn test
```

Ejecutar todos los tests en Chrome:

```bash
mvn -Dbrowser=chrome test
```

Ejecutar todos los tests en Firefox:

```bash
mvn -Dbrowser=firefox test
```

Ejecutar todos los tests en Edge:

```bash
mvn -Dbrowser=edge test
```

Ejecutar un test especifico:

```bash
mvn -Dtest=LoginSauceDemoTest test
```

Ejecutar un test especifico en otro navegador:

```bash
mvn -Dtest=LoginSauceDemoTest -Dbrowser=firefox test
```

```bash
mvn -Dtest=LoginSauceDemoTest -Dbrowser=edge test
```

Ejecutar un metodo especifico:

```bash
mvn -Dtest=LoginDemoClaseTest#primeraPruebaSelenium test
```

Ejecutar por grupo TestNG:

```bash
mvn -Dgroups=regression test
```

Ver resultados de Allure (si tienes Allure CLI instalado):

```bash
allure serve target/allure-results
```

## Notas

- WebDriverManager descarga y configura los drivers automáticamente.
- Algunas pruebas usan `Thread.sleep`, por lo que pueden tardar mas.
