package cashmachine;

import cashmachine.command.CommandExecutor;
import cashmachine.exception.InterruptOperationException;

import java.util.Locale;

/**
 *
 */
public class CashMachine
{
    public static void main(String[] args){
        Locale.setDefault(Locale.ENGLISH);
        try{
            Operation nextOperation = Operation.LOGIN;
            CommandExecutor.execute(nextOperation);
            do{
                nextOperation = ConsoleHelper.askOperation();
                CommandExecutor.execute(nextOperation);
            }while(nextOperation != Operation.EXIT);
        }catch (InterruptOperationException e){}
    }
}
