package MyExceptions;

public class MinimumBalanceRequiredException extends Exception
{
    String str;

    public MinimumBalanceRequiredException(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return "MinimumBalanceRequiredException!\n"+str;
    }
}