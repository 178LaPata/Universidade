% Parte I

soma(X, Y, Z, R):- R is (X+Y+Z).

soma2([], 0).
soma2([H|T], R):- soma2(T,P), R is (P+H).

maior(X, Y, R):- X>Y -> R is X; R is Y.

maior2([H], H).
maior2([H|T], R):- maior(T,P), P>H -> R is P; R is H.

tamanho([],0).
tamanho([H|T], R):- tamanho(T,P), R is (P+1).

media([], 0).
media(L,R):- soma2(L,P), tamanho(L,C), R is (P/C).

insere([],F,[F]).
insere([H|T], F, R):- F<H -> R = (F|[H|T]); (insere(T,F,L), R = (H|L)).

ordena([],[])
ordena([H|T],L):- ordena(T,P), insere(P,H,L).

pares(X,R):- X mod 2 =:= 0 -> R is 'true', R is 'false'.

% Parte II

pertence(A,[], 'false')
pertence(A,[H|T],R):- A == H, R = 'true'; pertence(A,T,R).

comprimento([],0).
comprimento([H|T], R):- comprimento(T,P), R is (P+1).

diferentes([], 0).
diferentes(L,R):- diferentes2(L,P), tamanho(P,K), R is K.

diferentes2([],[]).
diferentes2((H|T),L):- diferentes2(T,R), (pertence(H,R,'false') -> L = [H|R]; L = R).

apaga1(E,[],[]).
apaga1(E,[H|T],R) :- (E==H -> R=T; apaga1(E,T,R1),R=[H|R1]).

apagaT(E,[],[]).
apagaT(E,[H|T],R) :- (E==H -> apagaT(E,T,R1), R=R1; apagaT(E,T,R2),R=[H|R2]).

adicionar(E,[],[E]).
adicionar(E,[H|T],R) :- pertence(E,[H|T],'false') -> R=[E|[H|T]]; R=[H|T].

concatenar([],[],[]).
concatenar([],L,L).
concatenar(L,[],L).
concatenar([H|T],L,R) :- concatenar(T,L,R1), R=[H|R1].

inverter([],[]).
inverter([H|T],R) :- inverter(T,R1), concatenar(R1,[H],R).

sublista([],[],'true').
sublista([],L,'true').
sublista(L,[],'false').
sublista([H|T],[A|B],R) :- (H==A -> sublista(T,B,R), R='true'; sublista([H|T],B,R)).