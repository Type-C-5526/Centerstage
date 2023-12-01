package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.util.SubsystemWrapper;

@TeleOp(name="MainTeleOp", group="Linear OpMode")
public class MainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SubsystemWrapper subsystemWrapper = new SubsystemWrapper(hardwareMap);
        if (isStopRequested()) return;
        waitForStart();

        while (opModeIsActive()) {

            //Drive
            {
                subsystemWrapper.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            }
            //Intake
            {
                if (gamepad1.a) {
                    subsystemWrapper.setIntakePower(-1);
                } else if (gamepad1.b) {
                    subsystemWrapper.setIntakePower(1);
                } else {
                    subsystemWrapper.setIntakePower(0);
                }
            }
            //Set Elevator
            {
                if (gamepad1.right_bumper) {
                    subsystemWrapper.elevatorPosition(1900);
                }
                if (gamepad1.dpad_down) {
                    subsystemWrapper.elevatorPosition(650);
                }
                if (gamepad1.dpad_right) {
                    subsystemWrapper.elevatorPosition(1150);
                }
                if (gamepad1.dpad_up) {
                    subsystemWrapper.elevatorPosition(1750);
                }
                if (gamepad1.dpad_left) {
                    subsystemWrapper.elevatorPosition(0);
                }
            }
            //Plane
            {
                if(gamepad1.x){
                    subsystemWrapper.setLauncherPosition(1);
                }
                else {
                    subsystemWrapper.setLauncherPosition(0);
                }
            }
            //

        }
    }
}