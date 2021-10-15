// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.value_tuner.ValueTuner;

import java.io.*;
import java.util.Arrays;

import static frc.robot.Constants.Drivetrain.DEADBAND;

/**
 * The VM is configured to automatically run this class, and to call the
 * methods corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command autonomousCommand;

    private RobotContainer robotContainer;

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();
    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This method is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (autonomousCommand != null) {
            autonomousCommand.schedule();
        }
    }

    /**
     * This method is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    ValueTuner valueTuner;

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        valueTuner = new ValueTuner("key3", 3);

    }

    public double easeOutQuint(double x) {
        return x > 0 ? 1 - Math.pow(1 - x, 5) : -(1 - Math.pow(1 + x, 5));
    }


    /**
     * This method is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
//        System.out.println(valueTuner.getValue());
//        XboxController xboxController = new XboxController(0);
//        xboxController.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
//        System.out.println(easeOutQuint(-xboxController.getY(GenericHID.Hand.kLeft)));

    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    double angle = 0;

    static void modifyFile(String filePath, String oldString, String newString) {
        File fileToBeModified = new File(filePath);

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent

            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent

            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO:easing function and mapping for drivetrain steer

    /**
     * This method is called periodically during test mode.
     */
    public void testPeriodic2() {
        XboxController xboxController = new XboxController(0);
/*
        double x = xboxController.getX(GenericHID.Hand.kRight);
        double y = -xboxController.getY(GenericHID.Hand.kRight);
        if (Math.abs(x) < 0.1) x = 0;
        if (Math.abs(y) < 0.1) y = 0;
        if (Math.hypot(x, y) >= 0.5) {
            angle = Math.toDegrees(Math.atan2(y, x));
            angle = angle < 0 ? angle + 360 : angle;
            angle = angle > 180 ? angle < 270 ? 180 : 0 : angle;
        }
        System.out.println(angle);
*/

        double y = -xboxController.getY(GenericHID.Hand.kLeft);
        double x = xboxController.getX(GenericHID.Hand.kLeft);

        if (Math.abs(x) < DEADBAND) x = 0;
        if (Math.abs(y) < DEADBAND) y = 0;
        x /= 2;
        x = (int) x + (int) ((x * 10 - (int) x)) / 10.;
        y = (int) y + (int) ((y * 10 - (int) y)) / 10.;
        System.out.println((y + x) + " " + (y - x));
    }

    final Translation2d[] wheelSkews = {
            new Translation2d(0.75, 0.75),
            new Translation2d(-0.75, 0.75),
            new Translation2d(0.75, -0.75),
            new Translation2d(-0.75, -0.75)
    };

    final SwerveDriveKinematics swerveDriveKinematics = new SwerveDriveKinematics(wheelSkews);


    public SwerveModuleState[] setChassisSpeeds(ChassisSpeeds desiredChassisSpeeds) {
        desiredChassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                desiredChassisSpeeds.vxMetersPerSecond,
                desiredChassisSpeeds.vyMetersPerSecond,
                desiredChassisSpeeds.omegaRadiansPerSecond,
                new Rotation2d(Math.toRadians(UtilEverything.toMinus180Plus180(90))));

        SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(desiredChassisSpeeds);
        Arrays.stream(swerveModuleStates).forEach(e -> e.angle = new Rotation2d(Math.toRadians(UtilEverything.toPositive(e.angle.getDegrees()))));

        return setModuleStates(swerveModuleStates);
    }

    public SwerveModuleState[] setModuleStates(SwerveModuleState[] desiredStates) {
        for (int i = 0; i < 4; i++) {
            desiredStates[i] = SwerveModuleState.optimize(desiredStates[i], new Rotation2d(Math.toRadians((0))));
            desiredStates[i].angle = new Rotation2d(Math.toRadians(UtilEverything.toPositive(desiredStates[i].angle.getDegrees())));
        }
        return desiredStates;
    }
}
