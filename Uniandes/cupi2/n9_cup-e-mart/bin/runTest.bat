@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n9_cupEMart
REM Autor: Manuel Muñoz - 19-Oct-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd..
java -classpath ./lib/cupEMart.jar;./test/lib/cupEMartTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupEMart.test.CupEMartTest
java -classpath ./lib/cupEMart.jar;./test/lib/cupEMartTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupEMart.test.LineaProductoTest
java -classpath ./lib/cupEMart.jar;./test/lib/cupEMartTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupEMart.test.SucursalTest
cd bin