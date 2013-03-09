*Expected Results*
AUTONOMOUS- moves itself to the designated location, tilts the shooter up for x seconds, starts
the shoot command(starts up the shooter motor, pushes the loader arm FOUR time) 
TELEOP- allows driver to drive the robot, allows vision processing to automatically angle itself
so that it faces the goal (if this does not work, manually tilt and pan using the second joystick),
allows the second driver to start up the shooter motors and allows him/her to push the frisbees in 
maually

*Joystick Control*
1. Emergency Shooter button- stops shooter motor, panning, tilting, loader motors COMPLETELY
2. Emergency break button- stops the driver motors COMPLETELY. 
Press them again to undo. If you don't press again, you will not be able to control the robot
3. Info Button- prints out the current information about the robot
4. Start Vision processing- *ONLY FOR TELEOP*- starts to search the surrounding for the goal. Angles 
itself to face the goal. If it can not find one, an error message will be printed on the console.
If so, you must start the vision processing again. If the code succeeds in finding the goal, it will print
out a message on the console. 
5. Load button- moves the loader arm and then moves it backward to reset

*Important Reminders*
1. Autonomous code was written to only shoot 4 frisbees. Shooter motors will stop right after the loader
motor moves 4 times. 
2. During teleop- vision processing does not automatically shoot the Frisbee. You must manually start up the motors 
and load the Frisbee using your joystick
3. During autonomous, we are assuming the robot's starting location does not change

*Code Description*
RobotMap.java
Assigns every single input/output port value for all motors and joystick/buttons. They should be
constants so they are declared as "final"

OI.java
Instantiate joystick and button objects. They should be constants so they are declared as "final"

RobotMain.java
Executes commands, commandgroups, and the subsystems. This is essentially the brain of the robot.
Everything that should happen during autonomous goes under autonomousPeriodic(). This method loops
itself. Everything that should happen during teleop goes under teleopPeriodic(). This method also 
loops itself. Also, for example, if a command should start if a certain button is pushed, put that
piece of code in here. Lastly, all command, subsystems, commandgroups should be instantiated and 
started in here. 

Commands
AutonomousCommand.java
Contains everything the robot should do when an instance of the AutonomousCommand is called.

CommandBase.java
All instances of the subsystems are done in here.

OptimizeTrajectory.java
Contains everything the robot should do when an instance of the OptimizeTrajectory is called.
This essentially executes vision processing code, finds the center mass of the goal, and angles 
the shooter to face that center mass. 

Shoot.java
Contains everything the robot should do when an instance of the Shoot is called.
This starts up the shooter motor at a certain PWM value, turns on the loader motors to push the
Frisbee in. The number of frisbees has to be specified when initializing the Shoot command. 
