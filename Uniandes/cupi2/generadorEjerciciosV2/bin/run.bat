@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Todos los derechos reservados 2005
REM
REM Proyecto Cupi2
REM Ejercicio: Generador de Ejercicios
REM Autor: Pablo Barvo - 05-Sep-2005
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

REM ---------------------------------------------------------
REM Ejecucion del programa
REM ---------------------------------------------------------

cd..
java -classpath ./lib/generador.jar;./lib\jcalendar.jar;./lib\looks-1.2.2.jar uniandes.cupi2.generador.interfaz.InterfazGenerador
cd bin