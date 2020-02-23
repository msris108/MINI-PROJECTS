#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<time.h>
#include <sys/time.h>

#include"StaffAVLtree.h"
#include"bankImplv1.h"

void main()
{
	Bank *B;
	B = malloc(sizeof(Bank));
	setData(B);
	long sID;
	char sPassword[50];
	int choice=0;
	while(choice != 2)
	{
		clrscr();
		printf("\n\n\t\t**********WELCOME*********\n\n");
		printf("Enter 1 to login or 2 to exit\n");
		scanf("%d",&choice);
		switch(choice)
		{
			case 1:
			staffLogin(B);
			break;

			default:
			printf("********Thank you*********");
			delay(3);
			clrscr();
			break;
		}
	}
}