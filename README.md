# The Mower

## Goal
Build a program that implement the following mower’s specification.

## The tasks
The company X wants to develop an auto-mower for square surfaces.

The mower can be programmed to go throughout the whole surface. Mower's position is
represented by coordinates (X,Y) and a characters indicate the orientation according to cardinal notations
(N,E,W,S).<br/>
The lawn is divided in grid to simplify navigation.

For example, the position can be 0,0,N, meaning the mower is in the lower left of the lawn, and oriented to
the north.

To control the mower, we send a simple sequence of characters. Possible characters are
L,R,F. L and R turn the mower at 90° on the left or right without moving the mower. F means the mower
move forward from one space in the direction in which it faces and without changing the orientation.

If the position after moving is outside the lawn, mower keep it's position. Retains its orientation and go to
the next command.<br/>
We assume the position directly to the north of (X,Y) is (X,Y+1).

To program the mower, we can provide an input file constructed as follows:<br/>
The first line corresponds to the coordinate of the upper right corner of the lawn. the bottom left corner is
assumed as (0,0).

The rest of the file can control multiple mowers deployed on the lawn. Each mower has 2 next lines:

The first line gives mower's starting position and orientation as "X Y O". X and Y being the
position and O the orientation.

The second line gives instructions to the mower to go throughout the lawn. Instructions are
characters without spaces.

Each mower moves sequentially, meaning that the second mower moves only when the first has fully
performed its series of instructions.

When a mower has finished, it gives the final position and orientation.

## Example
**input file**<br/>
5 5<br/>
1 2 N<br/>
LFLFLFLFF<br/>
3 3 E<br/>
FFRFFRFRRF

**result**<br/>
1 3 N<br/>
5 1 E

## Stack 
- mowplan : Vue.js frontend
- mower: Java / Spring Boot backend

## Ameliorations possibles
- frontend:

- backend:
  - use Orientation with number (0 to 3) to handle "turn" function
    - example 1 : NORTH -> go LEFT = 0-1 = -1; -1%4 = 3 = WEST
	- example 2 : WEST -> go RIGHT = 3+1 = 4; 4%4 = 0 = NORTH
