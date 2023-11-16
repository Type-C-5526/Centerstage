package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SubsystemWrapper {

    private HardwareMap m_hM;
    private DcMotor elevatorLeft;
    private DcMotor elevatorRight;
    private DcMotor intake;
    private Servo plane;
    private CRServo arm;
    private PID lPID;
    private PID rPID;
    private boolean elevatorActive;

    public SubsystemWrapper(HardwareMap hM){
        m_hM = hM;

        elevatorLeft = m_hM.dcMotor.get("el");
        elevatorRight = m_hM.dcMotor.get("er");
        intake = m_hM.dcMotor.get("intake");
        plane = m_hM.servo.get("avion");
        arm = m_hM.crservo.get("arm");

        lPID = new PID(0.05, 0,0, 2300, 0);
        rPID = new PID(0.05, 0,0, 0, -2300);
        elevatorActive = true;
    }

    /**
     *
     * @param p
     */
    public void setLauncherPosition(double p){
        plane.setPosition(p);
    }

    /**
     *
     * @param p
     */
    public void setArmPosition(double p){
        arm.setPower(p);
    }
    /**
     *
     * @param p
     */
    public void elevatorPosition(int p){
        if(elevatorActive) {
            elevatorLeft.setPower(lPID.calculate(p, elevatorLeft.getCurrentPosition()));
            elevatorRight.setPower(rPID.calculate(-p, elevatorRight.getCurrentPosition()));
        }
        else {
            elevatorLeft.setPower(0);
            elevatorRight.setPower(0);
        }
    }
    public void setIntakePower(double p){
        intake.setPower(p);
    }
    public void activateElevator(){
        elevatorActive = true;
    }
    public void deactivateElevator(){
        elevatorActive = false;
    }



}
