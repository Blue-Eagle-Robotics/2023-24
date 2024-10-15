package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;


public class TeleopArm extends Command {

    private Arm arm;
    private DoubleSupplier speed;

    public TeleopArm(Arm arm, DoubleSupplier speed) {
        addRequirements(arm);
        this.arm = arm;
        this.speed = speed;
    }
    
    @Override
    public void initialize() {
        // Runs once on start
    }

    @Override
    public void execute() {
        // Runs repeatedly after initialization
        double output = MathUtil.applyDeadband(speed.getAsDouble(), Constants.stickDeadband);
        arm.drive(arm.getAbsoluteAdjustedPosition() < 40 && output < 0 ? output / 5 : output);
    }

    @Override
    public void end(boolean interrupted) {
        // Runs when ended/cancelled
    }

    @Override
    public boolean isFinished() {
        return false;
        // Whether or not the command is finished
    }
}