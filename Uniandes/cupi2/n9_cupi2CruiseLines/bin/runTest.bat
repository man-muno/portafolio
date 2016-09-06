@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n9_cupi2CruiseLines
REM Autor: Manuel Mu�oz - 13-Mar-2007
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd..
java -classpath lib/cupi2CruiseLines.jar;test/lib/cupi2CruiseLinesTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupi2CruiseLines.test.AgenciaTest
java -classpath lib/cupi2CruiseLines.jar;test/lib/cupi2CruiseLinesTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupi2CruiseLines.test.CruceroTest
java -classpath lib/cupi2CruiseLines.jar;test/lib/cupi2CruiseLinesTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupi2CruiseLines.test.CiudadTest
cd bin