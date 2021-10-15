package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Ports.Intake.*;

public class Intake extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(MOTOR);
    private final Solenoid piston = new Solenoid(PISTON);

    private final static Intake INSTANCE = new Intake();

    @SuppressWarnings("WeakerAccess")
    public static Intake getInstance() {
        return INSTANCE;
    }

    private Intake() {
        motor.setInverted(MOTOR_INVERTED);
        motor.setNeutralMode(NeutralMode.Coast);
    }

    public void setPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    public boolean getPiston() {
        return piston.get();
    }

    public void setPiston(boolean engaged) {
        piston.set(engaged);
    }

    public void togglePiston() {
        piston.toggle();
    }
}
