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

## How to play

- Colors: **White = `O`** (you), **Black = `X`** (computer).
- On your turn, enter the starting position (1–24) for each die roll when prompted.
- The board is redrawn after every move so you can track stone positions, the bar, and borne-off stones.
- The game ends automatically once one side bears off all of their stones.

## License

This project was created for educational purposes as part of a university AI course.
