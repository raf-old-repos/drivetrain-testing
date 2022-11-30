package frc.robot.subsystems.drivetrain;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class DrivetrainCommand extends CommandBase {

    private DrivetrainSubsystem drivetrain;
    private double displacement;

    public DrivetrainCommand(DrivetrainSubsystem drivetrain, double displacement) {

        this.drivetrain = drivetrain;
        this.displacement = displacement;
    }

    @Override
    public void execute() {
        drivetrain.setPosition(displacement);
    }

    @Override
    public boolean isFinished(){
        return false;
    }


    

}
