package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Drivetrain.*;
import static frc.robot.Ports.Drivetrain.*;

public class Drivetrain extends SubsystemBase {
    private final TalonFX rightMaster = new TalonFX(RIGHT_MASTER);
    private final TalonFX rightAuxiliary = new TalonFX(RIGHT_AUXILIARY);
    private final TalonFX leftMaster = new TalonFX(LEFT_MASTER);
    private final TalonFX leftAuxiliary = new TalonFX(LEFT_AUXILIARY);

    private final AHRS navx = new AHRS();

    private final DifferentialDriveKinematics differentialDriveKinematics = new DifferentialDriveKinematics(0);
    private final DifferentialDriveOdometry differentialDriveOdometry = new DifferentialDriveOdometry(new Rotation2d(0));

    private final static Drivetrain INSTANCE = new Drivetrain();

    @SuppressWarnings("WeakerAccess")
    public static Drivetrain getInstance() {
        return INSTANCE;
    }

    private Drivetrain() {
        rightMaster.setInverted(RIGHT_MASTER_INVERTED);
        rightAuxiliary.setInverted(RIGHT_AUXILIARY_INVERTED);
        leftMaster.setInverted(LEFT_MASTER_INVERTED);
        leftAuxiliary.setInverted(LEFT_AUXILIARY_INVERTED);

        rightMaster.setNeutralMode(NeutralMode.Coast);
        rightAuxiliary.setNeutralMode(NeutralMode.Coast);
        leftMaster.setNeutralMode(NeutralMode.Coast);
        leftAuxiliary.setNeutralMode(NeutralMode.Coast);

        rightMaster.config_kP(0, kP);
        rightMaster.config_kI(0, kI);
        rightMaster.config_kD(0, kD);

        rightAuxiliary.config_kP(0, kP);
        rightAuxiliary.config_kI(0, kI);
        rightAuxiliary.config_kD(0, kD);

        leftMaster.config_kP(0, kP);
        leftMaster.config_kI(0, kI);
        leftMaster.config_kD(0, kD);

        leftAuxiliary.config_kP(0, kP);
        leftAuxiliary.config_kI(0, kI);
        leftAuxiliary.config_kD(0, kD);

        rightAuxiliary.follow(rightMaster);
        leftAuxiliary.follow(leftMaster);

        rightMaster.setSensorPhase(RIGHT_SENSOR_INVERTED);
        leftMaster.setSensorPhase(LEFT_SENSOR_INVERTED);
    }

    public void setPower(double power, boolean isRight) {
        if (isRight) rightMaster.set(ControlMode.PercentOutput, power);
        else leftMaster.set(ControlMode.PercentOutput, power);
    }

    public void setVelocity(double velocity, boolean isRight) {
        if (isRight) rightMaster.set(ControlMode.Velocity, velocity * TICKS_PER_METER / 10);
        else leftMaster.set(ControlMode.Velocity, velocity * TICKS_PER_METER / 10);
    }

    public double getVelocity(boolean isRight) {
        if (isRight) return rightMaster.getSelectedSensorVelocity() / TICKS_PER_METER * 10;
        return leftMaster.getSelectedSensorVelocity() / TICKS_PER_METER * 10;
    }

    public Pose2d getPose() {
        return differentialDriveOdometry.getPoseMeters();
    }

    public void setChassisSpeeds(ChassisSpeeds chassisSpeeds) {
        DifferentialDriveWheelSpeeds wheelSpeeds = differentialDriveKinematics.toWheelSpeeds(chassisSpeeds);
        setVelocity(wheelSpeeds.rightMetersPerSecond, true);
        setVelocity(wheelSpeeds.leftMetersPerSecond, false);
    }

    public double calmEasingFunction(double x) {
        return x > 0 ? 1 - Math.cos((x * Math.PI) / 2) : -(1 - Math.cos((-x * Math.PI) / 2));
    }

    public double aggressiveEasingFunction(double x) {
        return x > 0 ? 1 - (1 - x) * (1 - x) : -(1 - (1 + x) * (1 + x));
    }


    @Override
    public void periodic() {
        differentialDriveOdometry.update(
                new Rotation2d(0),
                leftMaster.getSelectedSensorPosition() / TICKS_PER_METER,
                rightMaster.getSelectedSensorPosition() / TICKS_PER_METER);
    }
}
