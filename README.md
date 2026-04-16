# Robot Rescue

A console-based Java adventure game developed as part of a university **Programming 1** project.  
In this game, the player controls a robot exploring a research station, solving code-based challenges, facing enemies, playing mini-games, and collecting the components needed to repair and launch a shuttle.

## Overview

**Robot Rescue** is a text-based adventure game built with object-oriented Java.  
The game combines multiple mechanics into one experience:

- exploration of locked rooms
- code-solving challenges
- enemy encounters
- mini-games
- robot energy and damage management
- artifact collection and shuttle repair
- save/load game functionality

The main objective is to explore the station, recover all required shuttle artifacts, install them, and successfully complete the mission before the robot runs out of energy or becomes inoperable.

## Features

- **Object-oriented design** using multiple classes and packages
- **Console-based gameplay** with menu-driven interaction
- **Room exploration system** with randomly selected unopened rooms
- **Puzzle challenges**, including:
  - Binary code
  - Morse code
  - Caesar cipher / secret code
- **Enemy encounters** with different enemy types
- **Mini-games**:
  - Tic-Tac-Toe
  - Nim
- **Robot status management**:
  - energy
  - damage
  - experience points
- **Maintenance system** for repairing the robot and installing artifacts
- **Save and load functionality** using Java serialization

## Project Structure

```text
app/         -> application entry point and main game flow
challenges/  -> puzzle and code-based challenge classes
games/       -> mini-game implementations
model/       -> core game entities such as Robot, Room, Enemy, Artifact, Shuttle
utils/       -> utility classes
