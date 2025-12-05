#!/bin/bash

# Hollywood Squares - Headless Mode (Virtual Display Only)
# Runs the game in a virtual display without VNC

echo "=========================================="
echo "  Hollywood Squares - Headless Mode"
echo "=========================================="
echo ""

# Configuration
DISPLAY_NUM=99
SCREEN_RESOLUTION="1280x800x24"

# Check if Xvfb is installed
if ! command -v Xvfb &> /dev/null; then
    echo "Installing Xvfb..."
    apt-get update && apt-get install -y xvfb
fi

# Kill any existing Xvfb
echo "Cleaning up previous sessions..."
pkill -f Xvfb || true
sleep 1

# Start virtual framebuffer (Xvfb)
echo "Starting virtual display :$DISPLAY_NUM..."
Xvfb :$DISPLAY_NUM -screen 0 $SCREEN_RESOLUTION -ac +extension GLX +render -noreset &
XVFB_PID=$!
sleep 2

# Export DISPLAY
export DISPLAY=:$DISPLAY_NUM

echo "✅ Virtual display ready!"
echo ""

# Compile if needed
if [ ! -d "bin" ] || [ ! -f "bin/HollywoodSquares.class" ]; then
    echo "Compiling game..."
    javac -d bin -source 21 -target 21 src/*.java
fi

# Run the game
echo "🎮 Starting Hollywood Squares in headless mode..."
echo "   (Game UI will run in virtual display)"
echo ""
cd /app
java -cp "bin:lib/*" HollywoodSquares

# Cleanup
echo ""
echo "Cleaning up..."
kill $XVFB_PID 2>/dev/null
echo "Game session ended."
