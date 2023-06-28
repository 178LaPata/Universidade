module Ficha7 where

--1
data ExpInt = Const Int
			| Simetrico ExpInt
			| Mais ExpInt ExpInt
			| Menos ExpInt ExpInt
			| Mult ExpInt ExpInt

--a 
calcula :: ExpInt -> Int
calcula (Const x)= x 
calcula (Simetrico e)= -(calcula e)
calcula (Mais e1 e2)= calcula e1 + calcula e2
calcula (Menos e1 e2)= calcula e1 - calcula e2
calcula (Mult e1 e2)= calcula e1 * calcula e2

--b
infixa :: ExpInt -> String
infixa (Const x) = show x 
infixa (Simetrico e) = "-" ++ (infixa e)
infixa (Mais e1 e2) = '(' : (infixa e1) ++ " + " ++ (infixa e2) ++ ")"
infixa (Menos e1 e2) = '(' : (infixa e1) ++ " - " ++ (infixa e2) ++ ")"
infixa (Mult e1 e2) = '(' : (infixa e1) ++ " * " ++ (infixa e2) ++ ")"

--c
posfixa :: ExpInt -> String
posfixa (Const x) = show x
posfixa (Simetrico e) = (posfixa e) ++ " ~ "
posfixa (Mais e1 e2) = (posfixa e1) ++ " " ++ (posfixa e2) ++ " + "
posfixa (Menos e1 e2) = (posfixa e1) ++ " " ++ (posfixa e2) ++ " - "
posfixa (Mult e1 e2) = (posfixa e1) ++ " " ++ (posfixa e2) ++ " * "

--2

data RTree a = R a [RTree a]
	deriving Show
--a
soma :: Num a => RTree a -> a
soma (R x lrt) = x + sum (map soma lrt)

--b
altura :: RTree a -> Int
altura (R x []) = 1
altura (R x lrt)= 1 + maximum (map altura lrt)

--c
prune :: Int -> RTree a -> RTree a
prune 0 (R x _) = R x []
prune _ (R x []) = (R x [])
prune n (R x lrt) = R x (map (prune (n-1)) lrt)

--d
mirror :: RTree a -> RTree a
mirror (R x lrt) = R x (map mirror lrt)

--e
postorder :: RTree a -> [a]
postorder (R x lrt)= 
    (concat (map postorder lrt)) ++ [x]

--3
data BTree a = Empty | Node a (BTree a) (BTree a)
data LTree a = Tip a | Fork (LTree a) (LTree a)

--a
ltSum :: Num a => LTree a -> a
ltSum (Tip x) = x
ltSum (Fork e d) = ltSum e + ltSum d

--b
listaLT :: LTree a -> [a]
listaLT (Tip x) = [x]
listaLT (Fork e d) = (listaLT e) ++ (listaLT d)

--c
ltHeight :: LTree a -> Int
ltHeight (Tip x) = 1
ltHeight (Fork e d) = 1 + max (ltHeight e) (ltHeight d)

--4
data FTree a b = Leaf b 
				| No a (FTree a b) (FTree a b)
	deriving Show

--a
splitFTree :: FTree a b -> (BTree a, LTree b)
splitFTree (Leaf x) = (Empty, Tip x)
splitFTree (No y e d) = 
     let (bt1,lt1)= splitFTree e
         (bt2,lt2)= splitFTree d
     in (Node y bt1 bt2, Fork lt1 lt2)


--b
joinTrees :: BTree a -> LTree b -> Maybe (FTree a b)
joinTrees Empty (Tip x) = Just (Leaf x)
joinTrees _ (Tip x) = Nothing
joinTrees Empty (Fork e d) =  Nothing
joinTrees (Node y e1 d1) (Fork e d) =  
    let lt1= joinTrees e1 e
        lt2= joinTrees d1 d 
    in case lt1 of Nothing -> Nothing
                   (Just t1) -> case lt2 of Nothing -> Nothing
                                            (Just t2) -> Just (No y t1 t2)
