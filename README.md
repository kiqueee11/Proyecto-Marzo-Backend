
# Flash Meet Backend (Springboot)

Flash Meet es una aplicación para conocer gente de forma novedosa, basada en el anonimato. El backend está implementado utilizando microservicios en Spring.

## Microservicios

- **Autenticación**
- **Chat**
- **Gateway**
- **Mensajes**
- **Usuarios**
- **Servidor WebSocket**
- **Servidor de Descubrimiento (Eureka)**
- **Ajustes**
- **Videollamadas**

## Endpoints Implementados
Excepto las de el microservicio de autenticacion, todas las rutas necesitan el Authorization Bearer Token

### Autenticación

- **Crear usuario:**
  ```
  POST http://localhost:8089/auth/auth/signup
  ```

  **Parámetros:**
  - `nombre` (string)
  - `clave` (string)
  - `email` (string)
  - `image1` a `image6` (string): Enlaces o rutas de las imágenes del perfil del usuario (ej. fotos de perfil).
  - `sexo` (string): Género del usuario, ejemplo: `HOMBRE`, `MUJER`.
  - `posicion` (string): Ubicación geográfica del usuario en formato `latitud,longitud` (ej. `10.5,20.7`).
  - `fechaNacimiento` (string): Fecha de nacimiento en formato `yyyy-MM-dd'T'HH:mm:ss.SSS'Z'` (ISO 8601).
  - `descripcion` (string): Descripción breve del usuario.
  - `distancia` (int): Distancia en metros que define el alcance de la búsqueda de otros usuarios cercanos (ej. `12000` metros = 12 km).

- **Cerrar sesión:**
  - No implementado.

### Chat

- **Crear chat:**
  ```
  POST http://localhost:8089/chat/chats/create
  ```

  **Parámetros:**
  - `user1_id` (string)
  - `user2_id` (string)

- **Eliminar chat:**
  ```
  POST http://localhost:8089/chat/chats/delete-chat
  ```

  **Parámetros:**
  - `chatId` (string)

- **Revelar identidad:**
  ```
  POST http://localhost:8089/chat/chats/reveal-identity
  ```

  **Parámetros:**
  - `chatId` (string)

### Mensajes

- **Enviar mensaje:**
  ```
  POST http://localhost:8089/messages/messages/send/
  ```

  **Body de la solicitud:**
  ```json
  {
    "messageContent": "Y asi es como se hace, al final despues de tanto podemos decir que el realtime por lo menos lo basico ya esta implementado. ahora acabo de implementar la security rule para que solo un usuario autorizado pueda enviar mensaje en un chat",
    "senderId": "id_del_remitente",
    "chatId": "23",
    "isMessageRead": false,
    "messageUID": "esta es mi casitaa"
  }
  ```

- **Obtener todos los mensajes:**
  ```
  POST http://localhost:8089/messages/messages/get-all-messages
  ```

  **Parámetros:**
  - `chatId` (string)

### Usuarios (Profile Manager)

- **Crear usuario:**
  ```
  POST http://localhost:8089/users/users/internal/create-user
  ```

  **Parámetros:**
  - `uidString` (string): Identificador único creado en el microservicio de autenticación, que servirá como identificador de todos los recursos relacionados con el usuario.
  - `email` (string)
  - `image1` a `image6` (string): Enlaces o rutas de las imágenes del perfil del usuario.
  - `sexo` (string): Género del usuario (ej. `HOMBRE`, `MUJER`).
  - `posicion` (string): Ubicación geográfica del usuario en formato `latitud,longitud` (ej. `10.5,20.7`).
  - `fechaNacimiento` (string): Fecha de nacimiento en formato `yyyy-MM-dd'T'HH:mm:ss.SSS'Z'` (ISO 8601).
  - `descripcion` (string): Descripción breve del usuario.
  - `distancia` (int): Distancia en metros para la búsqueda de usuarios cercanos (ej. `12000` metros = 12 km).

- **Buscar usuario por email:**
  ```
  POST http://localhost:8089/users/users/internal/get-user-by-email
  ```

  **Parámetros:**
  - `email` (string)

- **Buscar usuario por id:**
  ```
  POST http://localhost:8089/users/users/internal/get-user-by-id
  ```

  **Parámetros:**
  - `id` (string)

- **Buscar usuario por posición:**
  ```
  POST http://localhost:8089/users/users/buscarusuarioporposicion
  ```

  **Parámetros:**
  - `posicion` (string): Ej. `10.5,20.7`
  - `distancia` (int): Distancia en metros.

### Servidor WebSocket

- **Suscribirse:**
  ```
  ws://localhost:8089/realtime/realtime
  ```

---

**Servicios en desarrollo:**
- **Cerrar sesión (Autenticación)**
-   **Videollamadas** y **Settings**.



NOTA: todos los microservicios estan detras del Gateway (implementado con Spring Gateway) y este es el encargado de autorizar el paso a las demas rutas, dejando libres al inicio de sesion y a la creacion de usuarios.
Todas las rutas con "internal" son llamadas desde los microservicios entre si, por eso usan datos simples o incluso primitivos de java, en el resto estamos implementando DTO para una mejor respuesta al fron-end
