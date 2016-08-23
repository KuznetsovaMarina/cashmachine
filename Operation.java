package cashmachine;

/**
 *
 */
public enum Operation {
    LOGIN,   //to do for user identify
    INFO,    //to look at information about bank account
    DEPOSIT, //to put money to bank account
    WITHDRAW,//to take money
    EXIT;    //to exit from cashmachine emulator; user may finish application by writing word "exit" before any command


    public static Operation getAllowableOperationByOrdinal(Integer i){
        switch (i)
        {
            case 1:
                return Operation.INFO;
            case 2:
                return Operation.DEPOSIT;
            case 3:
                return Operation.WITHDRAW;
            case 4:
                return Operation.EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
