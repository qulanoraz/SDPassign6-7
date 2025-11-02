Project Report: Console RPG Battle Game

Introduction

This project is a console-based RPG (Role-Playing Game) developed in Java. The main goal of this project was to create a turn-based battle game where players can choose heroes, fight enemies, collect buffs, and move around a map. We used several design patterns that we studied in class to make the code clean and easy to expand.

Project Description

The game is a simple console RPG where the player controls a hero character on a 12x12 grid map. The player can move around the map using keyboard commands (w/a/s/d), fight against enemies, and collect buffs that improve the hero's abilities. The game ends when the player defeats all enemies or dies in battle.

Main Features

Three hero classes: Warrior, Mage, and Archer

Each class has different skills (strong and weak types)

Turn-based battle system where players choose skills

Map with walls that block movement

Buffs that can be collected (damage, health, defense)

Battle statistics tracking

Event logging system

Design Patterns Used

We implemented four important design patterns in this project:

1. Factory Pattern

The Factory Pattern is used to create hero objects. We created a HeroFactory class that takes the hero type and class name as parameters and returns the correct hero object. This makes it easy to create new heroes without writing the same code many times.

Example: When a player chooses "melee" type and "warrior" class, the factory creates a Warrior object with the correct stats.

2. Strategy Pattern

The Strategy Pattern manages the attack behavior of heroes. Each hero has three strategies (melee, ranged, and magic), and each strategy returns a skill. For example, the Warrior has a strong melee skill but weak ranged and magic skills. This pattern allows us to change attack behavior without changing the hero classes.

3. Observer Pattern

The Observer Pattern is used for event notifications. We created several observers:

BattleLogger: logs all battle events

GameStatsTracker: counts defeated enemies and used skills

ConsoleUIObserver: displays special messages for battle start, victory, and defeat

HealthMonitor: tracks hero health changes

When something happens in the game (like an attack or defeat), all observers are notified automatically.

4. Decorator Pattern

The Decorator Pattern is used for buffs. When a player picks up a buff on the map, the hero is "wrapped" with a decorator that adds extra abilities:

DamageBuff: increases attack damage

HealthBuff: restores health points

DefenseBuff: reduces incoming damage

Multiple buffs can be applied to the same hero.

Technical Implementation

Class Structure

The project has several packages:

model: Contains Hero, Warrior, Mage, Archer, Wall, Buff, and Map classes

factory: Contains HeroFactory for creating heroes

strategy: Contains attack strategy interfaces and implementations

skill: Contains skill interfaces and different skill types

observer: Contains observer interfaces and concrete observers

decorator: Contains hero decorators for buffs

Game Flow

Player selects hero type and class

Hero and enemies are placed on the map

Player moves around using keyboard commands

When player meets an enemy, battle starts

In battle, player chooses skills turn by turn

Enemy also attacks with random skills

Battle continues until someone dies

Player can collect buffs during the game

Game ends when all enemies are defeated or player dies

Battle System

The battle system is turn-based. In each turn:

Player sees available skills

Player chooses a skill by entering a number

The skill is used and damage is calculated

If enemy is alive, enemy attacks back

This continues until someone dies

Each hero class has three skills based on their type. The Warrior is strong in melee, the Archer is strong in ranged, and the Mage is strong in magic.
