package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.FORWARD;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

public class HardwareMecanum {
    /* Public OpMode members. */

    public DcMotor frontArm = null;
   // public DcMotor ar= null;
    public Servo cl= null;
    public Servo cr =null;
    public CRServo tm= null;


    public DcMotor FL = null;
    public DcMotor FR = null;
    public DcMotor BL = null;
    public DcMotor BR = null;
    
    /**
     * IMU
     */
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public double headingOffset = 0.0;
    

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareMecanum(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        /**
         * Motors
         */
        //Get hardware for motors
        FL = hwMap.dcMotor.get("FL");
        BL = hwMap.dcMotor.get("BL");
        FR = hwMap.dcMotor.get("FR");
        BR = hwMap.dcMotor.get("BR");
        frontArm=hwMap.dcMotor.get("arm");      

      
      
        cl=hwMap.servo.get("cl");
        cr=hwMap.servo.get("cr");
        tm=hwMap.crservo.get("tm");
     //   ar=hwMap.dcMotor.get("ar");
        cr.setDirection(Servo.Direction.REVERSE);
        cr.setPosition(0);
        cl.setPosition(0);
       // tm.setPosition(0);
        
        //Set direction to all motors
       // FR.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);
    //    FL.setDirection(DcMotor.Direction.FORWARD);
        BL.setDirection(DcMotor.Direction.FORWARD);
        
      //  FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      //  FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        

        //FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    //    FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      //  FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        /**
         * IMU
         */
        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "BN055IMUCalibration.json";
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);
        
    }
    
    public boolean isGyroCalibrated() {
        return imu.isGyroCalibrated();
    }

    public void loop() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        gravity = imu.getGravity();
    }

    public double getRawHeading() {
        return angles.firstAngle;
    }

    public double getHeading() {
        return (getRawHeading() - headingOffset) % (2.0 * Math.PI);
    }

    public void resetHeading() {
        headingOffset = getRawHeading();
    }
    
    public double getHeadingDegrees() { return Math.toDegrees(getHeading()); }
}
