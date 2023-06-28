module Ficha9 where

import System.Random 
import Data.Char
import Data.List
import Data.Maybe

--1
--a
bingo :: IO ()
bingo = do putStrLn "Bem vindo ao Bingo"
           dialogoBingo []
           
dialogoBingo :: [Int] -> IO()
dialogoBingo l = do x <- randomRIO (1,90) ::IO Int
                    if length l == 90
                        then putStrLn "Acabou o programa"
                     else if elem x l
                     then 
                        do dialogoBingo l
                     else
                        do putStr ("Saiu o Número : ")
                           print x
                           a <- adicionaLista l x
                           putStrLn ""
                           dialogoBingo a

adicionaLista :: [Int] -> Int -> IO [Int]
adicionaLista l a = readIO (show (a : l))

--b
mastermind :: IO ()
mastermind = do ln <- geraNumeros;
                joga ln

joga :: [Int] -> IO ()
joga ln = do lnj <- leJogo;
             if ln==lnj then putStr "Ganhou!"
              else 
                do let lnc= concat(zipWith (\x y-> if x==y then [x] else []) ln lnj);
                   let ln' = ln\\lnc;
                   let lnj' = lnj\\lnc;
                   let lnpe = comuns ln' lnj';
                   putStrLn $ "Certos nas posições certas: " ++ (show $ length lnc);
                   putStrLn $ "Certos nas posições erradas: " ++ (show $ length lnpe);
                   joga ln

comuns :: [Int] -> [Int] -> [Int]
comuns [] l = []
comuns (x:xs) ys 
  | elem x ys = x : comuns xs (ys\\[x])
  | otherwise= comuns xs ys


geraNumeros :: IO [Int]
geraNumeros = do n1 <- randomRIO (0,9);
                 n2 <- randomRIO (0,9);
                 n3 <- randomRIO (0,9);
                 n4 <- randomRIO (0,9);
                 return [n1,n2,n3,n4]

leJogo :: IO [Int]
leJogo =
    do putStrLn "Introduza 4 digitos separados por espaços e termine com ENTER"
       s<- getLine;
       let lnj = map (digitToInt.head) (words s);
       return lnj

--2
data Aposta = Ap [Int] (Int,Int)

--a
valida :: Aposta -> Bool
valida (Ap l (a,b)) = length l == 5 && numValidos l
                      && a/=b && 1<=a && a<=9
                      && 1<=b && b<=9

numValidos :: [Int] -> Bool
numValidos [] = True
numValidos (h:t) = 1<=h && h<=50 && not (elem h t) 
                   && numValidos t

--b
comuns :: Aposta -> Aposta -> (Int, Int)
comuns (Ap ns (a,b)) (Ap xs (c,d)) = (numComs ns xs, numComs [a,b] [c,d])

numComs :: [Int] -> [Int] -> Int
numComs [] l = 0
numComs (x:xs) = if (elem x l)
                 then 1 + numComs xs l
                 else numComs xs l

--c
--i
instance Eq Aposta where
    ap1 == ap2 = comuns ap1 ap2 == (5,2)

--ii
premio :: Aposta -> Aposta -> Maybe Int
premio ap ch = case (comuns ap ch) of
                  (5,2) -> Just 1
                  (5,1) -> Just 2
                  (5,0) -> Just 3
                  (4,2) -> Just 4
                  (4,1) -> Just 5
                  (4,0) -> Just 6
                  (3,2) -> Just 7
                  (2,2) -> Just 8
                  (3,1) -> Just 9
                  (3,0) -> Just 10
                  (1,2) -> Just 11
                  (2,1) -> Just 12
                  (2,0) -> Just 13

--d
--i
leAposta :: IO Aposta
leAposta = do putStrLn "Insira 5 números entre 1 e 50, separados por espaços, e termine com ENTER: "
              s  <- getLine;
              let ln = map read (words s) 
              putStrLn "Insira 2 números entre 1 e 9, separados por espaços, e termine com ENTER: "
              s1 <- getLine;
              let [x,y] = map read (words s1)
              let ap = Ap ln (x,y)
              if valida ap then return ap 
                           else do putStrLn "Aposta inválida";
                                   leAposta;

--ii
joga' :: Aposta -> IO ()
joga' ap = do apj <- leAposta;
            let p= premio ap apj;
            putStrLn (mostraPremio p)

mostraPremio :: Maybe Int -> String
mostraPremio Nothing = "Sem prémio!"
mostraPremio (Just p) = "Tem o prémio: " ++ show p

--e
geraChave :: IO Aposta
geraChave = do ln <- geraNumeros' [] 5 (1,50);
               [x,y] <- geraNumeros' [] 2 (1,9);
       
geraNumeros' :: [Int] -> Int -> (Int,Int) -> IO [Int]
geraNumeros' l n (i,s) = do if (length l) == n then return l
                             else do k <- randomRIO (i,s);
                                     if elem k l then geraNumeros' l n (i,s)
                                                 else geraNumeros' (k:l) n (i,s)

--f
ciclo :: Aposta -> IO ()
ciclo ap = do op <- menu;
                case op of
                    "1" -> do {joga' ap; ciclo ap}
                    "2" -> do {ch <- geraChave; ciclo ch}
                    "0" -> putStrLn "Fim"
                    _ -> ciclo ap
