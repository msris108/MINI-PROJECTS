This is a theatre booking java application developed using the object oriented programming concepts in java. 

___________________________________________________________________________________

The java files in the application are:

?MovieBooking.java  
This is the java file containing the main class MovieBooking. Options to login to your
account or to exit the application are coded in this file.

?InvalidInputException.java  
This is the java file containing the exception class that handles any invalid input entered. Present in MyExceptions package.

?PasswordMismatchExcpetion.java  
This is the java file containing the exception class that handles the error when the password entered during login does not match with the userId. Present in MyExceptions package.

?UserIDNotValidException.java  
This is the java file containing the exception class that handle the error when the userId entered cannot be found in the registered list of customers in the theater data base. Present in MyExceptions package.

The class files in the application are:

?MovieBooking.class
			The MovieBooking class contains the main function. 

?Theater.class
			The Theater class contains a static list of movies and users as its members.
This class has a method setData which initializes the movie list and user list with data that can be manipulated and accessed throughout the code. The displayMovies method displays the now showing movie details in a formatted manner. The displayCustomers method implements polymorphism, depending on the type of parameter passed during function call ( if no arguments are passed,it displays details of all the registered customers, if the userID is passed as an argument, the details of that particular user can be viewed. )

?Movie.class
			The Movie class has movie details, namely, unique movie ID, movie name, screen number, movie type, movie status, review, price, time of show, and seats the are available and their number. The displaySeats() method displays the a pictorial representation of the booked, available and selected seats.

?Ticket.class
  The Ticket class has ticket detials, namely, userID, movieId, movie name, price, number of tickets.
  
?User.class
  The User class has two main data members, the userID an password. Get and set methods have been coded for both the data members.
  
?Admin.class
	The Admin class is derived from the User class. It has a pre-decided value for userId and password to enable admin login. The class has a method modifyMovies, which enable the admin to update the now-showing movies in the theatre. There is a method AdminInterface which provides a platform where the admin can choose to display movie details, modify movie details or view details of all customers. 

  
?Customer.class
	The Customer class is derived from the User class. It has additional properties such as name, mobile number, emailId, sex, age , number of tickets booked. Get and set methods for all the said data members have been coded. The method inputCustomerDetails, lets a newly registering customer enter their userId, password, personal details etc. The bookMovies method shows the Customer the list of now showing movies and gets their desired movie choice, according to the chosen movie, screen is displayed and seats are selected and payment is made according to the total price. The editDetails method lets the customer modify a specific detail in their profile. The method CustomerInterface provides a platform which lets the customers choose whether to book movie tickets, view their profile, or edit their profile.  

?Entry.class
	The Entry class provides methods signUp (to reigster a new customer to the theatre database) and login (to enable the admin or customer to login to their profile and perform allowed operations).

?Console.class
	The Console class has a method clrscr that clears the console screen each time it is called. 

?Time.class
	The Time class has members hour and minute. It is used to maintain the show timings for each movie.

?InvalidInputException.class 
Class that handles any invalid input entered.

?PasswordMismatchExcpetion.class  
Class that handles the error when the password entered during login does not match with the userId.

?UserIDNotValidException.class  
Class that handles the error when the userId entered cannot be found in the registered list of customers in the theater data base.




___________________________________________________________________________________

THE FILE CONTAINING THE MAIN CLASS IS MovieBooking.class.

TO COMPILE THE APPLICATION : javac MovieBooking.java
TO RUN THE APPLICATION : java MovieBooking


OOPS CONCEPTS INVOLVED:

?Inheritance 
?Polymorphism
?Exception handling
?Constructor overloading
?Packages
___________________________________________________________________________________

PROJECT TEAM MEMBERS:

Sriram M - 185001168
Sruthi Bijoy - 185001170
Tarun V - 185001184



Additional information regarding the execution of the project:

All the exception class must be put in a package called MyExceptions. The MyExceptions package must be in the package containing MovieBooking.class (main class) .

_______________________________________________________________________________