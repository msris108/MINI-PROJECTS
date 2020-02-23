void clrscr()
{
    system("@cls||clear");
}

void delay(int number_of_seconds)
{
    int milli_seconds=1000*number_of_seconds;
    clock_t start_time=clock();
    while(clock()<start_time+milli_seconds)
        ;
}

typedef struct
{
    char bankName[100];
    customerAVLtree *ctree;
    staffAVLtree *stree;
}Bank;


void customerDetails(Customer *customer)
{
    clrscr();
	printf("\n\n***********DETAILS***********\n\n");
	printf("Name : %s\n",customer->cName);
	printf("Account number : %ld\n",customer->cId);
	printf("Email : %s\n",customer->cAddress);
	printf("Balance : Rs.%f\n",customer->cBalance);
}

void deposit(Customer *customer, float amount)
{
	if(amount>100000)
	{
		printf("\nMaximum deposit limit exeeded, deposit a lower amount!");
	}
	else
	{
		customer->cBalance+=amount;
		printf("\nAmount deposited SUCCESSFULLY!\nNew Balance: %f",customer->cBalance);
	}
}

void withdraw(Customer *customer, float amount)
{
	if((customer->cBalance-amount)>1000 && amount<100000)
	{
		customer->cBalance-=amount;
		printf("\nAmount withdrawn SUCCESSFULLY!\nNew Balance: %f",customer->cBalance);
	}
	else
	{
		printf("\nInsufficient balance, withdraw a lower amount!\nCurrent Balance : %f",customer->cBalance);
	}
}

void transfer(Bank *B,Customer *customer, long acc,float amount)
{
	Customer *recipient;
	recipient=searchCustomerAVL(B->ctree,acc);
	if((customer->cBalance-amount) >1000 && amount<500000)
	{

		recipient=searchCustomerAVL(B->ctree,acc);

		if(recipient==NULL)
		{
			printf("\n\nRecipient account number invalid!!");
			return;
		}

		else
		{
			customer->cBalance-=amount;
			recipient->cBalance+=amount;
			printf("\n\nAmount Rs.%f tranferred to account number %ld SUCCESSFULLY",amount,acc);
		}
		
	}
	else if(customer->cBalance-amount>1000)
	{
		printf("\n\nInsufficient balance, could not transfer amount!\nCurrent Balance : %f",customer->cBalance);
	}
	else
	{
		printf("\n\nMaximum transfer amount of Rs.5,00,000 exeeded, transfer a lower amount !");	
	}
}

void loan(Bank *B, Customer *customer)
{
    int loan;
    if(customer->credit<350)
    {
        printf(" \nYou are not eligible for loan ");
    }
    else if(customer->credit<450)
    {
        printf(" \n You are eligible for loan upto Rs 50,000");
    }
    else if(customer->credit<550)
    {
        printf(" \n You are eligible for loan upto Rs 1,50,000");
    }
    else if(customer->credit<650)
    {
        printf(" \n You are eligible for loan upto Rs 5,00,000");
    }
    else
    {
        printf(" \n You are eligible for loan upto Rs 7,50,000");
    }

    printf(" \n Enter loan amount:");
    scanf("%d", &loan);
    customer->cBalance+=loan;
    printf("\n Your loan has been sanctioned ");
}

void createQueue(Bank *B,priorityQueueADT *pq)
{
    int i;
    srand(time(0));
    Customer *customer;
    long acc;
    for(i=0;i<5;++i)
    {
        acc=(rand()%(185001114-185001100))+185001100;
        customer=searchCustomerAVL(B->ctree,acc);

        enqueue(pq,customer);
    }

   // displayQueue(pq,1,0);
}

int verifyCustomer(Customer *customer)
{
    char pass[100];
    printf("Please Enter the Password: ");
    scanf(" %s" , pass);

    if(strcmp(pass, customer->cPass) != 0)
    {
        printf("Verified ID proof? [y] or [n]");
        char ch;
        scanf(" %c", &ch);
        if(ch == 'y' || ch == 'Y')
        {
            printf("Enter the new Password:");
            scanf("%s" , customer->cPass);
            return 1;
        }
        else
        {
            return 0;
        }
    }
    return 1;
}

void staffMenu(Bank *B,Staff *staff)
{
	Customer *customer;
	int choice;
    int count=0;
	float amount;
	long acc;
    char c;
    staff->queue=malloc(sizeof(priorityQueueADT));
    init(staff->queue);
    createQueue(B,staff->queue);
    while(!emptyQueue(staff->queue))
    {
        clrscr();
        if(count == 4)
        {
            count = 0;
            char ans;
            clrscr();
            printf("\nOne more Customer Left!\nDo you want to bring in the next set of customers? [y] or [n]");
            scanf(" %c" , &ans);

            if(ans == 'y' || ans == 'Y')
            {
                createQueue(B,staff->queue);
            }

            else
            {
                return;
            }                
        }
        customer=dequeue(staff->queue);
        printf("\n\t\tCurrent customer : %s\n",customer->cName);
        if(verifyCustomer(customer) == 0)
        {   
            count++;
            continue;
        }
        else
        {
    	   choice =0;
        }
        clrscr();
    	while(choice != 5)
    	{
            clrscr();
	  		printf("\n\t\tCurrent customer : %s\n",customer->cName);
	  		printf("\n\t\t***********MENU***********\n");
	    	printf("\n1) View customer details\n2) Deposit money\n3) Withdraw money\n4) Transfer money \n5) Next customer\n6) View bank database\n7) Loan \n8) Exit to Main menu\nEnter choice: ");
    		scanf("%d",&choice);
    		switch(choice)
    		{
    			case 1:
    				customerDetails(customer);
                    printf("\n\nEnter any key to continue");
                    scanf(" %c", &c);
    				break;

    			case 2:
                    printf("******MONEY DEPOSIT******\n");
    				printf("\n\nEnter amount to deposit in Rs. :\n");
    				scanf("%f",&amount);
    				deposit(customer,amount);
                    printf("\n\nEnter any key to continue");
                    scanf(" %c", &c);
    				break;

    			case 3:
    				printf("\n\nEnter amount to Withdraw in Rs. :\n");
    				scanf("%f",&amount);
    				withdraw(customer,amount);
                    printf("\n\nEnter any key to continue");
                    scanf(" %c", &c);
    				break;

    			case 4:
    				printf("\n\nEnter recipient account number:");
    				scanf("%ld",&acc);
    				printf("\n\nEnter amount to tranfer :");
    				scanf("%f",&amount);
    				transfer(B,customer,acc,amount);
                    printf("\n\nEnter any key to continue");
                    scanf(" %c", &c);
    				break;

                case 5:
                    count++;
                    if(count < 5)
                        continue;
                    break;

                case 6:
                    clrscr();
                    printf("\n\nName\t\t\tAccount No\tEmailID\t\t\t\tAge\t\tBalance\n_______________________________________________________________________________________________________\n");
                    displayCustomer(B->ctree);
                    printf("\n\nEnter any key to continue");
                    scanf(" %c", &c);
                    break;

                case 7:
                    loan(B,customer);
                    printf("\n\nEnter any key to continue");
                    scanf(" %c", &c);
                    break;
                case 8:
                    return;
    		}
    	}
    }
}

void staffLogin(Bank *B)
{
    Staff *staff;
    staff = NULL;
    long sID;
    char sPassword[50];
    clrscr();
	printf("\n\n\t\t**********LOGIN*********\n\n");
	printf("Enter staff ID :\n");
	scanf("%d",&sID);
	printf("Enter Password :\n");
	scanf("%s",sPassword);
    staff = searchStaffAVL(B->stree,sID);

    if(staff != NULL)
    {
        if(strcmp(staff->sPassword,sPassword) == 0)
        {
            printf("\n\n\t\tLOGIN SUCCESSFULL!!\n\n");
            delay(3);
            staffMenu(B,staff);
        }
        else
        {
            delay(3);
            printf("\n\n\t\tINVALID USERNAME OR PASSWORD!\n\n");
        }
    }
    else
    {
        delay(3);
        printf("\n\n\t\tINVALID USERNAME OR PASSWORD!\n\n");
    }

}

void setData(Bank *B)
{
	Customer customer;
	Staff staff;
	B->ctree = NULL;
	B->stree = NULL;
	long sId[] = {1100,1101,1102,1103,1104,1105,1106,1107,1108,1109};
    char spass[][50] =  {"staff00","staff01","staff02","staff03","staff04","staff05","staff06","staff07","staff08","staff09"};
    int age[]={18,24,56,78,35,51,25,67,40,67,21,46,55,19,32};
    char cName[][50] = {"Tom James","Brent Morey","Shawn Calvin","Harr Steve","Dave Floyd","Milli Brown","Misato Ippo","Harry Potter","Simba Pim","Gordon Ramsay","Ant man    ","Hulk      ","Robert Patt","Scott Lang","Rupert Grint"}; 
    char cPass[][50] = {"TomJames","BrentMorey","ShawnCalvin","HarrSteve","DaveFloyd","MilliBrown","MisatoIppo","HarryPotter","SimbaPim","GordonRamsay","Antman","Hulk","RobertPatt","ScottLang","RupertGrint"}; 
    //long cId[] = {2222344,3345511,3311245,5566778,1100223,3344552,1546102,7712834,2435465,5613029}; 
    long cId[] = {185001100,185001101,185001102,185001103,185001104,185001105,185001106,185001107,185001108,185001109,185001110,185001111,185001112,185001113,185001114};
    char cAddress[][50] = {"tom_21@gmail.com","brent_morey@gmail.com","shawn_cal12@yahoo.com","kit__harr@gmail.com","dave_00@hotmail.com","millibrown@gmail.com","misato__@gmail.com","harry_potter@yahoo.com","simba2000@gmail.com","opaqueman6@gmail.com","antman@gmail.com","hulk200@gmail.com","robert1@gmail.com","scottlang@yahoo.com","rupertGrint@gmail.com"}; 
    char cpass[][50] = {"tom_21","brent_morey","shawn_cal12","kit__harrington","dave_00","millibrown","misato__","harry_potter","simba2000","opaqueman6","antman","hulk","robert","scott","rupert"};
    float cBalance[]={18000,34012,56999,1600000,4512320,5060609,9989992,25000,2778768,3100000,5000000,560000,20000000,790000,45000};
    int credit[]={100,200,300,400,500,600,700,800,900,100,200,300,400,500,600};

    for(int i=0;i<10;++i)
    {
    	strcpy(customer.cName,cName[i]);
    	customer.cId=cId[i];
    	strcpy(customer.cAddress,cAddress[i]);
    	customer.cId = cId[i];
    	customer.age=age[i];
    	customer.cBalance = cBalance[i];
        strcpy(customer.cPass,cPass[i]);
        customer.cId=cId[i];
        customer.credit=credit[i];
    	B->ctree = insertCustomer(B->ctree,customer);
    	staff.sID = sId[i];
    	strcpy(staff.sPassword,spass[i]);
    	B->stree = insertStaff(B->stree,staff);
    }

    for(int i=10;i<15;++i)
    {
        strcpy(customer.cName,cName[i]);
        customer.cId=cId[i];
        strcpy(customer.cAddress,cAddress[i]);
        customer.cId = cId[i];
        customer.age=age[i];
        customer.cBalance = cBalance[i];
        strcpy(customer.cPass,cPass[i]);
        customer.cId=cId[i];
        customer.credit=credit[i];
        B->ctree = insertCustomer(B->ctree,customer);
    }
}