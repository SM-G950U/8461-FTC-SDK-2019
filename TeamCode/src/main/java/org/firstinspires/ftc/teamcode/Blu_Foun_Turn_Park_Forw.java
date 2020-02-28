package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Blu_Foun_Turn_Park_Forw", group = "Autonomous")
public class Blu_Foun_Turn_Park_Forw extends org.firstinspires.ftc.teamcode.Maincanum {


    @Override
    public void runOpMode() throws InterruptedException {
        driveNormal(-22);                   //starting fwd

        sleep(100);                      //wait for robot to stop moving

        driveStrafe(11,true);  // drive closer to the wall

        sleep(100);                     //wait for stop

        driveNormal(-13);                  //get to the foundation

        setFArm(true);                              //move arms
        setFGrabber(false);                          //move grabber

        driveStrafe(6,false);

        driveNormal(30);

        turnAbsolute(280);

        setFArm(false);
        setFGrabber(true);
        sleep(100);

        driveNormal(35);

        driveStrafe(10,false);



    }
}