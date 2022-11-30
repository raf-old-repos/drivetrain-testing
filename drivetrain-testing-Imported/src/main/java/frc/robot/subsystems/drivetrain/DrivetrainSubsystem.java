package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// doesnt work for some unknown reason
// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;

public class DrivetrainSubsystem extends SubsystemBase {

  private SparkMaxPIDController PIDControllerMiddleRight;
  private SparkMaxPIDController PIDControllerMiddleLeft;

  private double target = 0;

  public final AHRS gyro = new AHRS(SPI.Port.kMXP);

  private CANSparkMax frontLeft = new CANSparkMax(Constants.FRONT_LEFT_MOTOR, MotorType.kBrushless);
  private CANSparkMax frontRight = new CANSparkMax(Constants.FRONT_RIGHT_MOTOR, MotorType.kBrushless);
  private CANSparkMax middleLeft = new CANSparkMax(Constants.MIDDLE_LEFT_MOTOR, MotorType.kBrushless);
  private CANSparkMax middleRight = new CANSparkMax(Constants.MIDDLE_RIGHT_MOTOR, MotorType.kBrushless);
  private CANSparkMax backLeft = new CANSparkMax(Constants.BACK_LEFT_MOTOR, MotorType.kBrushless);
  private CANSparkMax backRight = new CANSparkMax(Constants.BACK_RIGHT_MOTOR, MotorType.kBrushless);

  private MotorControllerGroup left = new MotorControllerGroup(frontLeft, middleLeft, backLeft);
  private MotorControllerGroup right = new MotorControllerGroup(frontRight, middleRight, backRight);

  // Encoders
  private RelativeEncoder frontRightEncoder = this.frontRight.getEncoder();
  private RelativeEncoder frontLeftEncoder = this.frontLeft.getEncoder();
  private RelativeEncoder middleLeftEncoder = this.middleLeft.getEncoder();
  private RelativeEncoder middleRightEncoder = this.middleRight.getEncoder();
  private RelativeEncoder backLeftEncoder = this.backLeft.getEncoder();
  private RelativeEncoder backRightEncoder = this.backRight.getEncoder();

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

    var followRight = middleRight;
    var followLeft = middleLeft;

    frontRight.follow(followRight);
    backRight.follow(followRight);

    frontLeft.follow(followLeft);
    backLeft.follow(followLeft);

    PIDControllerMiddleLeft = middleLeft.getPIDController();
    PIDControllerMiddleRight = middleRight.getPIDController();

    PIDControllerMiddleLeft.setP(Constants.kP);
    PIDControllerMiddleLeft.setI(Constants.kI);
    PIDControllerMiddleLeft.setD(Constants.kD);

    PIDControllerMiddleRight.setP(Constants.kP);
    PIDControllerMiddleRight.setI(Constants.kI);
    PIDControllerMiddleRight.setD(Constants.kD);

    this.resetEncoders();
    
  }

  public void drive(double xSpeed, double zRotation) {
    diffDrive.curvatureDrive(xSpeed, Constants.TURN_RATE * zRotation, true);
  }

  public void setDrivePower(double power) {
    left.set(power);
    right.set(power);
  }

  public void resetEncoders() {
    // Using front wheels
    frontLeftEncoder.setPosition(0);
    frontRightEncoder.setPosition(0);
  }

  public void setTarget(double target) {
    this.target = target;
  }

  public double getLeftVoltage() {
    return left.get();
  }

  public double getRightVoltage() {
    return right.get();
  }

  // Sets the recorded heading to 0. Makes new direction the 0 heading.
  public void zeroHeading() {
    gyro.reset();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("x", gyro.getDisplacementX());
    SmartDashboard.putNumber("y", gyro.getDisplacementY());

    double kP = SmartDashboard.getNumber("kP", 0.001);
    double kI = SmartDashboard.getNumber("kI", 0);
    double kD = SmartDashboard.getNumber("kD", 0);

    this.PIDControllerMiddleLeft.setP(kP);
    this.PIDControllerMiddleLeft.setI(kI);
    this.PIDControllerMiddleLeft.setD(kD);

    this.PIDControllerMiddleRight.setP(kP);
    this.PIDControllerMiddleRight.setI(kI);
    this.PIDControllerMiddleRight.setD(kD);

  }

  public void setPosition(double displacement){
    this.PIDControllerMiddleLeft.setReference(displacement, CANSparkMax.ControlType.kPosition);
    this.PIDControllerMiddleRight.setReference(displacement, CANSparkMax.ControlType.kPosition);

}

}
