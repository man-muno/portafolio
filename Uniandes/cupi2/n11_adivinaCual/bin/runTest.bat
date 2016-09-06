@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n11_adivinaCual
REM Autor: Manuel Mu�oz - 27-Oct-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ..
java -classpath lib/adivinaCual.jar;test/lib/adivinaCualTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.adivinaCual.test.PreguntaTest
java -classpath lib/adivinaCual.jar;test/lib/adivinaCualTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.adivinaCual.test.JuegoTest

cd bin