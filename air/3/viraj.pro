domains
	Patient = string
	Disease, indication = symbol

predicates
	hypothesis(string, Disease)
	symptoms(string, indication)
	response(char)
	go
	
clauses
	go:-
		write("Enter name: "),
		readln(Patient),
		hypothesis(Patient,Disease),
		write(Patient," has ",Disease),nl.
	
	go:-
		write("Failed").
		
	hypothesis(Patient, chickenpox):-
		symptoms(Patient, fever),
		symptoms(Patient, spots).
		
	symptoms(Patient, fever):-
		write("Do you have fever?"),
		response(Reply),
		Reply = 'y'.
		
	symptoms(Patient, spots):-
		write("Do you have spots?"),
		response(Reply),
		Reply='y'.
		
	response(Reply):-
		readchar(Reply).