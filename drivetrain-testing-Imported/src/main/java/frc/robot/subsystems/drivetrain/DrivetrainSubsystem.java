package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// doesnt work for some unknown reason
// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;



public class DrivetrainSubsystem extends SubsystemBase {

  private CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR, MotorType.kBrushless); 
  private CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  private CANSparkMax middleLeft = new CANSparkMax(Constants.MIDDLE_LEFT_MOTOR, MotorType.kBrushless); 
  private CANSparkMax middleRight = new CANSparkMax(Constants.MIDDLE_RIGHT_MOTOR, MotorType.kBrushless); 
  private CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR, MotorType.kBrushless); 
  private CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR, MotorType.kBrushless); 

  private MotorControllerGroup left = new MotorControllerGroup(frontLeft, middleLeft, backLeft); 
  private MotorControllerGroup right = new MotorControllerGroup(frontRight, middleRight, backRight); 

  private final DifferentialDrive diffDrive = new DifferentialDrive(left, right);

  public DrivetrainSubsystem() {
    left.setInverted(false); 
    right.setInverted(true); 

    frontLeft.setIdleMode(IdleMode.kBrake);
    frontRight.setIdleMode(IdleMode.kBrake);
    middleLeft.setIdleMode(IdleMode.kBrake); 
    middleRight.setIdleMode(IdleMode.kBrake);
    backLeft.setIdleMode(IdleMode.kBrake);
    backRight.setIdleMode(IdleMode.kBrake);
  }
    

  public void drive(double xSpeed, double zRotation) {
    diffDrive.curvatureDrive(xSpeed, Constants.TURN_RATE * zRotation, true);
  }
  
}
