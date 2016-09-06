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
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd ..
java -classpath ../[[RUTA_LIB]]/[[NOMBRE]].jar;../[[RUTA_TEST]]/[[RUTA_LIB]]/[[NOMBRE]]Test.jar;../[[RUTA_TEST]]/[[RUTA_LIB]]/junit.jar junit.swingui.TestRunner [[PAQUETE]].[[DIRECTORIO_PRUEBAS]].[[CLASE_PRUEBAS]]