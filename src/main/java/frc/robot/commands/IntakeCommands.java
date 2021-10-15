package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

import static frc.robot.Constants.Intake.*;

public class IntakeCommands {
    private static final Intake intake = Intake.getInstance();

    public static class ToggleIntake extends InstantCommand {
        public ToggleIntake() {
            addRequirements(intake);
        }

        @Override
        public void initialize() {
            intake.togglePiston();
        }
    }

    public static class SetIntake extends InstantCommand {
        private final boolean engaged;

        public SetIntake(boolean engaged) {
            this.engaged = engaged;
        }

        @Override
        public void initialize() {
            intake.setPiston(engaged);
        }
    }

    public static class ActivateIntake extends CommandBase {
        public ActivateIntake() {
            addRequirements(intake);
        }

        @Override
        public void initialize() {
            super.initialize();
        }

        @Override
        public void execute() {
            intake.setPower(POWER);
        }

        @Override
        public void end(boolean interrupted) {
            intake.setPower(0);
        }

        @Override
        public boolean isFinished() {
            return super.isFinished();
        }
    }
}
