# About

* Class: **Programming Design Paradigms**
* Professor: **Clark Freifeld** (Northeastern University)
* Term: *Fall 2021*
* Designed and developed: **Darpan Mehra** NUID- 001399321

## Overview - Project 3 Dungeon

Dungeons are generated at random following some set of constraints resulting in a different network each time the game begins.
A dungeon can be non-wrapping or wrapping in nature. For example, moving to the north from row 0 (at the top) in the grid moves the player to the location in the same column in row 5 (at the bottom). This is an example of a wrapping dungeon.


Each location in the dungeon is connected by exactly one path if the interconnectivity is 0 or there can be more number of paths if the interconnectivity is high.
Treasure is generated at random caves in the dungeon. The percentage of the caves that contain treasure is given by the client.


A starting point is randomly selected from the dungeon and the player is then moved to that location.
A player is given an option to available direction from his current position. Upon select a direction, the player to moved to the location in that direction of the current location.
A player collects the treasures along the way.

When a player reaches the end location, the game is over and the path the player takes is displayed.
The treasure collected is also displayed.

## List of Features

* Create a non-wrapping dungeon
* Create a wrapping dungeon
* Create a dungeon with the interconnectivity of 0 or more
* Create a dungeon with caves having treasures by specifying the percent of caves to have treasure
* Generate a starting point for the player and assign it to the player
* Get the ending point for the player in the dungeon
* Move the player in the dungeon by getting the available directions from the current location
* Collect the treasure when the player visits the location
* Display the current location of the player in the dungeon along with the treasures collected
* End the game when the player reaches the destination

## How To Run

Run the jar file.
1. First argument: Dungeon height
2. Second argument: Dungeon width
3. Third argument: Interconnectivity
4. Fourth argument: Dungeon type (wrapping / nonwrapping)
5. Fifth argument: Percent of caves to have treasure

```bash
cd /YourProjectDirectory/res
java -jar Project03-Dungeon.jar 10 10 0 nonwrapping 20
```

## How to Use the Program

1. Initialize the model with the dungeon attributes such as height, width, inter-connectivity, type, treasure percentage and  random function
    1. e.g. ```model = new GameState(dungeonHeight, dungeonWidth, interConnectivity, dungeonType, treasurePercentage, rand);``` 
2. Copy of the dungeon will be displayed for your reference
3. Player will be positioned at the starting location
4. At each location, the player will be given a choice to move in the directions available which leads to the next position in the dungeon
   1. e.g. ```Player is at (2, 0) and has treasures {DIAMONDS=10, RUBIES=6, SAPPHIRES=5}
      Location originally had {RUBIES=6, DIAMONDS=10, SAPPHIRES=5} treasures which the player acquired.
      Available directions from current location: [NORTH, EAST, WEST]```
5. The player can type the direction it wants to move to and then the player will be moved to the location which corresponds to that direction from the current location.
   1. e.g. ```west
      Moving player to: (2, 5)```
6. Player collects the treasures if the player is in a cave that has treasures
7. If the player reaches the end location, the game is over and the information regarding the treasure collected and the path taken to reach the end location is displayed.

## Description of Examples

1. Example 1: Example1.txt
   1. Non-wrapping dungeon with player starting at (2, 2) and ending at (5, 4). Path length is 5, locations visited is 6.
2. Example 2: Example2.txt
   1. Wrapping dungeon with player starting at (1, 0) and ending at (4, 1). Path length is 6, locations visited is 7.
3. Example 3: Example3.txt
   1. Player visiting each location in the maze (6 x 6) before going to the end location.

## Design/Model Changes
1. Abstracted the Model functions and made it an Interface and class for the client interaction (called as GameState and IGameState)
2. Encapsulated the dungeon information in the Grid class having protected methods.
3. Added a treasure class to encapsulate the treasure information.
4. Added a treasure interface to encapsulate the treasure information.
5. Changed the Room name to Location for readability.
6. Added other relevant methods required for implementation.

## Assumptions
1. Dungeon height is between 6 and 100
2. Dungeon width is between 6 and 100
3. Interconnectivity cannot be less than 0
4. A big interconnectivity number (greater than maximum paths) means that the dungeon is fully open.
5. Dungeon can be only of two types - wrapping/ non-wrapping
6. Minimum quantity of each type in a treasure is 1 and maximum is 10.

## Limitations
* Java version 11 or more required.

## Citations
1. https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
2. https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/
3. https://www.educba.com/depth-limited-search/
