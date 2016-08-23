package cashmachine.command;

import cashmachine.ConsoleHelper;
import cashmachine.CurrencyManipulator;
import cashmachine.CurrencyManipulatorFactory;
import cashmachine.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 *
 */
class DepositCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle("cashmachine.resources.deposit");
    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        while (true) {
            try
            {
                String[] values = ConsoleHelper.getValidTwoDigits(code);
                CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
                int dom = Integer.parseInt(values[0]);
                int count = Integer.parseInt(values[1]);
                manipulator.addAmount(dom, count);
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), (dom * count), code));
                break;
            }
            catch (NumberFormatException ex)
            {
                ConsoleHelper.writeMessage(res.getString("invalid.data"));
            }
        }
    }
}

