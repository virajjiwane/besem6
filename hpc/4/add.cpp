#include<iostream>
#include<stdlib.h>
#include<omp.h>
#include<fstream>
#include<time.h>

using namespace std;

void add_p(int n){
	ofstream res("res_p.txt");
	ifstream file1("v1.txt");
	ifstream file2("v2.txt");
	int x,y,z;
	#pragma omp parallel for
	for(int i=0;i<n;i++){
		file1>>x;
		file2>>y;
		z = x+y;
		res<<z<<endl;
	}
	file1.close();
	file2.close();
	res.close();
}

void add_s(int n){
	ofstream res("res_s.txt");
	ifstream file1("v1.txt");
	ifstream file2("v2.txt");
	int x,y,z;
	for(int i=0;i<n;i++){
		file1>>x;
		file2>>y;
		z = x+y;
		res<<z<<endl;
	}
	file1.close();
	file2.close();
	res.close();
}

int main(){
	int n;
	cout<<"Enter size: ";
	cin>>n;
	ofstream file1("v1.txt");
	for(int i=0;i<n;i++){
		file1<<rand()%n+1<<endl;
	}
	file1.close();
	ofstream file2("v2.txt");
	for(int i=0;i<n;i++){
		file2<<rand()%n+1<<endl;
	}
	file2.close();
	clock_t p_time = clock();
	add_p(n);
	cout<<"P : "<<float(clock()-p_time)/CLOCKS_PER_SEC*1000;
	clock_t s_time = clock();
	add_s(n);
	cout<<endl<<"S : "<<float(clock()-s_time)/CLOCKS_PER_SEC*1000;
	return 0;
}
