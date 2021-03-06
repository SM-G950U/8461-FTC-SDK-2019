/*
ADB guide can be found at:
https://ftcprogramming.wordpress.com/2015/11/30/building-ftc_app-wirelessly/
*/
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
This code is written as an example only.
Obviously, it was not tested on your team's robot.
Teams who use and reference this code are expected to understand code they use.

If you use our code and see us at competition, come say hello!
*/

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp_Dev", group="TeleOp")
public class TeleOp_Dev extends Maincanum {


    @Override
    public void runOpMode() throws InterruptedException {

        hereWeGoAgain(); //init
        hereWeGoAuto(); //turn testing
        waitForGo();

        while (opModeIsActive()) {
            //double inputY = Math.abs(gamepad1.left_stick_y) > ACCEPTINPUTTHRESHOLD ? gamepad1.left_stick_y : 0;
            //double inputX = Math.abs(gamepad1.left_stick_x) > ACCEPTINPUTTHRESHOLD ? -gamepad1.left_stick_x : 0;
            //double inputC = Math.abs(gamepad1.right_stick_x) > ACCEPTINPUTTHRESHOLD ? -gamepad1.right_stick_x : 0;

            //arcadeMecanum(inputY, inputX, inputC, leftFront, rightFront, leftBack, rightBack);

            if (gamepad1.right_trigger > .5) { //when the right trigger is pressed, it speeds the drivetrain
                leftFront.setPower(leftFrontMecanum);
                leftBack.setPower(leftBackMecanum);
                rightFront.setPower(rightFrontMecanum);
                rightBack.setPower(rightBackMecanum);
            } else if (gamepad1.left_trigger > .5) { //when the left trigger is pressed, it slows the drivetrain
                leftFront.setPower(leftFrontMecanum / 4);
                leftBack.setPower(leftBackMecanum / 4);
                rightFront.setPower(rightFrontMecanum / 4);
                rightBack.setPower(rightBackMecanum / 4);
            } else { //when no triggers are pressed
                leftFront.setPower(leftFrontMecanum / 1.5);
                leftBack.setPower(leftBackMecanum / 1.5);
                rightFront.setPower(rightFrontMecanum / 1.5);
                rightBack.setPower(rightBackMecanum / 1.5);
            }


            // foundation mover open/close
            if (gamepad1.dpad_left) {
                setFGrabber(true);


            }

            if (gamepad1.dpad_right) {
                setFGrabber(false);
            }



            if (gamepad1.dpad_up){

                setFArm(false);

            }


            if (gamepad1.dpad_down){


                setFArm(true);

            }

            if(gamepad2.b){
                sleep(2000);

                unfold();


            }


            if (gamepad1.b){

                turnAbsolute(90);
            }

            if (gamepad1.x){


                turnAbsolute(270);
            }

            if(gamepad1.y){

                turnAbsolute(180);

            }

            if (gamepad1.a){

                turnAbsolute(1);
            }

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

            blockgrabAft.setPosition(gamepad1.right_stick_x);

            telemetry.addData("-----TepeOp_Dev is for testing only. Use with caution-----","");
            telemetry.addData("-----DRIVE IS DISABLED-FACE BUTTONS WILL MAKE ROBOT SPIN----","");
            telemetry.addData("Robot heading:",robotHeading());
            telemetry.addData("Robot heading absolute:",robotHeadingAbsolute());
            telemetry.addData("-----LiftArm-----","");
            telemetry.addData("liftPos:", liftPos);
            telemetry.addData("power", liftRaise.getPower());
            telemetry.addData("targetPos", liftRaise.getTargetPosition());

            telemetry.addData("-----FGrabber-----","");
            telemetry.addData("leftFGrabber", leftFGrabber.getPosition());
            telemetry.addData("rightFGrabber", rightFGrabber.getPosition());

            telemetry.addData("-----Grabber-----","");
            telemetry.addData("PIVOT autolevel manual mode=",manualPIVOTmode);
            telemetry.addData("PIVOT desired pos:",blockgrabFore.getPosition());
            telemetry.addData("ARMPos desired pos:",blockgrabAft.getPosition());
            telemetry.update();
        }


    }
}




//----- Old teleop comments-----
/* grabber grabs | old claw style
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

            }*/



            /*move grabber ARM/aft, cont mode. [UNUSED]
            if (gamepad1.a || gamepad2.a){
            //close ARM
                blockgrabAft.setPosition(1);

            }else if (gamepad1.y || gamepad2.y){
            //open ARM
                blockgrabAft.setPosition(0);


            }else{
            //dont move ARM
                blockgrabAft.setPosition(.5);
            }

            //move grabber ARM/aft, set pos mode
            if (gamepad1.a || gamepad2.a){
            //close ARM
                blockgrabAft.setPosition(1);

            }else if (gamepad1.y || gamepad2.y){
            //open ARM
                blockgrabAft.setPosition(0);


            }else{
            //dont move ARM
                //blockgrabAft.setPosition(.5);
            }*/




            /*move PIVOT/fore. cont mode
            if (gamepad1.right_bumper || gamepad2.left_bumper){
                //close PIVOT
                blockgrabFore.setPosition(1);

            }else if (gamepad1.left_bumper || gamepad2.left_bumper){
                //open PIVOT
                blockgrabFore.setPosition(0);


            }else{
                //dont move PIVOT
                blockgrabFore.setPosition(.5);
            }*/


               /*if (gamepad1.a || gamepad2.a) {
                ARMPos = ARMPos + 0.008;

            } else if (gamepad1.y || gamepad2.y) {
                ARMPos = ARMPos - 0.008;

            } else {
                //do nothing, leftover case [UNUSED]

            }
            if (ARMPos < 0) {

                ARMPos = ARMPos + .01;

            } else if (ARMPos > .7) {

                ARMPos = ARMPos - .01;

            } else {


                blockgrabAft.setPosition(ARMPos);
            }*/