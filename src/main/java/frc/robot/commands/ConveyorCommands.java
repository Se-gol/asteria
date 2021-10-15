package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Conveyor;

import static frc.robot.Constants.Conveyor.*;
import static frc.robot.Constants.Intake.POWER;

public class ConveyorCommands {
    private static final Conveyor conveyor = Conveyor.getInstance();

    public static class ActivateConveyor extends CommandBase {

        public ActivateConveyor() {
            addRequirements(conveyor);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            conveyor.setPower(POWER);
            conveyor.setFunnelPower(FUNNEL_POWER);
        }

        @Override
        public void end(boolean interrupted) {
            conveyor.setPower(POWER);
            conveyor.setFunnelPower(FUNNEL_POWER);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }

    public static class ToggleGate extends InstantCommand {
        public ToggleGate() {
            addRequirements(conveyor);
        }

        @Override
        public void initialize() {
            conveyor.togglePiston();
        }
    }

    public static class SetConveyor extends InstantCommand {
        private final boolean open;

        public SetConveyor(boolean open) {
            this.open = open;
        }

        @Override
        public void initialize() {
            conveyor.setPiston(open);
        }
    }
}
