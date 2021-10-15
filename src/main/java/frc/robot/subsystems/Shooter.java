package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Ports.Shooter.*;
import static frc.robot.Constants.Shooter.*;

public class Shooter extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(MOTOR);
    private final VictorSPX auxiliary = new VictorSPX(AUXILIARY);
    private final VictorSPX auxiliary2 = new VictorSPX(AUXILIARY_2);

    private final static Shooter INSTANCE = new Shooter();

    @SuppressWarnings("WeakerAccess")
    public static Shooter getInstance() {
        return INSTANCE;
    }

    private Shooter() {
        motor.setNeutralMode(NeutralMode.Coast);
        auxiliary.setNeutralMode(NeutralMode.Coast);
        auxiliary2.setNeutralMode(NeutralMode.Coast);

        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);

        auxiliary.config_kP(0, kP);
        auxiliary.config_kI(0, kI);
        auxiliary.config_kD(0, kD);

        auxiliary2.config_kP(0, kP);
        auxiliary2.config_kI(0, kI);
        auxiliary2.config_kD(0, kD);

        motor.setInverted(MOTOR_INVERTED);
        auxiliary.setInverted(AUXILIARY_INVERTED);
        auxiliary2.setInverted(AUXILIARY_2_INVERTED);

        motor.setSensorPhase(SENSOR_INVERTED);
        auxiliary.setSensorPhase(AUXILIARY_SENSOR_INVERTED);
        auxiliary2.setSensorPhase(AUXILIARY_2_SENSOR_INVERTED);

        auxiliary.follow(motor);
        auxiliary2.follow(motor);
    }

    public void setPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    public double getVelocity() {
        return motor.getSelectedSensorVelocity() / TICKS_PER_REVOLUTION * 10;
    }

    public void setVelocity(double velocity) {
        motor.set(ControlMode.Velocity, velocity * TICKS_PER_REVOLUTION / 10);
    }
}

