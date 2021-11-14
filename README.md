# Matian Robots

## Product description
Martian Robot App is a SprintBoot application that expose a REST API which allows the user to play "The Martian Robot game". For further detail about this game, go to section "The Martian Robot Game"

## How to run the product

*Prerequisites* Docker installed in the machine where the program will be run.

- Chekout the code from the public repsitory https://github.com/elenagarcia5/MartianRobots
- Under that directory, execute the provided script ./start.sh. This will build a docker image and start a docker container which will hold the application.
- Once the mentioned script finishes, the application is ready to be used, to play it, two different options have been provided:
	- Execute the script ./runApp.sh if you want to introduce the application input data from the command line.
	- Use our generated postMan project if you prefer to play the game by doing direct requests to our REST API
- To stop the application, use the provided script ./stop.sh that will stop the docker container.


### The Martian Robot game
The surface of Mars can be modelled by a rectangular grid around which robots are
able to move according to instructions provided from Earth. You are to write a
program that determines each sequence of robot positions and reports the final
position of the robot.
A robot position consists of a grid coordinate (a pair of integers: x-coordinate followed
by y-coordinate) and an orientation (N, S, E, W for north, south, east, and west). A
robot instruction is a string of the letters "L", "R", and "F" which represent,
respectively, the instructions:

- Left: the robot turns left 90 degrees and remains on the current grid point.
- Right: the robot turns right 90 degrees and remains on the current grid point.
- Forward: the robot moves forward one grid point in the direction of the current
orientation and maintains the same orientation.
The direction North corresponds to the direction from grid point (x, y) to grid point (x,
y+1).
There is also a possibility that additional command types may be required in the
future and provision should be made for this.
Since the grid is rectangular and bounded (...yes Mars is a strange planet), a robot
that moves "off" an edge of the grid is lost forever. However, lost robots leave a robot
"scent" that prohibits future robots from dropping off the world at the same grid point.
The scent is left at the last grid position the robot occupied before disappearing over
the edge. An instruction to move "off" the world from a grid point from which a robot
has been previously lost is simply ignored by the current robot.

**The input**
The first line of input is the upper-right coordinates of the rectangular world, the
lower-left coordinates are assumed to be 0, 0.
The remaining input consists of a sequence of robot positions and instructions (two
lines per robot). A position consists of two integers specifying the initial coordinates
of the robot and an orientation (N, S, E, W), all separated by whitespace on one line.
A robot instruction is a string of the letters "L", "R", and "F" on one line.

Each robot is processed sequentially, i.e., finishes executing the robot instructions
before the next robot begins execution.
The maximum value for any coordinate is 50.
All instruction strings will be less than 100 characters in length.

**The output**
For each robot position/instruction in the input, the output should indicate the final
grid position and orientation of the robot. If a robot falls off the edge of the grid the
word "LOST" should be printed after the position and orientation.