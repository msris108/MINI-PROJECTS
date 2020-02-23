package MyExceptions;

public class InvalidInputException extends Exception
{
    String str;

    public InvalidInputException(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return "InvalidInputException!\n"+str;
    }
}