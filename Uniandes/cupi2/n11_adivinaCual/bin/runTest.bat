@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n11_adivinaCual
REM Autor: Manuel Muñoz - 27-Oct-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ..
java -classpath lib/adivinaCual.jar;test/lib/adivinaCualTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.adivinaCual.test.PreguntaTest
java -classpath lib/adivinaCual.jar;test/lib/adivinaCualTest.jar;test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.adivinaCual.test.JuegoTest

cd bin