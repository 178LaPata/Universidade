
filho(joao, jose).
filho(jose, manel).
filho(carlos, jose).

pai(paulo, filipe).
pai(paulo, maria).

avo(antonio, nadia).

neto(nuno, ana).

sexo(joao, masculino).
sexo(jose, masculino).
sexo(maria, feminino).
sexo(joana, feminino).

pai(P,F) :- filho(F,P).
avo(A,N) :- filho(N,X),pai(A,X).
neto(N,A) :- avo(A,N).

descende(X,Y) :- pai(Y,X),avo(Y,X),filho(X,Y),neto(X,Y).

grau(X,Y,1) :- filho(X,Y).
grau(X,Y,G) :- filho(X,Z),grau(Z,Y,G1), G is G1+1.