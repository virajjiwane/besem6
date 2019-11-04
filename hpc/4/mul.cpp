#include<iostream>
#include<time.h>
#include<omp.h>

using namespace std;

void vecMul(int s){
	int vec[s];
	int mat[s][s];
	for(int i=0;i<s;i++){
		vec[i] = rand()%s;
		for(int j=0;j<s;j++){
			mat[i][j] = rand()%s;
		}
	}
	int res[s] = {0};
	#pragma omp parallel
	{
		int private_res[s]={0};
		#pragma omp for
		for(int i=0;i<s;i++){
			for(int j=0;j<s;j++){
				private_res[i] += vec[i]*mat[i][j];
			}
		}
		#pragma omp critical
		{
			for(int i=0;i<s;i++){
				res[i]+=private_res[i];
			}
		}
	}
}

void matMul(int s){
	int mat1[s][s];
	int mat2[s][s];
	int res[s][s];
	for(int i=0;i<s;i++){
		for(int j=0;j<s;j++){
			mat1[i][j] = rand()%s;
			mat2[i][j] = rand()%s;
			res[i][j] = 0;
		}
	}
	#pragma omp parallel for
	for(int i=0;i<s;i++){
		for(int j=0;j<s;j++){
			for(int k=0;k<s;k++){
				res[i][j] += mat1[i][k] * mat2[k][j];
			}
		}
	}
}

int main(){
	int s = 0;
	cout<<"Enter dimension: ";
	cin>>s;
	clock_t vec = clock();
	vecMul(s);	
	cout<<"Vec time: "<<float(clock()-vec)/CLOCKS_PER_SEC*1000<<endl;
	clock_t mat = clock();
	matMul(s);	
	cout<<"Mat time: "<<float(clock()-mat)/CLOCKS_PER_SEC*1000<<endl;
	return 0;
}
