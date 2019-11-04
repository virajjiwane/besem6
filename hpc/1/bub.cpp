#include<iostream>
#include<omp.h>
#include<stdio.h>
#include<time.h>
#include <stdlib.h>

using namespace std;

void up(int* a,int size){
	for(int i=0;i<size;i++){
		int first = i%2;
		for(int j=first;j<size-1;j++){
			if(a[j]>a[j+1]){
				int t = a[j];
				a[j] = a[j+1];
				a[j+1] = t;
			}
		}
	}
	
}

void p(int* a, int size){
	for(int i=0;i<size;i++){
		int first = i%2;
		#pragma omp parallel for default(none), shared(a, first, size)
		for(int j=first;j<size-1;j++){
			if(a[j]>a[j+1]){
				int t = a[j];
				a[j] = a[j+1];
				a[j+1] = t;
			}
		}
	}
		
}

int main(){
	int size=0;
	cout<<"Enter size: ";
	cin>>size;
	int a[size];
	int b[size];
	for(int i=0;i<size;i++){
		a[i] = rand()%size;
		b[i]=a[i];
	}
	clock_t utime = clock();
	up(a,size);
	cout<<"Non parallel time : "<<clock()-utime;
	clock_t ptime = clock();
	p(b,size);
	cout<<"Parallel time : "<<clock()-ptime;
	return 0;
}
