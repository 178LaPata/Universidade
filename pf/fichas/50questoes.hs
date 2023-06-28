module Perguntas where

import Data.Char
import Data.List 


-- 1
enumFromTo' :: Int -> Int -> [Int]
enumFromTo' x y 
        | x == y = [x]
        | x > y = []
        | otherwise = x : enumFromTo' (x+1) y

--2
enumFromThenTo' :: Int -> Int -> Int -> [Int]
enumFromThenTo' x y z
        | x == z = [x]
        | x > z = []
        | otherwise = x : enumFromThenTo' (x+y) y z

-- 3
maisMais' :: [a] -> [a] -> [a]
maisMais' [] (x:y) = (x:y) -- caso mais especifico -> para parar recursividade
maisMais' (h:t) (x:y) = h : maisMais' t (x:y)

--4 
fodasse' :: [a] -> Int -> a
fodasse' (h:t) 0 = h
fodasse' (h:t) x = fodasse' t (x-1)

--5
reverse' :: [a] -> [a]
reverse' [] = []
reverse' (h:t) = reverse' t ++ [h]

--6
take' :: Int -> [a] -> [a]
take' 0 (h:t) = []
take' x [] = []
take' x (h:t) = h : take' (x-1) t

--7
drop' :: Int -> [a] -> [a]
drop' 0 (h:t) = (h:t)
drop' x [] = []
drop' x (h:t) = drop' (x-1) t  

--8
zip' :: [a] -> [b] -> [(a,b)]
zip' [] [] = [] -- não era necessário 
zip' a [] = [] 
zip' [] b = []
zip' (h:t) (x:y) = (h,x) : zip' t y  

--9
elem' :: Eq a => a -> [a] -> Bool
elem' a [] = False
elem' a (h:t) 
        | a == h = True
        | otherwise = elem' a t 

--10 
replicate' :: Int -> a -> [a]
replicate' 0 y = []
replicate' x y = y : replicate' (x-1) y 

--11
intersperse' :: a -> [a] -> [a]
intersperse' x [] = []
intersperse' x (h:t) = h : x : intersperse' x t

--12
group' :: Eq a => [a] -> [[a]]
group' [] = []
group' (h:t) = [juntargroup' (h:t)] ++ group' (removegroup' (h:t))

juntargroup' :: Eq a => [a] -> [a]
juntargroup' [] = []
juntargroup' [h] = [h]
juntargroup' (h:x:t) 
                | h == x = h : juntargroup' (x:t)
                | otherwise = juntargroup' (h:t)

removegroup' :: Eq a => [a] -> [a]
removegroup' [] = []
removegroup' [h] = []
removegroup' (h:x:t) 
                | h == x = removegroup' (x:t)
                | otherwise = x : removegroup' (h:t) 

--13  
concat' :: [[a]] -> [a]
concat' [] = []
concat' (h:t) = h ++ concat' t

{- minha resolução no teste 
concat'' :: [[a]] -> [a]
concat'' [[]] = []
concat'' (h:t) = h ++ concat'' t
-}

--14  
inits' :: [a] -> [[a]]
inits' [] = [[]]
inits' (h:t) = inits' (init' (h:t)) ++ [(h:t)]

init' :: [a] -> [a]
init' [] = []
init' [a] = []
init' (h:t) = h : (init' t)


--15 
tails' :: [a] -> [[a]]
tails' [] = [[]]
tails' (h:t) = (h:t) : tails' t

--16
isPrefixOf' :: Eq a => [a] -> [a] -> Bool
isPrefixOf' (h:t) [] = False -- _ = lista
isPrefixOf' [] (h:t) = True
isPrefixOf' (h:t) (x:y)
                | h == x = isPrefixOf' t y
                | otherwise = False

--17
isSuffixOf' :: Eq a => [a] -> [a] -> Bool 
isSuffixOf' [] (h:t) = True
isSuffixOf' (h:t) (x:y)
                | length (h:t) > length (x:y) = False
                | length (h:t) < length (x:y) = isSuffixOf' (h:t) y
                | h == x = isSuffixOf' t y
                | otherwise = False

--18
isSubsequenceOf' :: Eq a => [a] -> [a] -> Bool
isSubsequenceOf' [] (h:t) = True
isSubsequenceOf' (h:t) [] = False
isSubsequenceOf' (h:t) (x:y) 
                | h == x = isSubsequenceOf' t y
                | otherwise = isSubsequenceOf' (h:t) y

--19
--elemIndices' :: Eq a => a -> [a] -> [Int]
--elemIndices' x [] = []
--elemIndices' x (h:t) 
--                | x == h = h : elemIndices' x t
--                | otherwise = elemIndices' x t 


myElemIndices :: Eq a => a -> [a] -> [Int]
myElemIndices coiso (h:t) = myElemIndicesAux coiso (h:t) 0

myElemIndicesAux :: Eq a => a -> [a] -> Int -> [Int]
myElemIndicesAux coiso [] contador = []
myElemIndicesAux coiso (h:t) contador | coiso == h = [contador] ++ (myElemIndicesAux coiso t (contador + 1))
                                      | otherwise = myElemIndicesAux coiso t (contador + 1)

--20
nub' :: Eq a => [a] -> [a]
nub' [] = []
nub' (h:t) = [h] ++ (nub' (nubAux' h t))

nubAux' :: Eq a => a -> [a] -> [a]
nubAux' x [] = []
nubAux' x (h:t) 
            | x == h = nubAux' x t
            | otherwise = [h] ++ nubAux' x t

--21
delete' :: Eq a => a -> [a] -> [a]
delete' x [] = []
delete' x (h:t) 
            | x == h = t
            | otherwise = h : delete' x t 

--22
barraBarra' :: Eq a => [a] -> [a] -> [a]
barraBarra' (h:t) [] = (h:t)
barraBarra' [] (h:t) = []
barraBarra' (h:t) (x:y)
                | h == x = barraBarra' t y
                | otherwise = h : barraBarra' t (x:y)

--23
union' :: Eq a => [a] -> [a] -> [a]
union' (h:t) [] = (h:t)
union' [] (h:t) = (h:t)
union' (h:t) (x:y)
            | elem x (h:t) = union' (h:t) y
            | otherwise = union' ((h:t) ++ [x]) y

--24
intersect' :: Eq a => [a] -> [a] -> [a]
intersect' (h:t) [] = []
intersect' [] (h:t) = []
intersect' (h:t) (x:y)
                | h == x = h : intersect' t y
                | otherwise = intersect' t y 

--25
insert' :: Ord a => a -> [a] -> [a]
insert' x [] = [x]
insert' x (h:t) 
            | x <= h = x : (h:t)
            | otherwise = h : insert' x t

--26
unwords' :: [String] -> String
unwords' [] = []
unwords' (h:t) = h ++ (" " ++ (unwords' t)) 

--27
unlines' :: [String] -> String
unlines' [] = []
unlines' (h:t) = h ++ ("\n" ++ (unlines' t))

--28
pMaior' :: Ord a => [a] -> Int
pMaior' [] = error "Lista Vazia"
pMaior' [h] = 0
pMaior' (h:x:t) 
            | h > x = pMaior' (h:t)
            | otherwise = 1 + pMaior' (x:t)

--29
temRepetidos' :: Eq a => [a] -> Bool
temRepetidos' [] = False
temRepetidos' (h:t)
                | temRepetidosAux' (h:t) = True
                | otherwise = temRepetidos' t

temRepetidosAux' :: Eq a => [a] -> Bool
temRepetidosAux' [head] = False
temRepetidosAux' (h:x:t)
                    | h == x = True
                    | otherwise = temRepetidosAux' (h:t)

--30 
algarismos :: [Char] -> [Char]
algarismos [] = []
algarismos (h:t) | h == '0' = h:(algarismos t)
                 | h == '1' = h:(algarismos t)
                 | h == '2' = h:(algarismos t)
                 | h == '3' = h:(algarismos t)
                 | h == '4' = h:(algarismos t)
                 | h == '5' = h:(algarismos t)
                 | h == '6' = h:(algarismos t)
                 | h == '7' = h:(algarismos t)
                 | h == '8' = h:(algarismos t)
                 | h == '9' = h:(algarismos t)
                 | otherwise = algarismos t

-- 31
posImpares :: [a] -> [a]
posImpares [] = []
posImpares (head:next:tail) = next:(posImpares tail)

-- 32
posPares :: [a] -> [a] 
posPares [] = []
posPares (head:next:tail) = head:(posImpares tail)

-- 33
isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted (head:next:tail) | head <= next = isSorted (next:tail)
                          | otherwise = False

--34
iSort :: Ord a => [a] -> [a]
iSort [] = []
iSort (h:t) = myInsert h (iSort t)

-- 35
menor :: String -> String -> Bool
menor [] _ = True
menor _ [] = False
menor (head1:tail1) (head2:tail2) | head1 > head2 = False
                                  | head1 < head2 = True
                                  | otherwise = menor tail1 tail2

-- 36
elemMSet :: Eq a => a -> [(a,Int)] -> Bool
elemMSet _ [] = False
elemMSet coiso ((cena,num):t) | cena == coiso = True
                              | otherwise = elemMSet coiso t

-- 37
lengthMSet :: [(a,Int)] -> Int
lengthMSet [] = 0
lengthMSet ((cena,num):t) = num + lengthMSet t

-- 38
converteMSet :: [(a,Int)] -> [a]
converteMSet [] = []
converteMSet ((cena,num):t) = (converteMSetAux (cena,num)) ++ converteMSet t

converteMSetAux :: (a,Int) -> [a]
converteMSetAux (_,0) = []
converteMSetAux (cena,num) = [cena] ++ (converteMSetAux (cena,(num - 1)))

-- 39
insereMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
insereMSet coisa [] = [(coisa,1)]
insereMSet coisa ((cena,num):t) | coisa == cena = ((cena,num + 1):t)
                                | otherwise = [(cena,num)] ++ (insereMSet coisa t)

-- 40
removeMSet :: Eq a => a -> [(a,Int)] -> [(a,Int)]
removeMSet coiso [] = []
removeMSet coiso ((cena,num):t) | coiso == cena && num == 1 = t
                                | coiso == cena = ((cena,num - 1):t)
                                | otherwise = (cena,num):(removeMSet coiso t)

-- 41
constroiMSet :: Ord a => [a] -> [(a,Int)]
constroiMSet lista = constroiMSetAux 1 lista

constroiMSetAux :: Ord a => Int -> [a] -> [(a,Int)]
constroiMSetAux contador [cena] = [(cena,contador)]
constroiMSetAux contador (cena1:cena2:t) | cena1 == cena2 = constroiMSetAux (contador + 1) (cena1:t)
                                         | otherwise = (cena1,contador):(constroiMSetAux 1 (cena2:t))

-- 42
myPartitionEithers :: [Either a b] -> ([a],[b])
myPartitionEithers [] = 
myPartitionEithers (Either a:t) = (a:m,n)
myPartitionEithers (Either b:t) = (m,b:n)
                                where (m.n) = myPartitionEithers t


-- 43
myCatMaybes :: [Maybe a] -> [a]
myCatMaybes [] = []
myCatMaybes (Just a:t) = a:(myCatMaybes t)
myCatMaybes (Nothing:t) = myCatMaybes t

-- 44
data Movimento = Norte | Sul | Este | Oeste 
	deriving Show

posicao :: (Int,Int) -> [Movimento] -> (Int,Int)
posicao (x,y) [] = (x,y)
posicao (x,y) (Norte:t) = posicao (x,y + 1) t
posicao (x,y) (Sul:t) = posicao (x,y - 1) t
posicao (x,y) (Este:t) = posicao (x - 1,y) t
posicao (x,y) (Oeste:t) = posicao (x + 1,y) t

-- 45
caminho :: (Int,Int) -> (Int,Int) -> [Movimento]
caminho (x1,y1) (x2,y2) | x1 < x2 = Oeste:(caminho (x1 + 1,y1) (x2,y2))
                        | x1 > x2 = Este:(caminho (x1 - 1,y1) (x2,y2))
                        | y1 < y2 = Norte:(caminho (x1,y1 + 1) (x2,y2))
                        | y1 > y2 = Sul:(caminho (x1,y1 - 1) (x2,y2))
                        | otherwise = []

-- 46
vertical :: [Movimento] -> Bool 
vertical [] = True
vertical (Este:t) = False
vertical (Oeste:t) = False
vertical (h:t) = vertical t

-- 47
data Posicao = Pos Int Int 
	deriving Show

maisCentral :: [Posicao] -> Posicao
maisCentral [(Pos x y)] = (Pos x y)
maisCentral ((Pos x1 y1):(Pos x2 y2):t) | distCentro (Pos x1 y1) < distCentro (Pos x2 y2) = maisCentral ((Pos x1 y1):t)
                                | otherwise = maisCentral ((Pos x2 y2):t)
                                where distCentro (Pos x y) = sqrt (fromIntegral(x^2 + y^2))

-- 48
vizinhos :: Posicao -> [Posicao] -> [Posicao]
vizinhos (Pos x1 y1) [] = []
vizinhos (Pos x1 y1) ((Pos x2 y2):t) | (x1 == (x2 + 1) && y1 == y2) || (x1 == (x2 - 1) && y1 == y2) || (x1 == x2 && y1 == (y2 + 1)) || (x1 == x2 && y1 == (y2 - 1)) = (Pos x2 y2):(vizinhos (Pos x1 y1) t)
                                     | otherwise = vizinhos (Pos x1 y1) t

-- 49
mesmaOrdenada :: [Posicao] -> Bool
mesmaOrdenada [(Pos x y)] = True
mesmaOrdenada ((Pos x1 y1):(Pos x2 y2):t) | y1 == y2 = mesmaOrdenada ((Pos x2 y2):t)
                                          | otherwise = False

-- 50
data Semaforo = Verde | Amarelo | Vermelho 
	deriving Show

interseccaoOK :: [Semaforo] -> Bool
interseccaoOK semaforo = (interseccaoOKAux semaforo) <= 1

                       where

                       interseccaoOKAux [Vermelho] = 0
                       interseccaoOKAux [Verde] = 1
                       interseccaoOKAux [Amarelo] = 1
                       interseccaoOKAux (Vermelho:t) = interseccaoOKAux t
                       interseccaoOKAux (Verde:t) = 1 + interseccaoOKAux t
                       interseccaoOKAux (Amarelo:t) = 1 + interseccaoOKAux t 


