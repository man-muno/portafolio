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
REM Asegura la creación de los directorios classes y lib
REM ---------------------------------------------------------

cd ..
mkdir [[RUTA_CLASSES]]
mkdir [[RUTA_LIB]]

REM ---------------------------------------------------------
REM Compila las clases del directorio source
REM ---------------------------------------------------------
cd [[RUTA_SOURCE]]
javac -source 1.4 -nowarn -d ../[[RUTA_CLASSES]]/ [[RUTA_PAQUETE]]/[[DIRECTORIO_MUNDO]]/*.java
javac -source 1.4 -nowarn -d ../[[RUTA_CLASSES]]/ [[RUTA_PAQUETE]]/[[DIRECTORIO_INTERFAZ]]/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ..
cd [[RUTA_CLASSES]]
jar cf ../[[RUTA_LIB]]/[[NOMBRE]].jar uniandes/*

cd ../[[RUTA_BIN]]

pause
