#!/bin/bash

# Hollywood Squares - Web Browser Edition (noVNC)
# Access the game through your web browser!

echo "=========================================="
echo "  Hollywood Squares - Web Browser Mode"
echo "=========================================="
echo ""

# Configuration
VNC_PORT=5900
WEB_PORT=6080
DISPLAY_NUM=99
SCREEN_RESOLUTION="1280x800x24"

# Check installations
if ! command -v Xvfb &> /dev/null; then
    echo "Installing display tools..."
    apt-get update && apt-get install -y xvfb x11vnc fluxbox novnc python3-websockify
fi

# Cleanup
echo "Cleaning up previous sessions..."
pkill -f Xvfb || true
pkill -f x11vnc || true
pkill -f websockify || true
sleep 2

# Start virtual display
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

# Start noVNC websockify
echo "Starting web server on port $WEB_PORT..."
websockify --web=/usr/share/novnc/ $WEB_PORT localhost:$VNC_PORT &
WEB_PID=$!
sleep 2

echo ""
echo "✅ Web server is running!"
echo ""
echo "🌐 OPEN IN YOUR BROWSER:"
echo "   http://localhost:$WEB_PORT/vnc.html"
echo ""
echo "   Or click: http://localhost:$WEB_PORT/vnc.html?autoconnect=true"
echo ""
echo "📱 To connect from another device:"
echo "   Find your IP: ip addr show | grep 'inet '"
echo "   Then use: http://YOUR_IP:$WEB_PORT/vnc.html"
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
java -cp "bin:lib/*" HollywoodSquares &
GAME_PID=$!

echo ""
echo "✨ Game is now accessible in your web browser!"
echo ""
echo "Press Ctrl+C to stop all servers..."
echo ""

# Trap to cleanup on exit
trap "echo 'Cleaning up...'; kill $GAME_PID $WEB_PID $VNC_PID $WM_PID $XVFB_PID 2>/dev/null; exit" INT TERM

# Keep script running
wait $GAME_PID

# Cleanup
echo "Cleaning up..."
kill $WEB_PID 2>/dev/null
kill $VNC_PID 2>/dev/null
kill $WM_PID 2>/dev/null
kill $XVFB_PID 2>/dev/null

echo "All servers stopped."
