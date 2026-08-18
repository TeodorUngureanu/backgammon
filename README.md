# Backgammon

A console-based Backgammon game written in Java, developed as a bonus assignment for an Artificial Intelligence lab at university. You play as White against a computer opponent (Black) that moves either randomly or using a simple heuristic AI.

## Features

- Full Backgammon rules: moving stones, bearing off, hitting/being hit and re-entering from the bar, doubles.
- ASCII-art rendering of the board after every move.
- Human vs. Computer gameplay:
  - The computer randomly picks between fully random moves and a simple rule-based strategy (prioritizes safe moves and blocking).
- Turn-based dice rolling with automatic handling of doubles.

## Project structure

```
src/
├── Main.java              # Game loop, input handling, computer player logic
├── Game.java               # Game state, turn management, move validation
├── Board.java               # Board representation and stone positions
├── Stone.java                # Stone colors
├── Dice.java                   # Dice rolling
├── DrawBoard.java                # ASCII board rendering
└── WrongMoveException.java         # Custom exception for invalid moves
```

## Requirements

- Java 8 or newer (JDK)

## How to run

### From the command line

```bash
javac -d build/classes src/*.java
java -cp build/classes Main
```

### From an IDE (Eclipse / NetBeans / IntelliJ)

1. Import the project as a Java project (the project also includes NetBeans project files under `nbproject/`).
2. Make sure the project is **not** configured as a Java module (no `module-info.java`) — the source files use the default package.
3. Run `Main.java`.

## How to play

- Colors: **White = `O`** (you), **Black = `X`** (computer).
- On your turn, enter the starting position (1–24) for each die roll when prompted.
- The board is redrawn after every move so you can track stone positions, the bar, and borne-off stones.
- The game ends automatically once one side bears off all of their stones.

## License

This project was created for educational purposes as part of a university AI course.
