package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FieldCentricMecanum;

public class SubsystemWrapper {

    private HardwareMap m_hM;
    private DcMotor m_elevatorLeft;
    private DcMotor elevatorRight;
    private DcMotor m_intake;
    private Servo m_plane;
    private CRServo m_arm;
    private PID m_lPID;
    private PID m_rPID;
    private boolean m_elevatorActive;

    private DcMotor m_fl;
    private DcMotor m_fr;
    private DcMotor m_bl;
    private DcMotor m_br;

    private IMU m_imu;

    private FieldCentricMecanum m_drive;

    public SubsystemWrapper(HardwareMap hM){
        m_hM = hM;

        m_elevatorLeft = m_hM.dcMotor.get("el");
        elevatorRight = m_hM.dcMotor.get("er");
        m_intake = m_hM.dcMotor.get("intake");
        m_plane = m_hM.servo.get("avion");
        m_arm = m_hM.crservo.get("arm");

        m_fl  = m_hM.dcMotor.get("fl");
        m_fr  = m_hM.dcMotor.get("fr");
        m_bl  = m_hM.dcMotor.get("bl");
        m_br  = m_hM.dcMotor.get("br");

        m_imu = m_hM.get(IMU.class, "imu");

        m_lPID = new PID(0.05, 0,0, 2300, 0);
        m_rPID = new PID(0.05, 0,0, 0, -2300);
        m_elevatorActive = true;



        m_drive = new FieldCentricMecanum(m_fl, m_bl, m_fr, m_br, m_imu);
    }

    /**
     *
     * @param leftStickY
     * @param leftStickX
     * @param rightStickX
     */
    public void drive(double leftStickY, double leftStickX, double rightStickX){
        m_drive.drive(leftStickY,leftStickX,rightStickX);
    }
    public void resetIMU(){
        m_drive.resetIMU();
    }

    /**
     *
     * @param p
     */
    public void setLauncherPosition(double p){
        m_plane.setPosition(p);
    }

    /**
     *
     * @param p
     */
    public void setArmPosition(double p){
        m_arm.setPower(p);
    }
    /**
     *
     * @param p
     */
    public void elevatorPosition(int p){
        if(m_elevatorActive) {
            m_elevatorLeft.setPower(m_lPID.calculate(p, m_elevatorLeft.getCurrentPosition()));
            elevatorRight.setPower(m_rPID.calculate(-p, elevatorRight.getCurrentPosition()));
        }
        else {
            m_elevatorLeft.setPower(0);
            elevatorRight.setPower(0);
        }
    }
    public void setIntakePower(double p){
        m_intake.setPower(p);
    }
    public void activateElevator(){
        m_elevatorActive = true;
    }
    public void deactivateElevator(){
        m_elevatorActive = false;
    }



}
