package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class Chassis extends SubsystemBase{
    public SparkMax LeftMotor, RightMotor;
    public SparkClosedLoopController LeftPID, RightPID;
    public RelativeEncoder LeftEncoder, RightEncoder;
    public SparkMaxConfig LeftConfig, RightConfig;

    public int SpeedMode = 6;

    public Chassis(){
        LeftMotor = new SparkMax(MotorConstants.LeftMotorID, MotorConstants.kMotorType);
        RightMotor = new SparkMax(MotorConstants.RightMotorID, MotorConstants.kMotorType);

        LeftConfig
            .idleMode(IdleMode.kBrake)
            .inverted(false);
        LeftConfig.closedLoop
            .outputRange(-1, 1)
            .pid(MotorConstants.LeftMotorPID[0], MotorConstants.LeftMotorPID[1], MotorConstants.LeftMotorPID[2]);
        
        RightConfig
            .idleMode(IdleMode.kBrake)
            .inverted(true);
        RightConfig.closedLoop
            .outputRange(-1, 1)
            .pid(MotorConstants.RightMotorPID[0], MotorConstants.RightMotorPID[1], MotorConstants.RightMotorPID[2]);
        
        LeftMotor.configure(LeftConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        RightMotor.configure(RightConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

        LeftEncoder = LeftMotor.getEncoder();
        RightEncoder = RightMotor.getEncoder();
        LeftPID = LeftMotor.getClosedLoopController();
        RightPID = RightMotor.getClosedLoopController();
    }

    public void drive(double speed, double rotation){
        LeftPID.setReference((SpeedMode/10)*(speed+rotation), ControlType.kVelocity);
        RightPID.setReference((SpeedMode/10)*(speed-rotation), ControlType.kVelocity);
    }

    public Command toggleSpeedMode(boolean inverted){
        return runOnce(() -> SpeedMode += !inverted ? 1 : -1);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("SpeedMode", SpeedMode);
    }
}
