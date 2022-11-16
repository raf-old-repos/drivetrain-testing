package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.DrivetrainCommand;
import frc.robot.subsystems.drivetrain.DrivetrainSubsystem;

public class DriveCommand extends CommandBase {

private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
private final DrivetrainCommand drivetrainCommand = new DrivetrainCommand(drivetrain, Constants.SET_POINT, Constants.Z_ROT);

public DriveCommand() {
    addRequirements(drivetrain);
}

    
    // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

      drivetrainCommand.execute();  

      // drive 20 meters forward 

      

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
