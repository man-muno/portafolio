@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n6_sudoku
REM Autor: Manuel Mu�oz - 07-Sep-2006
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd..

java -classpath ./lib/sudoku.jar;./test/lib/sudokuTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sudoku.test.SudokuTest
java -classpath ./lib/sudoku.jar;./test/lib/sudokuTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.sudoku.test.CasillaTest

cd bin