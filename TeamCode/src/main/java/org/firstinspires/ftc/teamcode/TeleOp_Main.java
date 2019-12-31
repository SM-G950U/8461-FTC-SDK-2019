/*
ADB guide can be found at:
https://ftcprogramming.wordpress.com/2015/11/30/building-ftc_app-wirelessly/
*/
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Arrays;

/*
This code is written as an example only.
Obviously, it was not tested on your team's robot.
Teams who use and reference this code are expected to understand code they use.

If you use our code and see us at competition, come say hello!
*/

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp_Main", group="TeleOp")
public class TeleOp_Main extends org.firstinspires.ftc.teamcode.Maincanum {


    @Override
    public void runOpMode() throws InterruptedException {

        hereWeGoAgain(); //init
        waitForGo();

        while (opModeIsActive()) {
            double inputY = Math.abs(gamepad1.left_stick_y) > ACCEPTINPUTTHRESHOLD ? gamepad1.left_stick_y : 0;
            double inputX = Math.abs(gamepad1.left_stick_x) > ACCEPTINPUTTHRESHOLD ? -gamepad1.left_stick_x : 0;
            double inputC = Math.abs(gamepad1.right_stick_x) > ACCEPTINPUTTHRESHOLD ? -gamepad1.right_stick_x : 0;

            arcadeMecanum(inputY, inputX, inputC, leftFront, rightFront, leftBack, rightBack);

            if (gamepad1.right_trigger > .5) { //when the right trigger is pressed, it speeds the drivetrain
                leftFront.setPower(leftFrontMecanum);
                leftBack.setPower(leftBackMecanum);
                rightFront.setPower(rightFrontMecanum);
                rightBack.setPower(rightBackMecanum);
            } else if (gamepad1.left_trigger > .5) { //when the left trigger is pressed, it slows the drivetrain
                leftFront.setPower(leftFrontMecanum / 3);
                leftBack.setPower(leftBackMecanum / 3);
                rightFront.setPower(rightFrontMecanum / 3);
                rightBack.setPower(rightBackMecanum / 3);
            } else { //when no triggers are pressed
                leftFront.setPower(leftFrontMecanum / 1.5);
                leftBack.setPower(leftBackMecanum / 1.5);
                rightFront.setPower(rightFrontMecanum / 1.5);
                rightBack.setPower(rightBackMecanum / 1.5);
            }


            // foundation mover open/close
            if (gamepad1.dpad_up || gamepad2.dpad_up) {
                setFGrabber(true);


            }

            if (gamepad1.dpad_down || gamepad2.dpad_down) {
                setFGrabber(false);
            }


            // grabber grabs
            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                blockgrabAft.setPosition(.8);
                blockgrabFore.setPosition(.2);
                //grabber open


            } else if (gamepad1.right_bumper || gamepad2.right_bumper) {
                blockgrabAft.setPosition(.35);
                blockgrabFore.setPosition(.65);
                //grabber close


            } else {

                //grabber dont move

            }


            liftExtender.setPower(-gamepad2.right_stick_y);


            //TODO make this go faster, old ratio 1:60, new of 1:139
            if (liftPos >= 0 && liftPos <= 2500) {

                if (gamepad2.left_stick_y < -.5) {
                    liftPos = liftPos + 4.5;
                    liftRaise.setPower(.75);

                } else if (gamepad2.left_stick_y > .5) {
                    liftPos = liftPos - 4.5;
                    liftRaise.setPower(.25);

                } else {
                    liftRaise.setPower(0.025);

                }
            } else if (liftPos < 0) {
                liftPos = liftPos + 2;
            } else if (liftPos > 2500) {
                liftPos = liftPos - 2;
            }

            liftRaise.setTargetPosition((int) liftPos);
            liftRaise.setMode(DcMotor.RunMode.RUN_TO_POSITION);//main forklift lifting code


            telemetry.addData("liftPos:", liftPos);
            telemetry.addData("power", liftRaise.getPower());
            telemetry.addData("targetPos", liftRaise.getTargetPosition());
            telemetry.addData("leftFGrabber", leftFGrabber.getPosition());
            telemetry.addData("rightFGrabber", rightFGrabber.getPosition());
            telemetry.update();
        }


    }
}
