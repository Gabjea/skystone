package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Tabara", group = "")
public class Tabara extends LinearOpMode {
    
    HardwareMecanum robot = new HardwareMecanum();
 
    @Override
    public void runOpMode() {
        
        
        robot.init(hardwareMap);
        
        
        waitForStart();
        telemetry.addData("Servo1 Rotation: ","%.2f",robot.cl.getPosition());
       //telemetry.addData("Servo2 Rotation: ","%.2f",robot.cr.getPosition());
        while (opModeIsActive()){

           double powerY = -gamepad1.left_stick_y*0.5;  // valorile de pe axa OY a joystick-ului stang
           double powerS= -gamepad1.left_stick_x*0.5;
            double powerX  =  gamepad1.right_stick_x*0.5; // valorile de pe axa OX a joystick-ului stang
            double powerD  =  -gamepad1.right_stick_y*0.5;
            
           robot.frontArm.setPower((-gamepad2.left_trigger + gamepad2.right_trigger)*0.15);
           robot.tm.setPower(gamepad2.left_stick_x*0.5);
            /*if(gamepad1.a==true)
                robot.frontArm.setPower(0.25);
            else if(gamepad1.b==true)
                robot.frontArm.setPower(-0.35);
            else robot.frontArm.setPower(0);
          */
           
           if(gamepad1.y==true){
               robot.cl.setPosition(1);
               robot.cr.setPosition(1);
           }else if(gamepad1.a==true){
               robot.cr.setPosition(0);
               robot.cl.setPosition(0);
           }
            
           
            
           
                
                if(gamepad1.left_stick_y !=0){ // robotul se misca in fata
                   // robot.FL.setPower(powerY);
                //    robot.FR.setPower(powerY-0.25);
                    robot.BL.setPower(powerY);
                    robot.BR.setPower(powerY);
                }
                else if(gamepad1.right_stick_x !=0){ // robotul se roteste pe loc la stanga
                 //   robot.FL.setPower(powerX);
                  //  robot.FR.setPower(-powerX+0.1);
                    robot.BL.setPower(powerX);
                    robot.BR.setPower(-powerX);
                }
                else if(gamepad1.left_stick_x !=0){ //side
                    //robot.FL.setPower(-powerS);
                    //robot.FR.setPower(powerS-0.1);
                    robot.BL.setPower(powerS);
                    robot.BR.setPower(-powerS);
                }
        
                else if(gamepad1.right_stick_y !=0){ // diagonala
                //    robot.FL.setPower(powerD);
                    
                    robot.BR.setPower(powerD);
                }
                else{ // robotul se opreste
                  //  robot.FL.setPower(0);
                //    robot.FR.setPower(0);
                    robot.BL.setPower(0);
                    robot.BR.setPower(0);
                }
            
                
               

                telemetry.addData("PowerX: ",  "%.2f", powerX);
                telemetry.addData("PowerY: ", "%.2f", powerY);
                telemetry.addData("PowerS: ",  "%.2f", powerS);
                telemetry.addData("PowerD: ", "%.2f", powerD);
                telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                telemetry.addData("BR: ", robot.BR.getCurrentPosition());
          //      telemetry.addData("ServoTM: ",robot.tm.getPosition());
                //telemetry.addData("Servo1 Rotation: ","%.2f",robot.br.getPosition());
               /*telemetry.addData("FL: ", robot.FL.getPower());
                telemetry.addData("BL: ", robot.BL.getPower());
                telemetry.addData("FR: ", robot.FR.getPower());
                telemetry.addData("BR: ", robot.BR.getPower());
                
            */
            telemetry.update();
        
        }
        
    }
}

