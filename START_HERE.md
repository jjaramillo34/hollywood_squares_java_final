# 🎮 Hollywood Squares - Start Here!

## 🚀 Quickest Way to Play Online

### Option 1: Web Browser (EASIEST!) 🌐

```bash
cd /app
./run-web-browser.sh
```

Then open in your browser:
```
http://localhost:6080/vnc.html?autoconnect=true
```

**✨ This is the easiest way!** No additional software needed - just your web browser!

---

### Option 2: VNC Client (BEST PERFORMANCE) 🖥️

```bash
cd /app
./run-with-vnc.sh
```

Then connect with a VNC client:
- Download [TigerVNC](https://tigervnc.org/)
- Connect to: `localhost:5900`

---

### Option 3: Headless (TESTING ONLY) 🧪

```bash
cd /app
./run-headless.sh
```

This runs the game in a virtual display (you won't see it, but it works for testing).

---

## 📋 What's Available

### Execution Scripts (Choose One):
1. **`./run-web-browser.sh`** ⭐ - Play in web browser (recommended!)
2. **`./run-with-vnc.sh`** - Play with VNC client
3. **`./run-headless.sh`** - Testing/automation
4. **`./run.sh`** - Direct execution (needs display)

### Documentation:
- **`README.md`** - Complete game documentation
- **`ONLINE_EXECUTION_GUIDE.md`** - Detailed online setup guide
- **`MODERNIZATION_REPORT.md`** - Java 21 upgrade details
- **`JAVA21_FEATURES.md`** - All modern features explained
- **`QUICK_START.md`** - 30-second quick start

---

## 🎯 Recommended: Web Browser Method

This is the **easiest and fastest** way to play:

### Step 1: Start the server
```bash
./run-web-browser.sh
```

### Step 2: Open your browser
Go to:
```
http://localhost:6080/vnc.html?autoconnect=true
```

### Step 3: Play!
- Click "Connect" if it doesn't auto-connect
- You'll see the Hollywood Squares game
- Play using your mouse and keyboard through the browser!

---

## 💡 Why These Methods?

**The Problem:** Java Swing GUI apps need a graphical display. In cloud/container environments, there's no physical screen.

**The Solution:** We use:
- **Xvfb** - Virtual framebuffer (fake display)
- **x11vnc** - VNC server to access the virtual display
- **noVNC** - Web-based VNC client (no software install needed!)

---

## 🔍 Quick Test

Want to verify everything works? Run:

```bash
# Check Java 21
java -version

# Check if compiled
ls -la bin/*.class

# Check display tools
which Xvfb x11vnc websockify

# Test compilation
./compile.sh
```

---

## 📞 Need Help?

See these guides:
- `ONLINE_EXECUTION_GUIDE.md` - Complete setup instructions
- `README.md` - Game documentation
- `QUICK_START.md` - Quick start guide

---

## 🎉 Ready to Play!

**Easiest method:**
```bash
./run-web-browser.sh
```

Then open: http://localhost:6080/vnc.html?autoconnect=true

**Have fun playing Hollywood Squares with Java 21!** ☕🎮

```
┌──────────────────────────────────────┐
│  🎮 Hollywood Squares - Java 21      │
│  🌐 Play in Your Browser!            │
│  ✨ Modern • Fast • Fun              │
└──────────────────────────────────────┘
```
