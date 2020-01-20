package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Directie {

    public void Miscare(String directie, double power, DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){

        switch (directie){

            case "fata": // robotul se misca in fata
            case "spate": { // robotul se misca in spate
                FL.setPower(power);
                FR.setPower(power);
                BL.setPower(power);
                BR.setPower(power);
                break;
            }

            case "stanga": // robotul se roteste la stanga pe loc
            case "dreapta": { // robotul se roteste la dreapta pe loc
                FL.setPower(-power);
                FR.setPower(-power);
                BL.setPower(power);
                BR.setPower(power);
                break;
            }

            case "side": { // robotul se opreste
                FL.setPower(power);
                FR.setPower(-power);
                BL.setPower(-power);
                BR.setPower(power);
                break;
            }

            case "stop": { // robotul se opreste
                FL.setPower(0);
                FR.setPower(0);
                BL.setPower(0);
                BR.setPower(0);
                break;
            }
        }
    }
}
