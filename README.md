# 📚 Sistema de Gestión de Biblioteca

## 📌 Descripción del Proyecto
El **Sistema de Gestión de Biblioteca** es una aplicación desarrollada en **Java con NetBeans y Ant**, que permite la administración eficiente de una biblioteca. Ofrece funcionalidades como la gestión de usuarios, libros, préstamos y reportes, con diferentes niveles de acceso según el tipo de usuario.

Este sistema está diseñado para facilitar el manejo de una biblioteca de manera estructurada, garantizando la seguridad de los datos y la facilidad de uso para bibliotecarios y lectores.

📌 **Desarrollado por**: *Kevin Salinas Oroncuy* - **2DAM**
📌 **Proyecto Final de Acceso a Datos**

## 🚀 Características Principales
- **Inicio de sesión seguro** con credenciales y niveles de acceso.
- **Gestión de Usuarios**:
  - Creación, edición y eliminación de usuarios.
  - Roles definidos: Administrador, Empleado y Usuario Regular.
- **Gestión de Libros**:
  - Registro, edición y eliminación de libros.
  - Control de disponibilidad y estado de libros.
- **Sistema de Préstamos y Devoluciones**:
  - Registro de préstamos con fechas de vencimiento.
  - Control de devoluciones y retrasos.
- **Generación de Reportes**:
  - Libros más prestados.
  - Usuarios con más préstamos.
  - Estado general de la biblioteca.
- **Panel de control dinámico**, donde los accesos dependen del rol del usuario.
- **Interfaz gráfica intuitiva con Swing** para una navegación sencilla.
- **Gestión de permisos avanzada**, asegurando que cada usuario solo pueda acceder a las funciones que le correspondan.
- **Base de datos en MySQL** con conexiones seguras mediante JDBC.
- **Cifrado de contraseñas con SHA-256** para mayor seguridad.
- **Registro de actividades en la biblioteca** para auditoría y control.

## 🛠️ Tecnologías Utilizadas
- **Lenguaje**: Java
- **IDE**: NetBeans con Ant
- **Base de Datos**: MySQL
- **Interfaz Gráfica**: Swing
- **Manejo de Sesión**: Clase estática `Sesion`
- **Encriptación de Contraseñas**: SHA-256
- **Gestión de Eventos** con `ActionListener`
- **Conexión a BD con JDBC**

## 🔑 Roles y Permisos
| Rol               | Gestión de Usuarios | Gestión de Libros | Préstamos | Reportes | Libros Disponibles |
|------------------|--------------------|------------------|-----------|----------|-------------------|
| **Administrador** | ✅ Sí               | ✅ Sí             | ✅ Sí      | ✅ Sí     | ✅ Sí              |
| **Empleado**     | ❌ No               | ✅ Sí             | ✅ Sí      | ❌ No     | ✅ Sí              |
| **Usuario Regular** | ❌ No               | ❌ No             | ❌ No      | ❌ No     | ✅ Sí              |

## 📂 Estructura del Proyecto
```
SistemaBiblioteca/
│── src/
│   ├── gui/                # Interfaces gráficas (Login, Dashboard, Gestión de Libros, etc.)
│   ├── dao/                # Acceso a base de datos (UsuarioDAO, LibroDAO, etc.)
│   ├── models/             # Clases de datos (Usuario, Libro, Prestamo, etc.)
│   ├── services/           # Lógica de negocio (UsuarioService, LibroService, etc.)
│   ├── utils/              # Utilidades (Conexion a BD, Gestión de sesión, etc.)
│── config/
│   ├── config.properties   # Configuración de la conexión a la base de datos
│── lib/                    # Librerías externas necesarias
│── README.md               # Documentación del proyecto
```

## 📌 Instalación y Ejecución
### 🔹 Requisitos Previos
- Tener instalado **Java (JDK 8 o superior)**
- Tener instalado **MySQL Server**
- Tener **NetBeans con soporte para Ant**
- Agregar las dependencias necesarias en `lib/`

### 🔹 Pasos de Instalación
1. **Clonar el repositorio** o descargar el código fuente.
2. **Configurar la base de datos**:
   - Crear la base de datos en MySQL.
   - Importar el archivo `biblioteca_db.sql`.
   - Configurar los parámetros en `config/config.properties`.
3. **Abrir el proyecto en NetBeans**.
4. **Compilar y ejecutar el sistema**.
5. **Iniciar sesión con un usuario registrado en la base de datos**.

## 📜 Licencia
Este proyecto es de código abierto y puede ser utilizado libremente para fines educativos y académicos. 📖✨


