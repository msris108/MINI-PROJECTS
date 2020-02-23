
#include"CustomerAVLtree.h"

typedef struct myheap
{
	int capacity;
	int size;
	Customer *customer[50];
}priorityQueueADT;

priorityQueueADT *init(priorityQueueADT *pq){
	pq->capacity=100;
	pq->size=0;
	return pq;
}

int emptyQueue(priorityQueueADT *pq)
{
	if(pq->size==0)
		return 1;
	return 0;
}

void enqueue(priorityQueueADT *pq, Customer *customer)
{
	Customer *temp;
	if(pq->size==pq->capacity){
		printf("\nQueue is full!");
		return;
	}
	else
	{
		pq->customer[pq->size+1] = customer;
		for(int i=pq->size+1;i>1;i/=2){
			if(pq->customer[i]->age > pq->customer[i/2]->age){
				temp=pq->customer[i];
				pq->customer[i]=pq->customer[i/2];
				pq->customer[i/2]=temp;
			}
		}
		pq->size++;
	}
}

void displayQueue(priorityQueueADT *pq,int index, int depth)
{
	if(pq->size==0){
		printf("\nQueue is empty!");
		return;
	}
	for(int i=0;i<depth;++i)
	{
		printf("\t");
	}
	printf("%ld\n",pq->customer[index]->cId);
	if(index*2<=pq->size)
	{
		displayQueue(pq,index*2,depth+1);
	}
	if(index*2+1<=pq->size)
	{
		displayQueue(pq,index*2+1,depth+1);
	}
}

Customer *dequeue(priorityQueueADT *pq){
	Customer *first;
	if(pq->size==0)
	{
		printf("\nQueue is empty!");
		first= NULL;
	}
	else
	{
		int i,child;
		Customer *temp;
		first=pq->customer[1];
		pq->customer[1]=pq->customer[pq->size--];
		for(i=1;i*2<=pq->size;i=child){
			child=i*2;
			if(child!=pq->size && (pq->customer[child+1] > pq->customer[child]))
			{
				child++;
			}
			if(pq->customer[i]->age<pq->customer[child]->age){
				temp=pq->customer[i];
				pq->customer[i]=pq->customer[child];
				pq->customer[child]=temp;
			}
			else
				break;
		}
	}

	return first;
}
