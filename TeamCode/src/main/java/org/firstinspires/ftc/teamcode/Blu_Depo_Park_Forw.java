package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Blu_Depo_Park_Forw", group = "Autonomous")

public class Blu_Depo_Park_Forw extends org.firstinspires.ftc.teamcode.Maincanum {

    @Override
    public void runOpMode() throws InterruptedException {

        hereWeGoAgain(); //init
        hereWeGoAuto();  //autoinit
        waitForGo();


        driveNormal(-18); //drive away from wall

        driveStrafe(40,true); //drive to line

        driveNormal(-8); //press into skybridge







    }

}
