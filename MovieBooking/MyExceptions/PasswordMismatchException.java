package MyExceptions;

public class PasswordMismatchException extends Exception
{
    String str;

    public PasswordMismatchException(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return "PasswordMismatchException!\n"+str;
    }
}