# Hollywood Squares - Quick Start Guide

## 🎮 Run the Game in 30 Seconds

### Step 1: Verify Java 21
```bash
java -version
```
Expected output: `java version "21.0.9"` or higher

### Step 2: Run the Game
```bash
cd /app
./run.sh
```

That's it! The game will compile (if needed) and launch automatically.

---

## 🎯 Game Controls

### Setup Phase
1. **Select Difficulty**: Type `E` (Easy), `M` (Medium), or `H` (Hard)
2. **Choose Players**: Select "One Player" or "Two Players"
3. **Enter Name(s)**: Type your name (and opponent's if 2-player)

### Playing Phase
1. **Select Celebrity**: Choose from dropdown (Tom Cruise, Mariah Carey, etc.)
2. **Read Question**: Question appears in the Question field
3. **Read Answer**: Celebrity's answer appears in the Answer field
4. **Make Decision**: Click "Agree" or "Disagree"
5. **Watch Board**: Your X or O appears if you're correct

### Winning
- Get 3 in a row: 250 points
- Have most squares when board fills: 50 × squares
- First to 500 points wins the game!

---

## 🛠️ Alternative Run Methods

### Method 1: Run Script (Recommended)
```bash
./run.sh
```

### Method 2: Manual Compile + Run
```bash
javac -d bin -source 21 -target 21 src/*.java
java -cp bin HollywoodSquares
```

### Method 3: Compile Script + Run
```bash
./compile.sh
./run.sh
```

---

## 🐛 Troubleshooting

### "Java not found"
Install Java 21:
- **macOS**: `brew install openjdk@21`
- **Ubuntu**: `sudo apt install openjdk-21-jdk`
- **Windows**: Download from oracle.com

### "Images not showing"
Ensure you're in the /app directory:
```bash
cd /app
./run.sh
```

### "Compilation failed"
Clean and rebuild:
```bash
rm -rf bin
./compile.sh
```

---

## 📚 More Information

- **Full Documentation**: See [README.md](README.md)
- **Modernization Details**: See [MODERNIZATION_REPORT.md](MODERNIZATION_REPORT.md)
- **Java 21 Features**: See [JAVA21_FEATURES.md](JAVA21_FEATURES.md)

---

## 🎉 Have Fun!

Enjoy playing Hollywood Squares with modern Java 21 technology!

```
  🎮 Hollywood Squares
  ✨ Java 21 Edition
  🚀 Ready to Play!
```
