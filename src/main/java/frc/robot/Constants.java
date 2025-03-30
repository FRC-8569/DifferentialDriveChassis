package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Constants {
    public static class MotorConstants{
        public static final int LeftMotorID = 5;
        public static final int RightMotorID = 8;

        public static final double[] LeftMotorPID = {0.000075, 0, 0};
        public static final double[] RightMotorPID = LeftMotorPID;

        public static final MotorType kMotorType = MotorType.kBrushless;
    }

    public static class OIConstants {
        public static final int kDriveAxis = 1;
        public static final int kRotationAxis = 0;
        public static final int kIncreaseSpeedBtn = 6;
        public static final int kDecreaseSpeedBtn = 5;

    }
}
