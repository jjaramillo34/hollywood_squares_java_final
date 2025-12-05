#!/bin/bash

# Compile script for Hollywood Squares Java 21

echo "Compiling Hollywood Squares with Java 21..."
echo ""

# Clean previous build
rm -rf bin
mkdir -p bin

# Compile all source files
javac -cp "lib/*" -d bin -source 21 -target 21 src/*.java

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Compilation successful!"
    echo ""
    echo "Compiled classes:"
    ls -lh bin/*.class
    echo ""
    echo "To run the game, execute: ./run.sh"
else
    echo ""
    echo "❌ Compilation failed!"
    exit 1
fi
