# RadioBeats Station Manager

Simulación de manager para la creación, edición y eliminación de emisoras
en una estacion de radio, capaz de crear plias de reproduccion de musica y
programaciones para las emisoras.

## Librerias Utilizadas

- `Lombok` Annotation Proccesor para la eliminación de boilerplate code.
- `JLayer` Libreria para codificar/reproducir/convertir ficheros MP3.
- `Jackson` API/Data Binder para serialización y deserialización de datos
en diferentes formatos de intercambio de datos.

## Testing Frameworks Utilizados

- `JUnit` Framework de unit testing para Java.
- `AssertJ` Framework de aserciones ricas y fluidas para testing en Java.

## Como Ejecutar

En primer lugar hay que clonar el presente repositorio:

```
git clone https://github.com/MrFixThis/RadioBeats.git
cd RadioBeats
```
### Construir desde Source Code

Para construir la aplicación, se puede hacer uso del Gradle wrapper que posee
el proyecto (Linux | MacOS 'gradlew'; Windows 'gradlew.bat').

Una vez en el directorio root del proyecto, ejecutar el comando:

#### Linux | MacOS

```bash
> ./gradlew build      # para construir > genera un artefacto (jar file) en ./build/libs/
```

#### Windows

```Powershell
> .\gradlew.bat build    # para construir > genera un artefacto (jar file) en ./build/libs/
```

### Ejecutar sin Construir

Para ejecutar la aplicación sin construir, una vez en el directorio root del proyecto, ejecutar:

#### Linux | MacOS

```bash
> ./gradlew run     # para ejecutar sin construir
```

#### Windows

```Powershell
> .\gradlew.bat run     # para ejecutar sin construir
```

## Autores

- Bryan Sneyder Baron Murcia ([@MrFixThis](https://github.com/MrFixThis))
- Juan David Pérez Ávila
- Juan Felipe Tarazona Gonzalez
- Juan Sebastian Carrera Lozano
