package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Chassis;

public class chassisCmd extends Command{
    public Chassis chassis;
    public Supplier<Double> DriveSpeedFunc, RotationSpeedFunc;

    public chassisCmd(Chassis chassis, Supplier<Double> drive, Supplier<Double> rotation){
        this.chassis = chassis;
        this.DriveSpeedFunc = drive;
        this.RotationSpeedFunc = rotation;

        addRequirements(chassis);
    }

    @Override
    public void execute(){
        chassis.drive(DriveSpeedFunc.get(), RotationSpeedFunc.get());
    }
}
