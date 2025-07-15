
# 📧 Email Sender Service (Spring Boot)

Este proyecto es un microservicio desarrollado con **Spring Boot** que permite enviar correos electrónicos de forma sencilla, tanto simples como con archivos adjuntos.

---

## ✅ Funcionalidades

- Enviar correos electrónicos simples (texto).
- Enviar correos electrónicos con archivos adjuntos.
- Guardado temporal del archivo recibido en el servidor.
- Configuración mediante `application.properties`.

---

## 🛠️ Tecnologías usadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Mail (JavaMailSender)
- Lombok (opcional)
- Maven

---

## 📦 Estructura del proyecto

```
src/
├── main/
│   ├── java/
│   │   └── .../
│   │       ├── controller/
│   │       │   └── MailController.java
│   │       ├── service/
│   │       │   ├── IEmailService.java
│   │       │   └── EmailServiceImpl.java
│   │       └── config/
│   │           └── MailConfiguration.java
│   └── resources/
│       └── application.properties
```

---

## 🧪 Endpoints

### 1. Enviar email simple

**POST** `/v1/sendMessage`

**Request body (JSON):**
```json
{
  "toUser": ["correo@ejemplo.com"],
  "subject": "Asunto del correo",
  "message": "Contenido del mensaje"
}
```

### 2. Enviar email con archivo adjunto

**POST** `/v1/sendMessageWithFile`

**Request type:** `multipart/form-data`

**Campos esperados:**
- `toUser`: correos destinatarios (array de string)
- `subject`: asunto del correo
- `message`: cuerpo del mensaje
- `file`: archivo a adjuntar

---

## ⚙️ Configuración

Agregá tus datos en `src/main/resources/application.properties`:

```properties
# Correo del remitente
email.sender=xxxxx@gmail.com

# Configuración SMTP
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=xxxxx@gmail.com
spring.mail.password=<tu-contraseña-de-aplicacion>
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.transport.protocol=smtp
spring.mail.properties.mail.debug=true
```

⚠️ **Importante:** Usá una [contraseña de aplicación](https://support.google.com/accounts/answer/185833?hl=es) si usás Gmail con verificación en dos pasos.

---

## ▶️ Cómo ejecutar

1. Cloná el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/email-sender-service.git
   cd email-sender-service
   ```

2. Configurá el archivo `application.properties`.

3. Ejecutá el proyecto:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 📩 Contacto

Para consultas o colaboración, podés escribirme a:  
**zubigarayjuansimon@gmail.com**