module Ficha5 where

--1

--a
any' :: (a -> Bool) -> [a] -> Bool
any' p [] = False
any' p (x:xs)
  | p x = True
  | otherwise= any' p xs

any'' :: (a -> Bool) -> [a] -> Bool
any'' p [] = False
any'' p (x:xs)= p x || any'' p xs

all' :: (a -> Bool) -> [a] -> Bool
all' p [] = False
all' p (x:xs)= p x && all' p xs

--b 
zipWith' :: (a->b->c) -> [a] -> [b] -> [c]
zipWith' f [] _ = []
zipWith' f _ [] = []
zipWith' f (x:xs) (y:ys) = (f x y) : zipWith' f xs ys

--c
takeWhile' :: (a->Bool) -> [a] -> [a]
takeWhile' _ [] =[]
takeWhile' p (x:xs)
  | p x = x: takeWhile' p xs
  | otherwise= []

--d
dropWhile' :: (a->Bool) -> [a] -> [a]
dropWhile' _ [] =[]
dropWhile' p (x:xs)
  | p x= dropWhile' p xs
  |otherwise= (x:xs)

--e
span' :: (a-> Bool) -> [a] -> ([a],[a])
span' _ [] = ([],[])
span' p (x:xs) 
  | not (p x) = ([],(x:xs))
  | p x = let (e,d)= span' p xs
          in (x:e,d)

--f
deleteBy :: (a -> a -> Bool) -> a -> [a] -> [a]
deleteBy p x [] = []
deleteBy p x (y:ys)
  | p x y = ys
  | otherwise= y : deleteBy p x ys

--g
sortOn :: Ord b => (a -> b) -> [a] -> [a]
sortOn  f [] = []
sortOn f (x:xs) = insert f x (sortOn f xs)

insert :: Ord b => (a -> b) -> a -> [a] -> [a]
insert f x [] = [x]
insert f x (y:ys) 
            | f x <= f y x:y:ys
            | otherwise = y : insert f x ys

-- ou

sortOn' :: Ord b => (a -> b) -> [a] -> [a]
sortOn' f [] = []
sortOn' f (x:xs) = insert' x (sortOn' f xs)
   where insert' x [] = [x]
         insert' x (y:ys)
            | f x< f y= x:y:ys
            |otherwise=y: insert x ys

--2
type Polinomio = [Monomio]
type Monomio = (Float,Int)

--a
selgrau :: Int -> Polinomio -> Polinomio
selgrau g p = filter (\(c,e) -> e==g) p

--b
conta :: Int -> Polinomio -> Int
conta g p = sum (map (\(c,e)-> if e==g then 1 else 0 ) p)

conta1 :: Int -> Polinomio -> Int
conta1 g p = foldr aux 0 p
   where aux :: Monomio -> Int -> Int
         aux (c,e) n = if e==g then 1+n else n

--c
grau :: Polinomio -> Int
grau p = maximum (map snd p)

--d
deriv :: Polinomio -> Polinomio
deriv p = let p1 = map (\(c,e)->(c*(fromIntegral e),e-1)) p
          in filter (\(c,e)->c/=0) p1

--e
calcula :: Float -> Polinomio -> Float
calcula v p= sum (map (\(c,e)-> c*(v^e)) p)

calcula1 v p = foldr aux 0 p
   where aux :: Monomio -> Float -> Float
         aux (c,e) r = c*(v^e) + r 
--f 
simp :: Polinomio -> Polinomio
simp pol = [(c,e) | (c,e) <- pol, c/= 0]

--g
mult :: Monomio -> Polinomio -> Polinomio
mult (c,e) p = map (\(c1,e1)-> (c1*c,e1+e)) p

--h
ordena :: Polinomio -> Polinomio
ordena [] =[]
ordena ((c,e):p) = ordena lmen ++ [(c,e)] ++ ordena lmai
    where lmen = filter (\(c1,e1) -> e1<=e ) p
          lmai = filter (\(c1,e1) -> e1>e ) p 

ordena1 :: Polinomio -> Polinomio
ordena1 p = sortOn' snd p

--i
-- normaliza :: Polinomio -> Polinomio

--j
-- soma :: Polinomio -> Polinomio -> Polinomio

--k
produto :: Polinomio -> Polinomio -> Polinomio
produto pol pol2 = foldr (\m a -> (mult m pol)++a) [] pol2

--l
-- equiv :: Polinomio -> Polinomio -> Bool

-- 3
type Mat a = [[a]]

--a 
dimOK :: Mat a -> Bool
dimOK [] = False
dimOK m = aux (map length m)
    where aux (O:_) = False
          aux (x:xs) = filter ((/=x) xs) == []

--b 
dimMat :: Mat a -> (Int,Int)
dimMat m@(l:ls) = (length m, length l)

--c
addMat :: Num a => Mat a -> Mat a -> Mat a
addMat (la:las) (lb:lbs) = zipwith (+) la lb : addMat las lbs
addMat [] [] = []

--d 
transpose :: Mat a -> Mat a
transpose ([]:_) = []
transpose m = (map head m) : transpose (map tail m)

--e
multMat :: Num a => Mat a -> Mat a -> Mat a
multMat (l:ls) m = (linha l m)
multMat [] _ = []

linha :: Num a => [a] -> Mat a -> [a]
linha l m = sum (zipwith (*) l (map head m)) : linha (map tail m)

--f
zipWMat :: (a -> b -> c) -> Mat a -> Mat b -> Mat c
zipWMat f (la:las) (lb:lbs) = zipwith f la lb : zipWMat f las lbs
zipWMat _ _ _ = []

somaMat :: Num a => Mat a -> Mat a -> Mat a
somaMat a b = zipWMat (+) a b

--g
triSup :: (Eq a, Num a) => Mat a -> Bool
triSup [] = True
triSup (l:m) = (all (==0) (map head m)) && triSup (map tail m)

triSup' :: (Eq a, Num a) => Mat a -> Bool
triSup' [] = True
triSup' m = all (==0) (aux 0 m)
  where aux :: Int -> Mat a -> [a]
        aux _ [] = []
        aux k (l:m)= take k l ++ aux (k+1) m

--h
rotateLeft :: Mat a -> Mat a
rotateLeft [] = []
rotateLeft [[x1],[x2],[x3]] = [[x1,x2,x3]]
rotateLeft m = (map (last) m) : rotateLeft (map (init) m)

rotateLeft' :: Mat a -> Mat a
rotateLeft' m = reverse $ trans m



