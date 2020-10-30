# !/bin/bash
# Programa para revisar como ejecutar comandos dentro de un programa y almacenarlos en una variable
# Autor: Sergio David Peaz Suarez - spaezsuarez@gmail.com
echo "$(cd src)"
echo -e "\n $(pwd)"
compilar=$(javac Main.java -d bin)
cambiar_ubicacion=$(cd bin)
ejecutar=$(java Main)

echo "$ubicacionActual"
echo -e "\n$cambiar_ubicacion"
echo -e "\n$ejecutar "