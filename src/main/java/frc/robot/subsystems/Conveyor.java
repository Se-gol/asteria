package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Ports.Conveyor.*;

public class Conveyor extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(MOTOR);
    private VictorSPX funnel = new VictorSPX(FUNNEL);
//    private AnalogInput proximitySensor = new AnalogInput(PROXIMITY_SENSOR);
//    private AnalogInput proximitySensor2 = new AnalogInput(PROXIMITY_SENSOR_2);
//    private Solenoid piston = new Solenoid(PISTON);


    private final static Conveyor INSTANCE = new Conveyor();

    @SuppressWarnings("WeakerAccess")
    public static Conveyor getInstance() {
        return INSTANCE;
    }

    private Conveyor() {
        motor.setInverted(MOTOR_INVERTED);
        funnel.setInverted(FUNNEL_INVERTED);
    }

    public void setPower(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    public void setFunnelPower(double power) {
        funnel.set(ControlMode.PercentOutput, power);
    }

    public void togglePiston() {
//        piston.toggle();
    }

    public void setPiston(boolean open) {
//        piston.set(!open);
    }
}
