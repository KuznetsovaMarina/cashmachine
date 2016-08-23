package cashmachine.command;

import cashmachine.ConsoleHelper;
import cashmachine.exception.InterruptOperationException;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *
 */
public class LoginCommand implements Command
{
    private final ResourceBundle validCreditCards = ResourceBundle.getBundle("cashmachine.resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        while(true){
            ConsoleHelper.writeMessage("Enter credit card number, please.");
            String creditNumber = ConsoleHelper.readString().trim();
            ConsoleHelper.writeMessage("Enter your PIN-code, please.");
            String pinNumber = ConsoleHelper.readString().trim();
            if(creditNumber.length() == 12 && pinNumber.length() == 4){
                if(pinNumber.equals(validCreditCards.getString(creditNumber))){
                    ConsoleHelper.writeMessage("Verification is successful.");
                    break;
                }
                else{
                    ConsoleHelper.writeMessage("Wrong credit card number or PIN-code.");
                }
            }
            else{
                ConsoleHelper.writeMessage("Data are not valid.");
            }
        }
    }
}
