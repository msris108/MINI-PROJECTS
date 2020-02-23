//avl tree

#include"priorityQueue.h"

typedef struct
{
	long sID;
	char sPassword[100];
	priorityQueueADT *queue;
}Staff;

typedef struct mystaffAVLtree{
	Staff data;
	struct mystaffAVLtree *right, *left;
	int height;
}staffAVLtree;

staffAVLtree *initS(staffAVLtree *t){
	t = malloc(sizeof(staffAVLtree));
	t->height = 0;
	t->left = NULL;
	t->right = NULL;
	return t;
}

int heightS(staffAVLtree *t){
	if(t == NULL)
	{
		return -1;
	}
	else
		return t->height;
}

int maxS(int a, int b){
	if(a>b)
		return a;
	else
		return b;
}

staffAVLtree *singleRotateLeftS(staffAVLtree *t1){
	staffAVLtree *t2;
	t2 = t1->left;
	t1->left = t2->right;
	t2->right = t1;

	t1->height = maxS(heightS(t1->left),heightS(t1->right));
	t2->height = maxS(heightS(t2->left),heightS(t2->right));

	return t2;
}

staffAVLtree *singleRotateRightS(staffAVLtree *t1){
	staffAVLtree *t2;
	t2 = t1->right;
	t1->right = t2->left;
	t2->left = t1;

	t1->height = maxS(heightS(t1->left),heightS(t1->right));
	t2->height = maxS(heightS(t2->left),heightS(t2->right));

	return t2;
}

staffAVLtree *doubleRotateLeftS(staffAVLtree *t){

	t->left = singleRotateRightS(t->left);

	return singleRotateLeftS(t);
}

staffAVLtree *doubleRotateRightS(staffAVLtree *t){

	t->right = singleRotateLeftS(t->right);

	return singleRotateRightS(t);

}

staffAVLtree* insertStaff(staffAVLtree *t, Staff x){
//	printf("staff\n");
	if (t== NULL)
	{
		t = initS(t);
		t->data = x;
	}
	else if (t->data.sID > x.sID)
	{
		t->left = insertStaff(t->left,x);
		int hdiff = abs(heightS(t->left) - heightS(t->right));
		if(hdiff == 2){
			if(t->left->data.sID > x.sID)
				t = singleRotateLeftS(t);
			else
				t = doubleRotateRightS(t);
		}
	}
	else if(t->data.sID < x.sID){
		t->right = insertStaff(t->right,x);
		int hdiff = abs(heightS(t->left) - heightS(t->right));
		if(hdiff == 2){
			if(t->right->data.sID < x.sID)
				t = singleRotateRightS(t);
			else
				t = doubleRotateRightS(t);
		}
	}
	t->height = maxS(heightS(t->left),heightS(t->right))+1;
	return t;
}

void displayStaff(staffAVLtree *t){
	
	if(t==NULL)
		return;
	displayStaff(t->left);
	printf("%ld %s ",t->data.sID,t->data.sPassword);
	displayStaff(t->right);

}

Staff* searchStaffAVL(staffAVLtree *t, long sID)
{

	if (t== NULL)
	{
		return NULL;
	}
	else if(t->data.sID == sID)
	{
		return &t->data;
	}
	else if (t->data.sID > sID)
	{
		return	searchStaffAVL(t->left,sID);	
	}
	else if(t->data.sID < sID){
		return searchStaffAVL(t->right,sID);
	}

}
