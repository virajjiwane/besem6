#include<iostream>
#include<algorithm>
#include<stdlib.h>
#include<time.h>
#include<omp.h>

using namespace std;

int binarysearch(int *a, int low, int high, int* keys, int k){
	if(low>high)return -1;
	int mid = (low+high)/2;
	if(a[mid]==keys[k])return mid;
	if(a[mid]<keys[k])return binarysearch(a,mid+1,high,keys,k);
	else return binarysearch(a,0,mid-1,keys,k);
}

void up_binary(int * a,int low, int high, int * keys,int k){
	clock_t up_time = clock();
	for(int i=0;i<k;i++){
		cout<<"Key: "<<keys[i]<<", Position: "<<binarysearch(a,low,high,keys,i)<<"\n";
	}
	cout<<"Total time: "<<clock()-up_time<<"\n";
}

void p_binary(int * a,int low, int high, int * keys,int k){
	clock_t up_time = clock();
	#pragma omp parallel for default(none), shared(a,low,high,keys,k)
	for(int i=0;i<k;i++){
		cout<<"Key: "<<keys[i]<<", Position: "<<binarysearch(a,low,high,keys,i)<<"\n";
	}
	cout<<"Total time: "<<clock()-up_time<<"\n";
}

int main(){
	int n;
	cout<<"Enter size of array: ";
	cin>>n;
	int a[n];
	for(int i=0;i<n;i++){
		a[i]=rand()%n;
	}
	sort(a,a+n);
	cout<<"Enter no of keys: ";
	int k;
	cin>>k;
	int keys[k];
	cout<<"Enter keys: ";
	for(int i=0;i<k;i++){
		cin>>keys[i];
	}
	up_binary(a,0,n-1,keys,k);
	p_binary(a,0,n-1,keys,k);
}
