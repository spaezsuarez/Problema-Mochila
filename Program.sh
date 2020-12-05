# !/bin/bash
# Programa para revisar como ejecutar comandos dentro de un programa y almacenarlos en una variable
# Autor: Sergio David Peaz Suarez - spaezsuarez@gmail.com
cd src
javac Main.java -d build
cd build
java Main
