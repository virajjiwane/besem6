#include "cuda_runtime.h"
#include "device_launch_parameters.h"

#include <stdio.h>
#include<math.h>
#include<iostream>
using namespace std;

__global__ void sum(float* input)
{
int tid = threadIdx.x;
int step_size = 1;
int number_of_threads = blockDim.x;
float aux_size = (float)number_of_threads;

while (number_of_threads > 0)
{
if (tid < number_of_threads)
{
int fst = tid*step_size * 2;
int snd = fst + step_size;
/*if (input[fst] > input[snd] && input[snd] > 0)
input[fst] = input[snd];*/
input[fst] = input[fst] + input[snd];

}
step_size = step_size*2;
if (number_of_threads != 1)
{
aux_size = aux_size / 2;
number_of_threads = (int)ceil(aux_size);
}
else
number_of_threads = 0;
}
}

int main(int argc, char const* argv[])
{
int count = 10;
float size = count * sizeof(float);
float  h[10];
//srand(100);
cout << "AARAY : " <<endl;
for (int i = 0; i < count; i++)
{
cout << i << "th element" << endl;
cin >> h[i]; //rand() % count;

}
cout << "AARAY : " << endl;
for (int i = 0; i < count; i++)
{
cout << h[i] << " ";
}

float* d;
cudaMalloc(&d, size);
cudaMemcpy(d, h, size, cudaMemcpyHostToDevice);
sum << <1, (count / 2) + 1 >> > (d);
float result;
cudaMemcpy(&result, d, sizeof(float), cudaMemcpyDeviceToHost);
cout << "SUM :" << (float)result/(float)count << endl;
float mean = (float)result / (float)count;
for (int i = 0; i < count; i++)
{
h[i] = (h[i] - mean) * (h[i] - mean);
cout << h[i] << " ";
}
float* f;
cudaMalloc(&f, size);
cudaMemcpy(f, h, size, cudaMemcpyHostToDevice);
sum << <1, (count / 2) + 1 >> > (f);
float resultfil;
cudaMemcpy(&resultfil, f, sizeof(float), cudaMemcpyDeviceToHost);
cout << "SD :" <<sqrt(resultfil)<< endl;

getchar();
cudaFree(d);
cudaFree(f);
return 0;
}
