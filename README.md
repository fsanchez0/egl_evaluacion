# egl_evaluacion

Hacer un Componente en java con las siguientes funcionalidades:

Crear un CRUD a partir de la base datos de tienda.

## Productos
- ALTAS 
- BAJAS
- CAMBIOS
## Ventas
- Registro de venta: La venta deberá de guardarse en la tabla ventas y los
  productos de la venta se deberán de ver en la tabla productos_
  ventas.
- EL costo de la venta deberá de calcularse apatir de los productos registrado en
  la venta.
- El stock de la tabla producto se deberá de decrementar al realizar una venta.
  
## Notas
- Como punto extra hacer una página web sencilla para la demostración del CRUD.
- Otro punto extra si está en una arquitectura Docker.

# Requisitos
- SpringBoot 3
- JPA
- JAVA 17 o 21

# Entregables
- Proyecto Spring
- Pasos
- Como validar el componente

# Pasos:
1. Crear usuario/pwd para base de datos 'tienda_db_user' 'rUIr9RPHEQLJCIZz'
2. Instalar docker-compose: https://docs.docker.com/compose/install/#prerequisites
3. Ejecutar comando
> docker-compose up

3. Acceder al servidor http://localhost:3000

# Pasos (Sin Docker):
En caso de tener problemas con Docker.

Prerequisitos: maven, node, crear usuario/pwd para base de datos 'tienda_db_user' 'rUIr9RPHEQLJCIZz'

Run BackEnd:
> cd egl_evaluacion_back
> 
> mvn spring-boot:run

Run FrontEnd:
> cd egl_evaluacion_front
> 
> npm install
> 
> npm start
> 
# Validar
Abrir el navegador: http://localhost:3000

Agregar Productos (Importante Stock > 0)

Clic en Ventas -> Nueva venta -> Agregar productos y Clic en confirmar

Verificar registros en base de datos.