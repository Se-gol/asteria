package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;

import static frc.robot.Constants.Turret.*;
import static frc.robot.Ports.Turret.*;

public class Turret extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(MOTOR);
    private final static Turret INSTANCE = new Turret();

    @SuppressWarnings("WeakerAccess")
    public static Turret getInstance() {
        return INSTANCE;
    }

    private Turret() {
        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);

        motor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog, 0, 20);
        motor.setInverted(MOTOR_INVERTED);
        motor.setSensorPhase(SENSOR_INVERTED);
    }

    private double map(double value, double minimumInput, double maximumInput, double minimumOutput, double maximumOutput) {
        return (value - minimumInput) * (maximumOutput - minimumOutput) / (maximumInput - minimumInput) + minimumOutput;
    }

    public void setPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    public double getVelocity() {
        return motor.getSelectedSensorVelocity() / TICKS_PER_DEGREE / 10;
    }

    public void setVelocity(double velocity) {
        motor.set(ControlMode.Velocity, velocity * TICKS_PER_DEGREE * 10);
    }

    public int getPositionTicks() {
        return (int) motor.getSelectedSensorPosition();
    }

    public void setPosition(int position) {
        motor.set(ControlMode.Position, position);
    }

    public void setPosition(double x, double y) {
        double angle = Math.toDegrees(Math.atan2(y, x));
        angle = angle < 0 ? angle + 360 : angle;
        angle = MathUtil.clamp(angle, 0, 180);
        int ticks = (int) map(angle, 0, 180, MIN_TICKS, MAX_TICKS);
        setPosition(ticks);
    }
}
