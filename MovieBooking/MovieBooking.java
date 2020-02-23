import java.util.*;
import java.io.*;
import MyExceptions.*;

public class MovieBooking
{
    public static void main(String args[])
    {
        int choice=0;
        Scanner in = new Scanner(System.in);
        Theater.setData();

        while(choice != 3)
        {
            Console.clrscr();
            System.out.println("\n\n***************WELCOME*************\n\nEnter 1 to login.\nDon't have an accout? Enter 2 to sign up.\nEnter 3 to exit.\n");
            System.out.println("Enter choice:");
            choice = in.nextInt();
            try
            {
                switch(choice)
                {
                   case 1:
                        Console.clrscr();
                        Entry.login();
                        break;

                    case 2:
                        Console.clrscr();
                        Entry.signUp();
                        break;
                    case 3:
                        break;
                    default:
                        throw new InvalidInputException("Invalid choice entered! Re-enter choice\n");
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println("\nCAUGHT "+e);
            }
        }
    }
}

class Movie 
{ 
    int movieId;
    String movieName; 
    Integer screenNo; 
    String movieType; 
    String movieStatus;
    double review;
    double price;
    Time time;
    int seats[][] = new int[10][];
    int occupied;

    Movie(int movieId, String movieName, int screenNo, String movieType, String movieStatus, double review, double price,Time time, int occupied)
    {
        Random random = new Random();
        this.movieId = movieId;
        this.movieName = movieName;
        this.screenNo = screenNo;
        this.movieType = movieType;
        this.movieStatus = movieStatus;
        this.review = review;
        this.price = price;
        this.time=time;
        this.occupied=occupied;
        for(int i=0;i<10;++i)
        {
            seats[i] = new int[10];
            for(int j=0;j<10;++j)
            {
                seats[i][j] = 0;
            }
        }

        for(int i=0;i<occupied;++i)
        {
            int pos1,pos2;
            pos1 = random.nextInt(9 - 0 + 1);
            pos2 = random.nextInt(9 - 0 + 1);
            if(seats[pos1][pos2] != 1)
                seats[pos1][pos2] = 1;
            else
                i--;
        }
    }

    public void displaySeats()
    {
        System.out.println("\t\t\t   ***SEATS AVAILABLE***\n");
        int flagr,flagc;
        System.out.print("\t\t\t   ");
        for(int i=0;i<10;i++)
        {
            System.out.print((i+1)+"  ");

        }
        System.out.println();
        for(int i=0;i<10;i++)
        {
            flagr = 0;
            System.out.print("\t\t\t "+(char)(65+i)+" ");
            for(int j=0;j<10;j++)
            {
                flagc = 0;
                if(this.seats[i][j] == 0)
                    System.out.print("O  ");
                else if(this.seats[i][j] == 1)
                    System.out.print("X  ");
                else
                {
                    System.out.print("*  ");
                }
            }
            System.out.println();
        }
        System.out.println("\n\n\t\t\t-------Screen This Way-------\n");

        System.out.println("\n\n\tX - seat booked\n\tO - seat not booked\n\t* - seat selected\n\n");
    } 
} 

class Theater
{ 
    static Movie movie[] = new Movie[6];
    static Customer customer[] = new Customer[100];
    static Admin admin;
    
    public static void setData()
    {
        //movie data
        Random random = new Random();
        int movieId[] = {1,2,3,4,5,6};
        int occupied[] = new int[6];
        String movieName[] = {"Abominable","Hustlers","Kaappaan","Chhichhore","Rambo     ","Joker     "}; 
        Integer screenNo[] = {1,1,2,2,3,3}; 
        Time time[]= new Time[6];
        time[0]=new Time(10,00);
        time[1]=new Time(16,20);
        time[2]=new Time(10,0);
        time[3]=new Time(16,20);
        time[4]=new Time(10,0);
        time[5]=new Time(16,20);
        double review[] = {4.5,4.6,3.9,3.6,4.1,4.6};
        String movieType[] = {"ENGLISH","ENGLISH","TAMIL","HINDI","ENGLISH","ENGLISH"}; 
        String movieStatus[] = new String[6];
        for(int i=0;i<6;++i)
        {
            if(review[i]>4.5)
                occupied[i]=random.nextInt(95 - 50 + 1) + 80;
            else if (review[i] > 4) {
                occupied[i]=random.nextInt(80 - 30 + 1) + 30;
            }
            else{
                occupied[i]=random.nextInt(60 - 15 + 1) + 15;
            }
            if(occupied[i]==100)
            {
                movieStatus[i] = "Not available";
            }
            else if (occupied[i]>75)
            {
                movieStatus[i] = "Fast filling";
            }
            else
            {
                movieStatus[i] = "Available";
            }
        }
        double price[] =  {250,200,250,200,200,250};

        //setting movie data
        for(int i=0;i<6;++i)
        {
            movie[i] = new Movie(movieId[i], movieName[i], screenNo[i], movieType[i], movieStatus[i], review[i], price[i],time[i],occupied[i]);
        }

        //customer data
        String userId[] = {"tommy_21","brent_morey","shawn_cal12","kit__harr","dave_Floyd00","millibrown","misato__","harry_potter","simba2000","opaqueman6"};
        String password[] =  {"tommy_21","brent_morey","shawn_cal12","kit__harr","dave_Floy00","millibrown","misato__","harry_potter","simba2000","opaqueman6"};
        String name[] = {"Tom James","Brent Morey","Shawn Calvin","Harr Steve","Dave Floyd","Milli Brown","Misato Ippo","Harry Potter","Simba Pim","Gordon Ramsay"}; 
        String mobNo[] = {"2222344211","3345511135","3311245675","5566778899","1100223344","3344552234","1546102293","7712834598","2435465668","5613029534"}; 
        String emailId[] = {"tom_21@gmail.com","brent_morey@gmail.com","shawn_cal12@yahoo.com","kit__harr@gmail.com","dave@hotmail.com","millibrown@gmail.com","misato__@gmail.com","harry_potter@yahoo.com","simba2000@gmail.com","opaqueman6@gmail.com"}; 
        String sex[] = {"male","male","female","male","male","female","female","female","male","male"};
        int age[]={18,34,56,16,45,50,32,25,27,31};
      //  float credit_balance[]={500,1000,1200,100,400,150,750,2000,310,1200};

        //setting cutomer data
        for(int i=0;i<10;++i)
        {
            customer[i] = new Customer(userId[i],password[i],name[i],mobNo[i],emailId[i],sex[i],age[i]);
        }

        //setting admin data

        admin = new Admin("admin","admin");
    }

    public static void displayMovies()
    {

        Console.clrscr();
        System.out.println("********MOVIE DETAILS**********");
        System.out.println("\n\nMovie Id\tMovie Name\tReview\tScreen Number\tMovie Type\tMovie Status\tTime\tSeats left\n");
        for(int i=0;i<6;i++)
        {
            if(movie[i].occupied==100)
            {
                movie[i].movieStatus = "Not available";
            }
            else if (movie[i].occupied>75)
            {
                movie[i].movieStatus = "Fast filling";
            }
            else
            {
                movie[i].movieStatus = "Available";
            }
            System.out.println( movie[i].movieId + "\t\t" + movie[i].movieName + "\t" + movie[i].review + "\t" + movie[i].screenNo + "\t\t" + movie[i].movieType + "\t\t" + movie[i].movieStatus + "\t" + movie[i].time + "\t"+(100-movie[i].occupied));
        }
    }

    public static void displayCustomers()
    {

        Console.clrscr();
        System.out.println("********CUSTOMER DETAILS**********");
        System.out.println("\n\nCustomer Name\t\tUserID\t\tAge\tMobile Number\tEmail ID\t\tSex\n");
        for(int i=0;i<Customer.num;i++)
        {
            System.out.println(customer[i].getName() + "\t\t" + customer[i].getUserId() + "\t" + customer[i].getAge() + "\t" + customer[i].getMobNo() + "\t" + customer[i].getEmailId() + "\t" + customer[i].getSex());
        }
    }

    public static void displayCustomers(String userId)
    {

        Console.clrscr();
        int i;
        System.out.println("********CUSTOMER DETAILS**********");
        for (i=0;i<Customer.num;++i)
        {
            if(userId.equals(Theater.customer[i].getUserId()))
            {
                break;
            }   
        }
        System.out.println("\n\nCustomer Name\t\tUserID\t\tAge\tMobile Number\tEmail ID\t\tSex\n");
        System.out.println(customer[i].getName() + "\t\t" + customer[i].getUserId() + "\t" + customer[i].getAge() + "\t" + customer[i].getMobNo() + "\t" + customer[i].getEmailId() + "\t" + customer[i].getSex());
    }

}

class Ticket
{ 
    String userId; 
    int movieId; 
    String movieName; 
    double price; 
    //Date booked_date;
    //Time movie_timing;
    int movie_timing_slot;
    int numberOfTickets;
    int seats[][][] = new int[100][][];

    Ticket(String userId, int movieId, String movieName, double price, int slot, int numTickets)
    {
        this.userId = userId; 
        this.movieId = movieId; 
        this.movieName = movieName; 
        this.price = price; 
        //ticket[i].booked_date = ;  
        this.movie_timing_slot = slot;
        this.numberOfTickets = numTickets;
    }
}

class User
{ 
    private String userId;
    private String password; 
    
//    Ticket t; 

    User(String userId, String password)
    {
        this.userId = userId;
        this.password = password;
    }

    User()
    {

    }

    public String getUserId()
    {
        return userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }  
} 

class Admin extends User
{
    Admin(String userId, String password)
    {
        super(userId, password);
    }

    public void modifyMovies()
    {
        Theater.displayMovies();
        Scanner in = new Scanner(System.in);
        System.out.println("\n\nEnter the movie ID to be changed:\n\n");
        int slot = in.nextInt();
        String temp;

        System.out.println("Enter the movie id:");
        Theater.movie[slot-1].movieId = in.nextInt();
        System.out.println("Enter the movie name:");
        temp = in.nextLine();
        Theater.movie[slot-1].movieName = in.nextLine();
        System.out.println("Enter the movie review:");
        Theater.movie[slot-1].review = in.nextDouble();
        System.out.println("Enter the movie type:");
        Theater.movie[slot-1].movieType = in.nextLine();
        temp = in.nextLine();
        System.out.println("Enter the movie availablity:");
        Theater.movie[slot-1].movieStatus = in.nextLine();

        //Setting all seats to empty
        for(int i=0;i<10;++i)
        {
            Theater.movie[slot-1].seats[i] = new int[10];
            for(int j=0;j<10;++j)
            {
                Theater.movie[slot-1].seats[i][j] = 0;
            }
        }
    }

    public void adminInterface()
    {

        int choice=0;
        Scanner in = new Scanner(System.in);
        while(choice == 0)
        {
            Console.clrscr();
            System.out.println("\n************WELCOME ADMIN*************\n\n");
            System.out.println("Enter 1 to update show details or 2 to view show details or 3 to view customer details 4 to return to main menu:");
            try
            {   
                choice = in.nextInt();
                switch(choice)
                {
                    case 1:
                        modifyMovies();
                        break;

                    case 2:
                        Theater.displayMovies();
                        break;
                    case 3:
                        Theater.displayCustomers();
                        break;
                    case 4:
                        return;
                    default:
                        throw new InvalidInputException("Inavlid choice entered! Re-enter choice!");
                }

                System.out.println("\nEnter 0 to go back.");
                choice = in.nextInt();

            }
            catch(InvalidInputException e)
            {
                choice = 0;
                System.out.println("\nCAUGHT "+e);
            }
        }
    }
}

class Customer extends User
{
    private String name; 
    private String mobNo; 
    private String emailId; 
    private String sex;
    
    private int age;
    private int numTickets;
    static int tNum=0;
    static int num=0;

    Ticket ticket[] = new Ticket[100];

    Customer(String userId, String password,String name,String mobNo,String emailId,String sex,int age)
    {
        super(userId,password);
        this.name = name;
        this.mobNo = mobNo;
        this.emailId = emailId;
        this.sex = sex;
        this.age = age;
        num++;
    }

    Customer()
    {
        num++;
    }

    public String getName()
    {
        return name;
    }

    public String getMobNo()
    {
        return mobNo;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public String getSex()
    {
        return sex;
    }

    public int getAge()
    {
        return age;
    }

    public void inputCustomerDetails()
    {
        int i;
        Scanner in = new Scanner(System.in);
        int available=0,balance=0;
        String id,pass;
        while(available == 0)
        {
            available = 1;
            System.out.println("Enter user ID :");
            id = in.next();
            setUserId(id);
            for(i=0;i<num-1;++i)
            {
                if(getUserId().equals(Theater.customer[i].getUserId()))
                {
                    available = 0;
                    break;
                }
            }
            try
            {
                if(available == 0)
                {
                    throw new UserIDNotValidException("UserID "+getUserId()+" already in use! Enter a different userID!\n");
                }
            }
            catch(UserIDNotValidException e)
            {
                System.out.println("\nCAUGHT "+e);
            }
        }

        System.out.println("Enter password: ");
        pass = in.next();
        setPassword(pass);
        System.out.println("Enter name :");
        name = in.next();
        System.out.println("Enter age :");
        age = in.nextInt();
        int valid;
        valid=0;
    	while(valid==0)
    	{
            System.out.println("Enter mobile number :");
        	mobNo = in.next();
            try
            {
                if(!validateMobile())
                {
                	throw new InvalidInputException("Mobile number not valid! Re-enter your mobile number!\n");
                }
                valid=1;
            }
            catch(InvalidInputException e)
           	{
           		System.out.println("CAUGHT "+e);
           	}
        }
        valid=0;
        while(valid==0)
    	{
            System.out.println("Enter email ID :");
        	emailId = in.next();
            try
            {
                if(!validateEmail())
                {
                	throw new InvalidInputException("EmailID not valid! Re-enter your emailID!\n");
                }
                valid=1;
            }
            catch(InvalidInputException e)
           	{
           		System.out.println("CAUGHT "+e);
           	}
        }
        System.out.println("Enter sex :");
        sex = in.next();
    }

    public void bookMovies() 
    {
        Scanner in = new Scanner(System.in); 
        System.out.println("Enter the number of tickets: ");
        numTickets = in.nextInt();
       // int tempSeats[][]][] = new int[10][][];
         Theater.displayMovies(); 
        System.out.println("Enter the movie slot: ");
        int slot = in.nextInt();

        char tempRow;
        int sum = 0;
        for(int i=0;i<numTickets;i++)
        {
            Console.clrscr();
            Theater.movie[slot-1].displaySeats();
            System.out.println("Enter the seat details: ");
            System.out.println("Enter row (A-J) or enter <n> to go back: ");
            tempRow = in.next().charAt(0);
            if(tempRow=='n')
            {
                for(int k=0;k<10;++k)
                {
                    for(int j=0;j<10;++j)
                    {
                        if(Theater.movie[slot-1].seats[k][j] == 2)
                            Theater.movie[slot-1].seats[k][j] = 1;
                    }
                }   
                return;
            }
            int row = (int)tempRow - 65;
            System.out.println("Enter coloumn: ");
            int coloumn = in.nextInt();

            if(Theater.movie[slot-1].seats[row][coloumn-1] == 1)
            {
                System.out.println("Seat Already taken");
                i--;
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {

                }
                continue;
            }

            Theater.movie[slot-1].seats[row][coloumn-1] = 2;
           //tempSeats[i][row][col] = 
            sum += Theater.movie[slot-1].price;
        }

        Console.clrscr();

        Theater.movie[slot-1].displaySeats();

        ticket[tNum] = new Ticket(this.getUserId(), Theater.movie[slot-1].movieId, Theater.movie[slot-1].movieName, Theater.movie[slot-1].price, slot, numTickets);

        System.out.println("Total price: " + sum);
        tNum++;
        char choice = 'k';
        while(choice != 'y' && choice != 'n')
        {
            System.out.println("\nEnter <y> to confirm booking or <n> to cancel booking :");
            choice = in.next().charAt(0);
            try
            {
                if(choice != 'y' && choice != 'n')
                {
                    throw new InvalidInputException("Entered choice is not valid! Re-enter choice !\n");
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println("CAUGHT " + e);
            }
        }
        if(choice == 'y')
        {
            System.out.println("Ticket Booked SUCCESSFULLY!");
            Theater.movie[slot-1].occupied+=numTickets;
            for(int i=0;i<10;++i)
            {
                for(int j=0;j<10;++j)
                {
                    if(Theater.movie[slot-1].seats[i][j] == 2)
                        Theater.movie[slot-1].seats[i][j] = 1;
                }
            }
        }
        else
        {
            System.out.println("Ticket Booking CANCELLED!");
            for(int i=0;i<10;++i)
            {
                for(int j=0;j<10;++j)
                {
                    if(Theater.movie[slot-1].seats[i][j] == 2)
                        Theater.movie[slot-1].seats[i][j] = 0;
                }
            }
        }

    }

    public boolean validateEmail()
    {
    	int numofAt=0, numofDot=0,dotPos=0,atPos=0;
    	for(int i=0;i<emailId.length();i++)
    	{
    		if(emailId.charAt(i) == '.')
    		{
    			numofDot++;
    			dotPos=i;
    		}

    		if(emailId.charAt(i) == '@')
    		{
    			numofAt++;
    			atPos=i;
    		}

    		if(numofAt>1 || numofDot>1)
    		{
    			return false;
    		}

    	}		

    	if(numofAt == 0 || numofDot == 0 || dotPos<atPos)
    	{
    		return false;
    	}


    	return true;
    }
    
    

    public boolean validateMobile()
    {
    	int len=mobNo.length();
    	if(len!=10)
    	{
    		return false;
    	}
    	for(int i=0;i<len;++i)
    	{
    		if(!Character.isDigit(mobNo.charAt(i)))
    		{
    			return false;
    		}
    	}
    	return true;
    }
    

     public void editDetails()
     {
        int choice=0;

        Scanner in = new Scanner(System.in);
        int valid=0;

        while(choice!=5)
        {
            Console.clrscr();
            System.out.println("EDIT PROFILE\n");
            System.out.println("Press 1 to change name\nPress 2 to change email-ID\nPress 3 to change password\nPress 4 to change mobile number\nPress 5 to exit\n\n");
            System.out.println("\nENTER VALID OPTION:");
            try
            {
                choice = in.nextInt();
                switch(choice)
                {
                    case 1:
                        System.out.println("Enter Your new name :");
                        this.name = in.next();
                        break;
                    case 2:
                    	valid=0;
                    	while(valid==0)
                    	{
	                        System.out.println("Enter your email-ID :");
	                        this.emailId=in.next();
	                        try
	                        {
		                        if(!validateEmail())
		                        {
		                        	throw new InvalidInputException("EmailID not valid! Re-enter your emailID\n");
		                        }
		                        valid=1;
		                    }
		                    catch(InvalidInputException e)
		                   	{
		                   		System.out.println("CAUGHT "+e);
		                   	}
		                }
                        break;
                    case 3:                 
                        try{
                            System.out.println("Enter current password :");
                            String pass=in.next();
                            if(pass.equals(getPassword())){             
                                System.out.println("Enter new password :");
                                pass=in.next();
                                setPassword(pass);
                            }
                            else
                                throw new PasswordMismatchException("Entered password does not match current password!\n");
                        }
                        catch(PasswordMismatchException e)
                        {
                           System.out.println("CAUGHT "+e);
                        }
                        break;
                    case 4:
                        valid=0;
                        while(valid==0)
                    	{
	                        System.out.println("Enter your new mobile number :");
                        	this.mobNo=in.next();
	                        try
	                        {
		                        if(!validateMobile())
		                        {
		                        	throw new InvalidInputException("Mobile number not valid! Re-enter your mobile number\n");
		                        }
		                        valid=1;
		                    }
		                    catch(InvalidInputException e)
		                   	{
		                   		System.out.println("CAUGHT "+e);
		                   	}
		                }
                        break;
                    case 5:
                        return;
                    default:
                        throw new InvalidInputException("Invalid choice entered! Re-enter choice!");
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println("CAUGHT "+e);
            }
        }   
    }       
                                
    
    public void customerInterface()
    {
        int choice=0;
        Scanner in = new Scanner(System.in);
        while(choice == 0)
        {
            Console.clrscr();
            System.out.println("\n************GREETINGS "+name+"********\n\n");
            System.out.println("Enter 1 to book movie or 2 to edit your profile or 3 to view profile or 4 return to main menu: ");
            try
            {   
                choice = in.nextInt();
                switch(choice)
                {
                    case 1:
                        bookMovies();
                        break;

                    case 2:
                        editDetails();
                        break;
                        
                    case 3:
                        Theater.displayCustomers(this.getUserId());
                        break;
                    case 4:
                        return;
                    default:
                        throw new InvalidInputException("Inavlid choice entered! Re-enter choice!");
                }
                System.out.println("\nEnter 0 to go back.");
                choice = in.nextInt();
            }
            catch(InvalidInputException e)
            {
                choice = 0;
                System.out.println("\nCAUGHT "+e);
            }
        }
    }
}

class Entry
{
    public static void signUp()
    {
        Scanner in = new Scanner(System.in);
        char confirm = '-';
        System.out.println("\n\n**************SIGN UP*************\n\n");
        Theater.customer[Customer.num] = new Customer();
        Theater.customer[Customer.num-1].inputCustomerDetails();

        while(confirm != 'y' && confirm != 'n')
        {
            System.out.println("Enter <y> to confirm entered details and create accout or <n> to cancel sign-up :");
            try
            {
                confirm = in.next().charAt(0);

                if(confirm != 'y' && confirm != 'n')
                {
                    throw new InvalidInputException("Invalid input entered.Please enter <y> or <n>.");
                }
                else if(confirm == 'n')
                {
                    Customer.num--;
                }
            }
            catch(InvalidInputException e)
            {
                System.out.println("\nCAUGHT "+e);
            }
        }
    }

    public static void login()
    {
        Scanner in = new Scanner(System.in);
        int i=0,userId_valid=0;
        String userId,password;
        System.out.println("\n\n**************LOGIN*************\n\n");
        while(userId_valid == 0)
        {
            System.out.println("\nEnter userID :");
            userId = in.next();

            if(userId.equals(Theater.admin.getUserId()))
            {
                userId_valid = 1;
            } 
            else
            {
                for (i=0;i<Customer.num;++i)
                {
                    if(userId.equals(Theater.customer[i].getUserId()))
                    {
                        userId_valid = 1;
                        break;
                    }   
                }
            }

            System.out.println("\nEnter password :");
            password = in.next();
            try
            {
                if(userId_valid == 0)
                {
                    userId_valid =2;
                    throw new UserIDNotValidException("The userID entered has not been registered!\n");
                }

                if(!userId.equals(Theater.admin.getPassword()) && !password.equals(Theater.customer[i].getPassword()))
                {
                    userId_valid = 2;
                    throw new PasswordMismatchException("UserID and password does not match!\n");
                }

                if(userId.equals(Theater.admin.getPassword()) && !password.equals(Theater.admin.getPassword()))
                {
                    userId_valid = 2;
                    throw new PasswordMismatchException("UserID and password does not match!\n");
                }

                System.out.println("\nLOGIN SUCCESSFUL!");

                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    
                }

                if(userId.equals("admin"))
                {
                    Theater.admin.adminInterface();
                }

                else
                {
                    Theater.customer[i].customerInterface();
                }

            }
            catch(UserIDNotValidException e)
            {
                System.out.println("\nCAUGHT "+e);
            }
            catch(PasswordMismatchException e)
            {
                System.out.println("\nCAUGHT "+e);
            }

            while(userId_valid != 1 && userId_valid !=0)
            {
                System.out.println("\nEnter 0 to re-try login or 1 to return to main menu.");
                userId_valid = in.nextInt();
                try
                {
                    if(userId_valid != 1 && userId_valid !=0)
                    {
                        throw new InvalidInputException("Invalid option entered! Enter 0 or 1.\n");
                    }
                }
                catch(InvalidInputException e)
                {
                    System.out.println("\nCAUGHT "+e);
                }

            }
        }
    }
}

class Console
{
    public static void clrscr()
    {
    //Clears Screen in java
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}

class Time
{

    private int hour;
    private int minute;

    public Time(int h, int m)
    {
        if ( h >= 1 && h <= 23)
            hour = h;
        else 
            hour = 0;
        if ( m >= 0 && m <= 59)
            minute = m;
        else 
            minute = 0;
    }

    public String toString()
    {
        String c = "";

        if ( hour > 12)
        {
            if(hour >10)
            {
                if((hour - 12) == 12)
                     c = (hour - 12) +":"+ minute + "am";
                else
                   c = (hour - 12) +":"+ minute + "pm";
            }
            else
            {
                c = "0" + (hour - 12) +":"+ minute + "pm";
            }
        }
        else
            c = hour +":"+ minute + "am";
        if(minute == 0)
        {
            c= c.substring(0, c.length()-2)+"0"+c.substring(c.length()-2, c.length());
        }
        return c;
    }
}