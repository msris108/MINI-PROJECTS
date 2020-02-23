//avl tree

typedef struct
{
	long cId;
	int age;
	char cName[100];
	char cAddress[100];
	float cBalance;
	char cPass[100];
	int credit;
}Customer;

typedef struct mycustomerAVLtree{
	Customer data;
	struct mycustomerAVLtree *right, *left;
	int height;
}customerAVLtree;

customerAVLtree *initC(customerAVLtree *t){
	t = malloc(sizeof(customerAVLtree));
	t->height = 0;
	t->left = NULL;
	t->right = NULL;
	return t;
}

int heightC(customerAVLtree *t){
	if(t == NULL)
	{
		return -1;
	}
	else
		return t->height;
}

int maxC(int a, int b){
	if(a>b)
		return a;
	else
		return b;
}

customerAVLtree *singleRotateLeftC(customerAVLtree *t1){
	customerAVLtree *t2;
	t2 = t1->left;
	t1->left = t2->right;
	t2->right = t1;

	t1->height = maxC(heightC(t1->left),heightC(t1->right));
	t2->height = maxC(heightC(t2->left),heightC(t2->right));

	return t2;
}

customerAVLtree *singleRotateRightC(customerAVLtree *t1){
	customerAVLtree *t2;
	t2 = t1->right;
	t1->right = t2->left;
	t2->left = t1;

	t1->height = maxC(heightC(t1->left),heightC(t1->right));
	t2->height = maxC(heightC(t2->left),heightC(t2->right));

	return t2;
}

customerAVLtree *doubleRotateLeftC(customerAVLtree *t){

	t->left = singleRotateRightC(t->left);

	return singleRotateLeftC(t);
}

customerAVLtree *doubleRotateRightC(customerAVLtree *t){

	t->right = singleRotateLeftC(t->right);

	return singleRotateRightC(t);

}

customerAVLtree* insertCustomer(customerAVLtree *t, Customer x){

	if (t== NULL)
	{
		t = initC(t);
		t->data = x;
	}
	else if (t->data.cId > x.cId)
	{
		t->left = insertCustomer(t->left,x);
		int hdiff = abs(heightC(t->left) - heightC(t->right));
		if(hdiff == 2){
			if(t->left->data.cId > x.cId)
				t = singleRotateLeftC(t);
			else
				t = doubleRotateRightC(t);
		}
	}
	else if(t->data.cId < x.cId){
		t->right = insertCustomer(t->right,x);
		int hdiff = abs(heightC(t->left) - heightC(t->right));
		//printf("%d\n",hdiff );
		if(hdiff == 2){
			if(t->right->data.cId < x.cId)
				t = singleRotateRightC(t);
			else
				t = doubleRotateRightC(t);
		}
	}
	t->height = maxC(heightC(t->left),heightC(t->right))+1;
	return t;
}

void displayCustomer(customerAVLtree *t)
{
	
	if(t==NULL)
		return;
	displayCustomer(t->left);
	printf("%s\t\t%ld\t%s\t\t%d\t\t%.4f\n",t->data.cName,t->data.cId,t->data.cAddress,t->data.age,t->data.cBalance);
	displayCustomer(t->right);

}

Customer* searchCustomerAVL(customerAVLtree *t, long cID)
{

	if (t== NULL)
	{
		return NULL;
	}
	else if(t->data.cId == cID)
	{
		return &t->data;
	}
	else if (t->data.cId > cID)
	{
		return	searchCustomerAVL(t->left,cID);	
	}
	else if(t->data.cId < cID){
		return searchCustomerAVL(t->right,cID);
	}

}
