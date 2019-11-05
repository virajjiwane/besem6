#include<stdio.h>
#include<stdlib.h>
#include<mpi.h>

int main(){
	int arr[] = {1,3,6,2,9,7};
	MPI_Init(NULL,NULL);
	int world_size;
	MPI_Comm_size(MPI_COMM_WORLD,&world_size);
	int world_rank;
	MPI_Comm_rank(MPI_COMM_WORLD,&world_rank);

	if(world_size<2){
		printf("World size less than 2");
		MPI_Abort(MPI_COMM_WORLD,1);
	}
	float local_sum;
	float sum;
	float min;
	float max;
	local_sum = (float)arr[world_rank];
	MPI_Reduce(&local_sum,&sum,1,MPI_FLOAT,MPI_SUM,0,MPI_COMM_WORLD);
	MPI_Reduce(&local_sum,&min,1,MPI_FLOAT,MPI_MIN,0,MPI_COMM_WORLD);
	MPI_Reduce(&local_sum,&max,1,MPI_FLOAT,MPI_MAX,0,MPI_COMM_WORLD);
	if(world_rank==0){
		printf("SUM: %f\n",sum);
		printf("MIN: %f\n",min);
		printf("MAX: %f\n",max);
	}
	MPI_Finalize();
	return 0;
}
