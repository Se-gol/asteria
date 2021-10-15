package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Intake;

public class CommandGroups {
    public static class Collect extends ParallelCommandGroup {
        public Collect() {
            addCommands(new IntakeCommands.ActivateIntake(), new ConveyorCommands.ActivateConveyor());
        }
    }

    public static class CollectAndShoot extends ParallelCommandGroup {
        public CollectAndShoot(double velocity) {
            addCommands(new Collect(), new ShooterCommands.Shoot(velocity));
        }
    }
}
