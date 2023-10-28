package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
    private double kP;
    private double kI;
    private double kD;
    private double k_Max = 0.0;

    private double k_Min = 0.0;

    private double m_LastError = 0.0;
    private double m_ActualError = 0.0;

    private double m_ActualTime = 0.0;

    private double m_LastTime = 0.0;
    private double m_I = 0.0;



    private ElapsedTime m_timer;
    public PID(double _P, double _I, double _D) {
        this.kP = _P;
        this.kI = _I;
        this.kD = _D;
        this.m_timer = new ElapsedTime();

        this.m_timer.startTime(); //starts timer


    }
    public PID(double _P, double _I, double _D, double _max, double _min) {
        this.kP = _P;
        this.kI = _I;
        this.kD = _D;
        this.k_Max = _max;
        this.k_Min = _min;
        this.m_timer = new ElapsedTime();


        m_timer.startTime(); //starts timer


    }
    public double calculate(double _setpoint, double _currentPoint){

        m_ActualError = _setpoint - _currentPoint;
        m_ActualTime = m_timer.time();
        double _P = m_ActualError * kP;// yayy!
        double _D = ((m_ActualError-m_LastError)/(m_ActualTime-m_LastTime))*kD;
        m_I += ((m_ActualTime-m_LastTime) * m_ActualError) * kI;
        double _output = _P + m_I + _D;

        if(k_Max!=0){
            if(_currentPoint>k_Max){ //MAX!!!
                _output = 0;
            }
        }
        if(k_Min!=0){
            if(_currentPoint<k_Min){ //MAX!!!
                _output = 0;
            }
        }





        m_LastTime = m_ActualTime;
        m_LastError = m_ActualError;

        return _output;
    }

}
