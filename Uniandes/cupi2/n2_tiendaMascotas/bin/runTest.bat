@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n2_tiendaMascotas
REM Autor: Manuel Mu�oz - 08-Feb-2007
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -classpath ../lib/tiendaMascotas.jar;../test/lib/tiendaMascotasTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.tiendaMascotas.test.TiendaMascotasTest
java -classpath ../lib/tiendaMascotas.jar;../test/lib/tiendaMascotasTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.tiendaMascotas.test.MascotaTest