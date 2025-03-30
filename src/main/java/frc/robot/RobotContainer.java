// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.chassisCmd;
import frc.robot.subsystems.Chassis;

public class RobotContainer {
  public Chassis chassis;
  public Joystick controller;

  public RobotContainer() {
    chassis.setDefaultCommand(new chassisCmd(
      chassis, 
      () -> controller.getRawAxis(OIConstants.kDriveAxis), 
      () -> controller.getRawAxis(OIConstants.kRotationAxis))
      );
    configureBindings();
  }

  private void configureBindings() {
    new Trigger(() -> controller.getRawButton(OIConstants.kIncreaseSpeedBtn))
      .onTrue(chassis.toggleSpeedMode(false));
    new Trigger(() -> controller.getRawButton(OIConstants.kDecreaseSpeedBtn))
      .onTrue(chassis.toggleSpeedMode(true));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
