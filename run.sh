#!/bin/bash

# Hollywood Squares - Java 21 Game Launcher

echo "========================================"
echo "  Hollywood Squares - Java 21 Edition"
echo "========================================"
echo ""

# Check if Java 21 is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java not found. Please install Java 21 or later."
    exit 1
fi

# Display Java version
echo "Java Version:"
java -version 2>&1 | head -1
echo ""

# Check if compiled classes exist
if [ ! -d "bin" ] || [ ! -f "bin/HollywoodSquares.class" ]; then
    echo "Compiled classes not found. Compiling..."
    javac -d bin -source 21 -target 21 src/*.java
    
    if [ $? -ne 0 ]; then
        echo "Compilation failed!"
        exit 1
    fi
    echo "Compilation successful!"
    echo ""
fi

# Run the game
echo "Starting Hollywood Squares..."
echo ""
cd /app
java -cp "bin:lib/*" HollywoodSquares
