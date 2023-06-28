module Ficha2 where

import Data.Char

-- 1 

--a
funA:: [Double] -> Double
funA [] = 0
funA (y:ys) = y^2 + (funA ys)

{-
funA [1,2,3,4]
1^2 + funA[2,3,4]
1^2 + 2^2 + funA [3,4]
1^2 + 2^2 + 3^2 + funA [4]
1^2 + 2^2 + 3^2 + 4^2 + funA[] 
1^2 + 2^2 + 3^2 + 4^2 + 0 
1 + 4 +9 +16
 -}

--b
funB :: [Int] -> [Int]
funB [] = []
funB (h:t) = if (mod h 2) == 0 
             then h : (funB t)
             else (funB t)

{-
funB [8,5,12]
8: funB [5,12]
8: funB [12]
8: 12: funB []
8: 12: []
[8,12]
-}

--c
funC (x:y:t) = funC t
funC [x] = []
funC [] = []

{-
funC [1,2,3,4,5]
funC (1:2:[3,4,5])
funC [3,4,5]
funC (3:4:[5])
funC [5]
funC []
[]
-}

--d
funD l = g [] l
g l [] = l
g l (h:t) = g (h:l) t

{-
funD "otrec" = g [] "otrec" = g [] 'o':"trec" = g 'o':[] "trec" = g ['o'] 't':"rec" =
    = g 't':['o'] "rec" = g "to" 'r':"ec" = g 'r':"to" "ec" = g "rto" 'e':['c'] = 
    = g 'e':"rto" ['c'] = g "erto" 'c':[] = g 'c':"erto" [] = "certo"
-}

--2

--a
dobros' :: [Float] -> [Float]
dobros' [] = []
dobros' (h:t) = 2*h : dobros' t

--b
numOcorre' :: Char -> String -> Int
numOcorre' x [] = 0
numOcorre' x (h:t) 
                | x == h = 1 + numOcorre' x t
                | otherwise = numOcorre' x t

--c
positivos' :: [Int] -> Bool
positivos' [] = True
positivos' (h:t)  
            | h>0 = positivos' t
            | otherwise = False

--d
soPos' :: [Int] -> [Int] 
soPos' [] = []
soPos' (h:t) 
        | h<0 = soPos' t
        | otherwise = h : soPos' t

--e
somaNeg' :: [Int] -> Int
somaNeg' [] = 0
somaNeg' (h:t)
            | h<0 = 1 + somaNeg' t
            | otherwise = somaNeg' t

--f
tresUlt' :: [a] -> [a]
tresUlt' [] = []
tresUlt' [x] = [x]
tresUlt' [x,y] = [x,y]
tresUlt' [x,y,z] = [x,y,z]
tresUlt' (h:t) = tresUlt' t

--g
segundos' :: [(a,b)] -> [b]
segundos' [] = []
segundos' ((a,b):t) = b : segundos' t

--h
nosPrimeiros' :: (Eq a) => a -> [(a,b)] -> Bool
nosPrimeiros' x [] = False
nosPrimeiros' x ((a,b):t) 
                | x == a = True
                | otherwise = nosPrimeiros' x t

--i
sumTriplos' :: (Num a, Num b, Num c) => [(a,b,c)] -> (a,b,c)
sumTriplos' [(a,b,c)] = (a,b,c)
sumTriplos' ((a,b,c):t) = (a + sumA, b + sumB, c + sumC)
        where (sumA, sumB, sumC) = sumTriplos' t

--3

--a
soDigitos :: [Char] -> [Char]
soDigitos [] = []
soDigitos (a:b) = if elem (ord a) [48..57] then a:soDigitos b else soDigitos b

--b
minusculas :: [Char] -> Int
minusculas [] = 0
minusculas (a:b) = if elem (ord a) [97..122] then 1 + minusculas b else minusculas b

--c
nums :: String -> [Int]
nums [] = []
nums (a:b) = if elem (ord a) [48..57] then (digitToInt a):nums b else nums b

--4
type Polinomio = [Monomio]
type Monomio = (Float,Int)

--a
conta :: Int -> Polinomio -> Int
conta n [] = 0
conta n ((c,e):t) 
            | e == n = 1 + conta n t 
            | otherwise = conta n t

--b
grau :: Polinomio -> Int
grau [(c,e)] = e
grau ((c,e):t) = max e (grau t)

--c
selgrau :: Int -> Polinomio -> Polinomio
selgrau n [] = []
selgrau n ((a,b):t) 
        | n == b = (a,b) : selgrau n t
        | otherwise = selgrau n t

--d
deriv :: Polinomio -> Polinomio
deriv [] = []
deriv ((a,b):t)
            | a>0 = ((a * fromIntegral b),(b-1)) : deriv t
            | otherwise = deriv t

--e
calcula :: Float -> Polinomio -> Float
calcula x [] = 0
calcula x ((c,e):t) = c*x^e + calcula x t

--f
simp :: Polinomio -> Polinomio
simp [(a,b)]
        | b < 1 = [] 
        | otherwise = [(a,b)]
simp ((a,b):t) 
        | b < 1 = simp t 
        | otherwise = (a,b) : simp t

--g
mult :: Monomio -> Polinomio -> Polinomio
mult _ [] = []
mult (x,y) ((a,b):t) = (a*x,b+y):(mult (a,b) t)

--h
normaliza :: Polinomio -> Polinomio
normaliza [] = []
normaliza ((c,e):p) =
  let p1 = selgrau e ((c,e):p)
      c1 = somaCoef p1
      p2 = semGrau e p
  in (c1,e) : normaliza p2

somaCoef :: Polinomio -> Float
somaCoef [] = 0
somaCoef ((c,e):p) = c + somaCoef p

semGrau :: Int -> Polinomio -> Polinomio
semGrau g [] = []
semGrau g ((c,e):p)
  | g/=e = (c,e): semGrau g p
  | otherwise = semGrau g p

--se o polinomio tiver ordenado

normaliza' :: Polinomio-> Polinomio
normaliza' ((c1,e1):(c2,e2):p)
  | e1/=e2 = (c1,e1) : normaliza' ((c2,e2):p)
  | e1==e2 = normaliza' ((c1+c2,e1):p)
normaliza' p = p

--i
soma :: Polinomio -> Polinomio -> Polinomio
soma p1 p2 = normaliza (p1 ++ p2)

-- polinomios normalizados e ordenados

soma' :: Polinomio -> Polinomio -> Polinomio
soma' [] p = p 
soma' p [] = p
soma' ((c1,e1):p1) ((c2,e2):p2) 
  | e1 < e2 = (c1,e1) : soma' p1 ((c2,e2):p2)
  | e1 == e2 = if c1+c2 /=0 
               then (c1+c2,e1) : soma' p1 p2
               else soma' p1 p2
  | e1 > e2 = (c2,e2) : soma' ((c1,e1):p1) p2
 
--j
produto :: Polinomio -> Polinomio -> Polinomio
produto _ [] = []
produto [] _ = []
produto (m:p1) p2 = (mult m p2) ++ produto p1 p2

produtoF :: Polinomio -> Polinomio -> Polinomio
produtoF p1 p2 = normaliza (produto p1 p2)

--k
ordena :: Polinomio -> Polinomio
ordena [] = []
ordena (m:p) = insere m (ordena p)
 where 
  insere :: Monomio -> Polinomio -> Polinomio
  insere (c,e) [] = [(c,e)]
  insere (c,e) ((c1,e1):p)
    | e>e1 = (c1,e1): insere (c,e) p
    | e==e1 = (c+c1, e):p
    | e<e1 = (c,e):(c1,e1):p

--l
equiv :: Polinomio -> Polinomio -> Bool
equiv p1 p2 = ordena (normaliza p1) == ordena (normaliza p2)
