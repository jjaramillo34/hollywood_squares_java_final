# Hollywood Squares - Online Execution Guide 🌐

This guide explains how to run the Java Swing GUI game in various online/remote environments.

---

## 🎯 The Challenge

Hollywood Squares is a **Java Swing GUI application** that requires a graphical display. In cloud/container environments (like this one), there's no physical display, so we need virtual solutions.

---

## ✅ Solution 1: VNC Server (Recommended for Remote Play)

### What You Need:
- VNC server (x11vnc) - ✅ Installed
- Virtual display (Xvfb) - ✅ Installed  
- VNC client (on your local machine)

### How to Run:

**Step 1: Start VNC Server + Game**
```bash
cd /app
./run-with-vnc.sh
```

**Step 2: Connect with VNC Client**

Install a VNC client on your computer:
- **Windows**: [TigerVNC](https://tigervnc.org/) or [RealVNC](https://www.realvnc.com/)
- **macOS**: [TigerVNC](https://tigervnc.org/) or built-in Screen Sharing
- **Linux**: `sudo apt install tigervnc-viewer` or `vncviewer`

**Step 3: Connect**
```
Address: localhost:5900
Or: localhost:99
```

### Pros:
✅ Full GUI interaction
✅ Play remotely from anywhere
✅ Multiple users can watch
✅ No code changes needed

---

## ✅ Solution 2: Headless Mode (Virtual Display)

### What You Need:
- Virtual display (Xvfb) - ✅ Installed

### How to Run:
```bash
cd /app
./run-headless.sh
```

### Use Case:
- Testing GUI without viewing
- Automated testing
- CI/CD pipelines
- Screenshot generation

### Pros:
✅ Lightweight (no VNC overhead)
✅ Fast execution
✅ Good for testing

---

## ✅ Solution 3: X11 Forwarding (SSH with Display)

### What You Need:
- SSH access with X11 forwarding
- X Server on local machine

### Setup:

**On Linux/macOS:**
```bash
ssh -X user@server
cd /app
./run.sh
```

**On Windows:**
1. Install [VcXsrv](https://sourceforge.net/projects/vcxsrv/) or [Xming](https://sourceforge.net/projects/xming/)
2. Use PuTTY with X11 forwarding enabled
3. Run the game

### Pros:
✅ Direct display to local machine
✅ Native performance
✅ Secure (SSH encrypted)

---

## ✅ Solution 4: Web-Based (Future Enhancement)

### Convert to Web Application:

To make it truly web-accessible, you could:

**Option A: Use noVNC (Web-based VNC)**
```bash
# Install noVNC
apt-get install novnc

# Start with websocket
websockify --web=/usr/share/novnc/ 6080 localhost:5900

# Access in browser
# http://localhost:6080/vnc.html
```

**Option B: Convert to Web App**
- Rewrite frontend with HTML5 Canvas
- Keep Java backend as REST API
- Use WebSocket for real-time updates

---

## 🔧 Quick Setup Scripts

I've created these scripts for you:

### 1. `run-with-vnc.sh` - Full VNC Setup
- Starts virtual display
- Starts VNC server on port 5900
- Launches the game
- Access via VNC client

### 2. `run-headless.sh` - Virtual Display Only  
- Runs game in virtual display
- No VNC (lighter weight)
- Good for testing

### 3. `run.sh` - Standard Execution
- Requires display (DISPLAY env var)
- Use with X11 forwarding

---

## 📊 Comparison

| Method | Setup Difficulty | Performance | Remote Access | Best For |
|--------|------------------|-------------|---------------|----------|
| **VNC** | Easy | Good | ✅ Yes | Remote playing |
| **Headless** | Easy | Excellent | ❌ No | Testing |
| **X11 Forward** | Medium | Excellent | ✅ Yes | Direct display |
| **noVNC** | Medium | Good | ✅ Yes (Web) | Browser access |
| **Web App** | Hard | Excellent | ✅ Yes | Production |

---

## 🚀 Recommended Approach

### For Playing Now:
```bash
# Start VNC server
./run-with-vnc.sh

# Then connect with VNC client to localhost:5900
```

### For Testing:
```bash
# Just run in headless mode
./run-headless.sh
```

---

## 🔍 Verifying Installation

```bash
# Check if display tools are installed
which Xvfb       # Virtual framebuffer
which x11vnc     # VNC server
which fluxbox    # Window manager

# Check Java
java -version    # Should show Java 21.0.9
```

---

## 🐛 Troubleshooting

### "Cannot connect to VNC"
1. Check if VNC server is running: `ps aux | grep x11vnc`
2. Check port: `netstat -tuln | grep 5900`
3. Try different port: Edit `run-with-vnc.sh` and change VNC_PORT

### "Display not found"
1. Ensure Xvfb is running: `ps aux | grep Xvfb`
2. Check DISPLAY variable: `echo $DISPLAY`
3. Restart virtual display: `pkill Xvfb && ./run-headless.sh`

### "Game doesn't start"
1. Verify compilation: `ls -la bin/*.class`
2. Check Java: `java -version`
3. View logs: Check terminal output for errors

---

## 📚 Additional Resources

### VNC Clients
- **TigerVNC**: https://tigervnc.org/
- **RealVNC**: https://www.realvnc.com/
- **TightVNC**: https://www.tightvnc.com/

### X11 Forwarding
- **VcXsrv** (Windows): https://sourceforge.net/projects/vcxsrv/
- **XQuartz** (macOS): https://www.xquartz.org/

### Documentation
- Xvfb: `man Xvfb`
- x11vnc: `man x11vnc`

---

## 🎮 Ready to Play Online!

Choose your preferred method and enjoy Hollywood Squares remotely!

```
┌──────────────────────────────────────┐
│  🌐 Hollywood Squares Online Ready   │
│  Choose: VNC | X11 | Headless        │
│  🎯 Let's Play!                      │
└──────────────────────────────────────┘
```
