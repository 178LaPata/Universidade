module Ficha4 where 

import Data.Char
import Data.List


{-
--1
(a) [x | x <- [1..20], mod x 2 == 0, mod x 3 == 0]
retorna uma lista com todos os valores de x entre 1 e 20 que são divísveis por 2 E divisíveis por 3


(b) [x | x <- [y | y <- [1..20], mod y 2 == 0] , mod x 3 == 0]

retorna uma lista x que verifica dentro de uma lista de numeros pares de 0 a 20 , 
aqueles que são divisíveis por 3


(c) [(x,y) | x <- [0..20], y <- [0..20], x+y == 30]
retorna tuplos de valores x e y de valores entre 0 e 20 em que a sua soma é == 30

                   lista de impares até x              
(d) [ sum  [ y |  y <- [1..x] , odd y] | x <- [1..10]]

[1,1,4,4,9,9,16,16,25,25]
retorna a soma dos elementos impares de 1 a x para todo o x de 1 a 10

--2
a) [1,2,4,8,16,32,64,128,256,512,1024]

   [2^x | x <- [1..10]]


b) [(1,5),(2,4),(3,3),(4,2),(5,1)]

   [(x,6-x) | x <- [1..5]]


c) [[1],[1,2],[1,2,3],[1,2,3,4],[1,2,3,4,5]]

   [take x [1..5] | x <- [1..5]]
   [ [1..x] | x <- [1..5]]


d) [[1],[1,1],[1,1,1],[1,1,1,1],[1,1,1,1,1]]

   [take x [1,1,1,1,1] | x <- [1,5]]
   [take x [1,1..] | x <- [1,5]]
   [replicate x 1 | x <- [1..5]]


e) [1 , 2, 6, 24, 120, 720]    
   [ product [1..x] | x <- [1..6] ]
-}

--3
digitAlpha :: String -> (String,String)
digitAlpha [] = ([],[])
digitAlpha (x:xs) =
    let (ll,ld)= digitAlpha xs
    in if isDigit x then (ll,x:ld)
        else if isAlpha x then (x:ll,ld) 
            else (ll,ld)

--ou 

digitAlpha' :: String -> (String,String)
digitAlpha' s = separa s ([],[])
 where separa :: String -> (String,String) -> (String,String)
       separa [] (ll,ld) = (ll,ld)
       separa (x:xs) (ll,ld)
         | isDigit x = separa xs (ll,ld++[x])
         | isAlpha x = separa xs (ll++[x],ld)
         | otherwise = separa xs (ll,ld)

--4
nzp :: [Int] -> (Int,Int,Int)
nzp [] = (0,0,0)
nzp (x:xs)
    | x<0 = (nn+1, nz,np)
    | x==0 = (nn,nz+1,np)
    | x>0 = (nn,nz,np+1)
   where (nn,nz,np)= nzp xs

--ou

nzp' ::  [Int] -> (Int,Int,Int)
nzp' l = conta l (0,0,0)
    where conta :: [Int] -> (Int,Int,Int) -> (Int,Int,Int)
          conta [] (nn,nz,np) = (nn,nz,np)
          conta (x:xs) (nn,nz,np) 
                  | x<0 = conta xs (nn+1, nz,np)
                  | x==0 = conta xs (nn,nz+1,np)
                  | x>0 = conta xs (nn,nz,np+1)

--5
divMod2 :: Integral a => a -> a -> (a, a)
divMod2 a b = (div a b, mod a b)

--6
fromDigits :: [Int] -> Int
fromDigits [] = 0
fromDigits (x:xs) = calcula (x:xs) (length xs)
    where   calcula [x] _ = x
            calcula (y:ys) k = y*10^k + calcula ys (k-1)   

-- ou

fromDigits' :: [Int] -> Int
fromDigits' l = conv (reverse l)
    where   conv :: [Int] -> Int
            conv [] = 0
            conv (x:xs) = x + 10*(conv xs)

-- acumuladores
fromDigits2 :: [Int] -> Int
fromDigits2 l = conv l 0
    where   conv [] s = s
            conv (x:xs) s = conv xs (x+10*s)

--7
maxSumInit :: (Num a, Ord a) => [a] -> a
maxSumInit l = aux l 0 0
  where aux :: (Num a, Ord a) => [a] -> a -> a -> a
        aux [] m s = m
        aux (h:t) m s 
           |(s+h) > m = aux t (s+h) (s+h)
           |otherwise = aux t m (s+h)   

--8
fib :: Int -> Int
fib 0 = 0
fib n = fibAux n 0 1
  where fibAux 0 v1 v2 = v1
        fibAux 1 v1 v2 = v2
        fibAux n v1 v2 = fibAux (n-1) v2 (v1+v2)