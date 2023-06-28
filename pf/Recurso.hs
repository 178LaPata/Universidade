module Recurso where

--1
type BD = [Video]

data Video = Filme String Int       -- título, ano
           | Serie String Int Int   -- título, temporada, episódio
           | Show  String Int       -- título, ano
           | Outro String
   deriving (Eq)

outros :: BD -> BD 
outros ((Outro x):t) = Outro x : outros t
outros ((_):t) = outros t

-- resposta Pinto
outros':: BD -> BD
outros' [] = []
outros' ((Filme s i):t) = outros' t
outros' ((Serie s i1 i2):t) = outros' t
outros' ((Show s i):t) = outros' t
outros' (h:t) = h : (outros' t)

--2
zipWithL :: (a -> b -> c) -> [a] -> [b] -> a -> b -> [c]
zipWithL f [] [] a b = []
zipWithL f [] (x:xs) a b = (f a x) : (zipWithL f [] xs a b)
zipWithL f (x:xs) [] a b = (f x b) : (zipWithL f xs [] a b)
zipWithL f (x:xs) (y:ys) a b = (f x y) : (zipWithL f xs ys a b)

--3
type Imagem = Matriz Pixel 
type Matriz a = [[a]]
type Pixel = (Int,Int,Int) 

transforma :: (Pixel -> Pixel) -> Imagem -> Imagem
transforma f [] = []
transforma f (h:t) = (aux f h) : (transforma f t)

aux :: (Pixel -> Pixel) -> [Pixel] -> [Pixel]
aux f [] = []
aux f (h:t) = f(h) : (aux f t)

--4

data Pokedex = Empty | Pokemons Pokemon Pokedex Pokedex
type Pokemon = (Nome, Stamina, Tipo)
data Tipo = Agua | Fogo | Terra | Ar
type Nome = String 
type Stamina = Int 

p1 = Pokemons ("Psyduck",90,Agua) 
  (Pokemons ("Charmander",55,Fogo) Empty Empty) 
  (Pokemons ("Blastoid",95,Agua) Empty Empty)

pokemons :: Int -> Pokedex -> [(Nome,Int)]
pokemons n Empty = []
pokemons n (Pokemons (nome, stam, tipo) e d) 
                        | stam >= n = pokemons n e
                        | otherwise = (pokemons n d) ++ [(nome,stam)] ++ (pokemons n e)

--5
data BT1 a = V  | N a (BT1 a) (BT1 a) 
data BT2 a b = L a | F b (BT2 a b) (BT2 a b) 
    deriving Show

--6 
func :: Int -> [Int] -> Int 
func x l = sum (filter (>0) (map (+x) l))

func1 :: Int -> [Int] -> Int
func1 _ [] = 0
func1 n (x:xs) 
        | x + n > 0 = x + func1 n xs
        | otherwise = func1 n xs 

-- Resposta Pinto (não enviei)
funcAl :: Int -> [Int] -> Int
funcAl _ [] = 0
funcAl x (h:t) | h > 0 = h+(h+x)+(funcAl x t)
               | otherwise = h+x+(funcAl x t)

--7

data BTree a = Empty
             | Node a (BTree a) (BTree a)

mostra :: BTree Int -> IO ()
mostra bTree = do putStrLn "Digite um numero"
                  x <- getLine
                  y <- readIO x :: IO Int
                  retorna (filter (>=y) (ordena1 (arvToLista bTree)))

retorna :: [Int] -> IO ()
retorna [] = do putStr ""
retorna (x:xs) = do print x
                    retorna xs

ordena1 :: [Int] -> [Int]
ordena1 [] = []
ordena1 (x:xs) = (ordena1 menor) ++ (x:igual) ++ (ordena1 maior)
          where menor = filter (<x) xs
                igual = filter (==x) xs
                maior = filter (>x) xs

arvToLista :: BTree Int -> [Int]
arvToLista Empty = []
arvToLista (Node x e d) = x : arvToLista e ++ arvToLista d

--8
-- NEM VI A PERGUNTA


