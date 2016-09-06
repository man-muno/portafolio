@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n9_cupEMart
REM Autor: Manuel Mu�oz - 19-Oct-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Asegura la creaci�n de los directorios classes y lib
REM ---------------------------------------------------------

cd ..
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM Compila las clases del directorio source
REM ---------------------------------------------------------
cd source
javac -nowarn -d ../classes/ uniandes/cupi2/cupEMart/mundo/*.java
javac -nowarn -classpath ../lib/jcalendar.jar;../classes/ -d ../classes/ uniandes/cupi2/cupEMart/interfaz/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ..
cd classes
jar cf ../lib/cupEMart.jar uniandes/*

cd ..\bin

pause
