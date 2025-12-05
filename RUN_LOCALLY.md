# 🏠 Run Hollywood Squares on Your Local Computer

You have **Java 25 LTS** on your local machine - perfect! Your game will run beautifully.

---

## ✅ What You Need

Your local setup:
- ✅ Java 25 LTS (installed)
- ⬇️ Game files (download from this container)

---

## 📦 Step 1: Download Game Files

Download these files/folders from the container to your local computer:

```
/app/src/           → All Java source files
/app/lib/           → JavaFX and other libraries
/app/bin/           → Compiled classes (or recompile locally)
/app/*.jpg          → Celebrity images and X/O markers
/app/*.JPG          → Additional images
```

**Easy way to package everything:**

```bash
# In the container, create a zip
cd /app
zip -r hollywood-squares.zip src/ lib/ bin/ *.jpg *.JPG *.md

# This creates hollywood-squares.zip you can download
```

---

## 🚀 Step 2: Run on Your Local Computer

### Method 1: Using Compiled Classes

If you downloaded the `bin/` folder:

```bash
# Windows
cd path\to\hollywood-squares
java -cp "bin;lib\*" --module-path lib --add-modules javafx.controls,javafx.fxml HollywoodSquares

# macOS/Linux  
cd path/to/hollywood-squares
java -cp "bin:lib/*" --module-path lib --add-modules javafx.controls,javafx.fxml HollywoodSquares
```

### Method 2: Recompile Locally (Recommended)

Since you have Java 25:

```bash
# Windows
cd path\to\hollywood-squares
javac --module-path lib --add-modules javafx.controls,javafx.fxml -cp "lib\*" -d bin -source 21 -target 21 src\*.java
java -cp "bin;lib\*" --module-path lib --add-modules javafx.controls,javafx.fxml HollywoodSquares

# macOS/Linux
cd path/to/hollywood-squares
javac --module-path lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d bin -source 21 -target 21 src/*.java
java -cp "bin:lib/*" --module-path lib --add-modules javafx.controls,javafx.fxml HollywoodSquares
```

---

## 🎮 Step 3: Play!

The game window will open directly on your local computer - no VNC needed!

---

## 🆙 Upgrade to Java 25? (Optional)

Your game currently targets Java 21 but can be upgraded to Java 25 for:
- Latest JVM optimizations
- Newer language features
- Better performance

To upgrade:

```bash
# Just change the source/target to 25
javac -source 25 -target 25 --module-path lib --add-modules javafx.controls,javafx.fxml -cp "lib/*" -d bin src/*.java
```

Java 25 new features you could use:
- **Scoped Values** (JEP 481)
- **String Templates** (JEP 430) 
- **Structured Concurrency** (JEP 480)
- **Stream Gatherers** (JEP 473)

---

## 🌟 Benefits of Running Locally

Running on your local computer vs container:

| Feature | Container (VNC) | Local Computer |
|---------|----------------|----------------|
| **Speed** | Good | Excellent |
| **Graphics** | Via VNC | Native |
| **Latency** | Some delay | Zero |
| **Full Screen** | Limited | Native |
| **Performance** | Good | Excellent |
| **Setup** | Easy (browser) | Download files |

---

## 🎨 JavaFX Note

For the best local experience, you might need JavaFX SDK for your platform:

**Download JavaFX 21+:**
- https://openjfx.io/
- Choose your OS (Windows/Mac/Linux)
- Extract to a folder
- Use `--module-path` to point to it

**Windows:**
```cmd
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib\*" HollywoodSquares
```

**macOS:**
```bash
java --module-path "/path/to/javafx-sdk-21/lib" --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*" HollywoodSquares
```

---

## 📝 Quick Start Script

### Windows (run.bat):
```batch
@echo off
echo Starting Hollywood Squares...
java --module-path lib --add-modules javafx.controls,javafx.fxml -cp "bin;lib\*" HollywoodSquares
pause
```

### macOS/Linux (run.sh):
```bash
#!/bin/bash
echo "Starting Hollywood Squares..."
java --module-path lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*" HollywoodSquares
```

---

## 🐛 Troubleshooting

### "Error: JavaFX runtime components are missing"

**Solution:** Add JavaFX to module path
```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp "bin:lib/*" HollywoodSquares
```

### "Images not found"

**Solution:** Ensure all .jpg files are in the same directory as where you run the game

### "ClassNotFoundException"

**Solution:** Make sure `bin/` folder has all compiled .class files

---

## 🎉 Enjoy!

Your Java 25 setup will run the game **faster and smoother** than the container!

**Benefits:**
- ✅ Native performance
- ✅ No VNC lag
- ✅ Full screen support
- ✅ Better graphics
- ✅ Instant response

Have fun playing Hollywood Squares with Java 25! 🎮✨
