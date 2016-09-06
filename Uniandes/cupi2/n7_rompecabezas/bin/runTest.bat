@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n7_rompecabezas
REM Autor: Manuel Mu�oz - 02-Oct-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -classpath ../lib/rompecabezas.jar;../test/lib/rompecabezasTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.rompecabezas.test.JuegoRompecabezasTest
java -classpath ../lib/rompecabezas.jar;../test/lib/rompecabezasTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.rompecabezas.test.FiguraTest
java -classpath ../lib/rompecabezas.jar;../test/lib/rompecabezasTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.rompecabezas.test.FichaTest