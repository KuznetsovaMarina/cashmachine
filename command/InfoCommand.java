package cashmachine.command;

import cashmachine.ConsoleHelper;
import cashmachine.CurrencyManipulator;
import cashmachine.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

/**
 *
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle("cashmachine.resources.info");
    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        if(CurrencyManipulatorFactory.getAllCurrencyManipulators().size() == 0){
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
        else {
            int counter = 0;
            for (CurrencyManipulator item : CurrencyManipulatorFactory.getAllCurrencyManipulators())
            {
                if(item.hasMoney())
                {
                    ConsoleHelper.writeMessage(item.getCurrencyCode() + " - " + item.getTotalAmount());
                }
                else counter++;
            }

            if(counter == CurrencyManipulatorFactory.getAllCurrencyManipulators().size()){
                ConsoleHelper.writeMessage(res.getString("no.money"));
            }
        }

    }
}
