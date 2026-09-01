# Automatizacion de Sauce Demo y Restful Booker

Proyecto academico en Java 17 con Maven, Selenium WebDriver, JUnit 5,
Page Object Model y RestAssured.

## Contenido

- 5 pruebas web de Sauce Demo.
- 5 pruebas de la API Restful Booker.
- Inicio de sesion usado solamente como preparacion.
- Selectores estables mediante `id` y CSS.
- Page Object Model en el paquete `pages`.
- Solicitudes API GET y POST.

## Pruebas web

Los identificadores y nombres corresponden a los casos seleccionados en TestLink:

1. WEB-05 Ordenar productos por precio de menor a mayor.
2. WEB-06 Agregar producto al carrito.
3. WEB-07 Eliminar producto del carrito.
4. WEB-09 Completar compra con datos validos.
5. WEB-20 Abrir detalle de Sauce Labs Backpack.

## Pruebas API

Los identificadores y nombres corresponden a los casos seleccionados en TestLink:

1. API-01 Crear token con credenciales validas.
2. API-02 Obtener lista de identificadores de reservas.
3. API-03 Crear reserva con datos validos.
4. API-04 Consultar la reserva creada.
5. API-05 Consultar una reserva inexistente.

## Ejecutar desde Eclipse

1. Abrir Eclipse.
2. Seleccionar `File > Import`.
3. Elegir `Maven > Existing Maven Projects`.
4. Seleccionar esta carpeta y presionar `Finish`.
5. Clic derecho sobre el proyecto.
6. Seleccionar `Run As > Maven test`.

## Ejecutar desde IntelliJ IDEA Community

1. Abrir IntelliJ IDEA Community Edition.
2. Seleccionar `Open`.
3. Elegir la carpeta `automatizacion-saucedemo-api`.
4. Esperar a que Maven descargue y cargue las dependencias.
5. Abrir `src/test/java/com/ramiro/automation/web/SauceDemoWebTests.java`.
6. Presionar el triangulo verde junto al nombre de la clase para ejecutar las pruebas web.
7. Para las pruebas API, ejecutar de la misma forma `RestfulBookerApiTests.java`.

Para ejecutar las diez pruebas juntas, abrir la ventana Maven y ejecutar
`Lifecycle > test`.

## Ejecutar desde una terminal

```powershell
mvn test
```

Maven ejecuta las pruebas web sin mostrar Edge. Para ver el navegador:

```powershell
mvn test -Dheadless=false
```

El reporte queda en `target/surefire-reports`.
