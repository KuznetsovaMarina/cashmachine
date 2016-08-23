package cashmachine.command;

import cashmachine.Operation;
import cashmachine.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * class CommandExecutor helps to interact with all operations
 */
public class CommandExecutor
{
    private static Map<Operation, Command> commandMap = new HashMap<Operation, Command>();

    static{
        commandMap.put(Operation.LOGIN, new LoginCommand());
        commandMap.put(Operation.INFO, new InfoCommand());
        commandMap.put(Operation.DEPOSIT, new DepositCommand());
        commandMap.put(Operation.WITHDRAW, new WithdrawCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {}

    public static final void execute(Operation operation) throws InterruptOperationException
    {
        commandMap.get(operation).execute();
    }
}
