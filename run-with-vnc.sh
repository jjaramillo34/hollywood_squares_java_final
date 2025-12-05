#!/bin/bash

# Hollywood Squares - VNC Remote Desktop Launcher
# This script sets up a virtual display and VNC server for remote access

echo "=========================================="
echo "  Hollywood Squares - VNC Edition"
echo "=========================================="
echo ""

# Configuration
VNC_PORT=5900
DISPLAY_NUM=99
SCREEN_RESOLUTION="1280x800x24"

# Check if Xvfb is installed
if ! command -v Xvfb &> /dev/null; then
    echo "Error: Xvfb not installed. Installing..."
    apt-get update && apt-get install -y xvfb x11vnc fluxbox
fi

# Kill any existing Xvfb and VNC servers
echo "Cleaning up previous sessions..."
pkill -f Xvfb || true
pkill -f x11vnc || true
sleep 2

# Start virtual framebuffer (Xvfb)
echo "Starting virtual display :$DISPLAY_NUM..."
Xvfb :$DISPLAY_NUM -screen 0 $SCREEN_RESOLUTION -ac +extension GLX +render -noreset &
XVFB_PID=$!
sleep 2

# Export DISPLAY
export DISPLAY=:$DISPLAY_NUM

# Start window manager
echo "Starting window manager..."
fluxbox &
WM_PID=$!
sleep 1

# Start VNC server
echo "Starting VNC server on port $VNC_PORT..."
x11vnc -display :$DISPLAY_NUM -forever -shared -rfbport $VNC_PORT -nopw &
VNC_PID=$!
sleep 2

echo ""
echo "✅ VNC Server is running!"
echo ""
echo "📡 Connection Info:"
echo "   VNC Port: $VNC_PORT"
echo "   Display: :$DISPLAY_NUM"
echo ""
echo "🌐 To connect:"
echo "   1. Use a VNC client (like TigerVNC, RealVNC, or TightVNC)"
echo "   2. Connect to: localhost:$VNC_PORT"
echo "   3. Or use: localhost:${DISPLAY_NUM}"
echo ""
echo "🎮 Starting Hollywood Squares..."
echo ""

# Compile if needed
if [ ! -d "bin" ] || [ ! -f "bin/HollywoodSquares.class" ]; then
    echo "Compiling game..."
    javac -d bin -source 21 -target 21 src/*.java
fi

# Run the game
cd /app
java -cp bin HollywoodSquares &
GAME_PID=$!

echo ""
echo "✨ Game is running in VNC session!"
echo ""
echo "Press Ctrl+C to stop all servers..."
echo ""

# Wait for game to finish
wait $GAME_PID

# Cleanup
echo "Cleaning up..."
kill $VNC_PID 2>/dev/null
kill $WM_PID 2>/dev/null
kill $XVFB_PID 2>/dev/null

echo "Servers stopped."
