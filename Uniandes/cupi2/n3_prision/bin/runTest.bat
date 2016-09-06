@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n3_prision
REM Autor: Manuel Mu�oz - 04-Sep-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -classpath ../lib/prision.jar;../test/lib/prisionTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.prision.test.PrisionTest
java -classpath ../lib/prision.jar;../test/lib/prisionTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.prision.test.SectorTest
java -classpath ../lib/prision.jar;../test/lib/prisionTest.jar;../test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.prision.test.PrisioneroTest