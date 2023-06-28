
aluno(1,joao,m).
aluno(2,antonio,m).
aluno(3,carlos,m).
aluno(4,luisa,f).
aluno(5,maria,f).
aluno(6,isabel,f).

curso(1,lei).
curso(2,miei).
curso(3,lcc).

%disciplina(cod,sigla,ano,curso)
disciplina(1,ed,2,1).
disciplina(2,ia,3,1).
disciplina(3,fp,1,2).

%inscrito(aluno,disciplina)
inscrito(1,1).
inscrito(1,2).
inscrito(5,3).
inscrito(5,5).
inscrito(2,5).

%nota(aluno,disciplina,nota)
nota(1,1,15).
nota(1,2,16).
nota(1,5,20).
nota(2,5,10).
nota(3,5,8).

%copia
copia(1,2).
copia(2,3).
copia(3,4).

% 1
alunoNaoInscrito(ID):- aluno(ID,_,_),not(inscrito(ID,_)).
naoInscrito(L):- setof(ID,alunoNaoInscrito(ID),L).

% 2

disciplinaExiste(D):- findall(ID,disciplina(ID,_,_,_),D).

pertence(A,[], 'false')
pertence(A,[H|T],R):- A == H, R = 'true'; pertence(A,T,R).

alunoNaoInscrito2(ID):- aluno(ID,_,_), disciplinaExiste(D), pertence(ID,D,'false').

naoInscrito2(L):- setof(ID,alunoNaoInscrito2(ID),L).

% 3

media(ID,M):- findall(N,nota(ID,_,N),L), sumlist(L,R), length(R,G), M is R/G.

% 4

mediaGeral(M):- findall(N,nota(_,_,N),L), sumlist(L,R), length(R,G), M is R/G.

mediaAcima(M):- setof(ID,(aluno(ID,_,_), nota(ID,_,_), media(ID,M1), mediaGeral(M2), M1>M2),M).
 
% 5

copiar(M):- findall(N, aluno(ID,N,_), copia(ID,_), M).

% 6

malandroDir(ID,R):- findall(IDF,(aluno(IDF,_,_),copia(IDF,ID)),R).
malandroEsq(ID,R):- findall(IDF,(aluno(IDF,_,_),copia(ID,IDF)),R).

malandros(ID,R):- malandroEsq(ID,R1),malandroDir(ID,R2), append(R1, R2, R3), setof(X, member(X,R3), R).

% 7

existeAluno(Aluno) :- aluno(Aluno,_,_). 

mapToNome([],[]).
mapToNome([H|T],R):- mapToNome(T,R1), (existeAluno(H) -> aluno(H,N,_), R = [N|R1]; R=R1).