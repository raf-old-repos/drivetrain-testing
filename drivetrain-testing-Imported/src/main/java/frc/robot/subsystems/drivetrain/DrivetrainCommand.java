package frc.robot.subsystems.drivetrain;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class DrivetrainCommand extends PIDCommand {


    public DrivetrainCommand(DrivetrainSubsystem drive, double setpoint, double zRot) {
        super(new PIDController(Constants.kP, Constants.kI, Constants.kD), 
        drive.gyro::getDisplacementX, 
        (setpoint + drive.gyro.getDisplacementX()), 
        output -> {
            drive.drive(output, zRot);
            SmartDashboard.putNumber("output", output);
        },
        drive 
        );

        var aX = drive.gyro.getWorldLinearAccelX();


        


        // lets go 20 meters forward woohoo

    }



    

}
