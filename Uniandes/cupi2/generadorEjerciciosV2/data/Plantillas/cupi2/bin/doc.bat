@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: [[NOMBRE_COMPLETO]]
REM Autor: [[AUTOR]] - [[FECHA]]
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Asegura la creación del directorio docs/api
REM ---------------------------------------------------------

cd ..\[[RUTA_DOCS]]
mkdir [[RUTA_API]]
cd ..\[[RUTA_BIN]]

REM ---------------------------------------------------------
REM Genera la documentación
REM ---------------------------------------------------------

javadoc -sourcepath ../[[RUTA_SOURCE]] -d ../[[RUTA_DOCS]]/[[RUTA_API]] -subpackages [[PAQUETE]]