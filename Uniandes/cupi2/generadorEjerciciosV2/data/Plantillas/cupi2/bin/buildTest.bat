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
REM Asegura la creación de los directorios classes y lib en test
REM ---------------------------------------------------------

cd ../[[RUTA_TEST]]
mkdir [[RUTA_CLASSES]]
mkdir [[RUTA_LIB]]

REM ---------------------------------------------------------
REM Compila las clases del directotio test/source
REM ---------------------------------------------------------

cd [[RUTA_SOURCE]]

javac -classpath ../../[[RUTA_LIB]]/[[NOMBRE]].jar;../[[RUTA_LIB]]/junit.jar -d ../[[RUTA_CLASSES]]/ [[RUTA_PAQUETE]]/[[DIRECTORIO_PRUEBAS]]/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ../[[RUTA_CLASSES]]

jar cf ../[[RUTA_LIB]]/[[NOMBRE]]Test.jar uniandes/* -C ../[[RUTA_DATA]] .

cd ../../[[RUTA_BIN]]

pause