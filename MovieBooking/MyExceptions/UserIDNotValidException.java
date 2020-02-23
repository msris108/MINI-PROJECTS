package MyExceptions;

public class UserIDNotValidException extends Exception
{
    String str;

    public UserIDNotValidException(String str)
    {
        this.str = str;
    }

    public String toString()
    {
        return "UserIDNotValidException!\n"+str;
    }
}
