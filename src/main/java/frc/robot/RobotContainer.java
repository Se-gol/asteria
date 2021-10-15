// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Turret;

import javax.swing.plaf.basic.BasicButtonUI;

public class RobotContainer {
    private final Drivetrain drivetrain = Drivetrain.getInstance();
    private final Turret turret = Turret.getInstance();
    public static XboxController xboxController = new XboxController(0);

    Trigger rt = new Trigger(() -> xboxController.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.3);
    Trigger lt = new Trigger(() -> xboxController.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.3);
    JoystickButton a = new JoystickButton(xboxController, XboxController.Button.kA.value);
    JoystickButton b = new JoystickButton(xboxController, XboxController.Button.kB.value);
    JoystickButton x = new JoystickButton(xboxController, XboxController.Button.kX.value);
    JoystickButton y = new JoystickButton(xboxController, XboxController.Button.kY.value);


    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        rt.whileActiveOnce(new CommandGroups.CollectAndShoot(70));
    }

    public Command getAutonomousCommand() {
        drivetrain.setDefaultCommand(new DrivetrainCommands.OneJoystickDrive());
        turret.setDefaultCommand(new TurretCommands.TurretControl());
        return null;
    }
}
