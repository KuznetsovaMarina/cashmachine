package cashmachine.command;

import cashmachine.ConsoleHelper;
import cashmachine.CurrencyManipulator;
import cashmachine.CurrencyManipulatorFactory;
import cashmachine.exception.InterruptOperationException;
import cashmachine.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 */
class WithdrawCommand implements Command
{
    @Override
    public void execute() throws InterruptOperationException{
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);

        while(true){
            ConsoleHelper.writeMessage("Enter amount value, please.");
            int valueWithdraw = 0;
            try
            {
                valueWithdraw = Integer.parseInt(ConsoleHelper.readString());
            }catch (Exception e){
                ConsoleHelper.writeMessage("Data is not valid.");
                continue;
            }

            if(manipulator.isAmountAvailable(valueWithdraw)){

                try
                {
                    Map<Integer, Integer> cashWithdrawMap = manipulator.withdrawAmount(valueWithdraw);

                    for (Map.Entry<Integer, Integer> pair : cashWithdrawMap.entrySet())
                    {
                        ConsoleHelper.writeMessage(pair.getKey() + " - " + pair.getValue());
                    }

                    break;
                }catch (NotEnoughMoneyException e) {}
            }
        }
    }
}
