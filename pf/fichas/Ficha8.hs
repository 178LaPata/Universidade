module Ficha8 where

import Ficha3
import Ficha7

--1
data Frac = F Integer Integer

--a 
normaliza :: Frac -> Frac
normaliza (F x y) = let x' = abs x
                        y' = abs y
                        c = mdc x' y'
                        s = (signum x)*(signum y)
                    in F (s*(div (abs x) c)) (div (abs y) c)

mdc :: Integer -> Integer -> Integer
mdc x y | x == y = x
        | x > y = mdc (x-y) y
        | x < y = mdc x (y-x)

--b
instance Eq Frac where
     (F a b) == (F c d) = (a*d == c*b)

--c
instance Ord Frac where
     f1 <= f2 = let (F a b) = normaliza f1
                    (F c d) = normaliza f2
                in a*d <= c*d

--d
instance Show Frac where
    show (F a b) = "(" ++ show (a) ++ "/" ++ show (b) ++ ")"

--e
instance Num Frac where
      (F a b) + (F c d) = F (a*d+c*b) (b*d)
      (F a b) * (F c d) = F (a*c) (b*d)
      negate (F a b) = F (-a) b
      abs (F a b) = F (abs a) (abs b)
      signum (F a b) = F ((signum a)*(signum b)) 1
      fromInteger n = F n 1

--f
fun :: Frac -> [Frac] -> [Frac]
fun f [] = []
fun f l = filter (> 2*f)  l

--2
data ExpInt = Const Int
               |Simetrico ExpInt
               |Mais ExpInt ExpInt
               |Menos ExpInt ExpInt
               |Mult ExpInt ExpInt

data Exp a = Const a
               |Simetrico (Exp a)
               |Mais (Exp a) (Exp a)
               |Menos (Exp a) (Exp a)
               |Mult (Exp a) (Exp a)

calcula :: Num a => Exp a -> Int
calcula (Const x)= x 
calcula (Simetrico e)= -(calcula e)
calcula (Mais e1 e2)= calcula e1 + calcula e2
calcula (Menos e1 e2)= calcula e1 - calcula e2
calcula (Mult e1 e2)= calcula e1 * calcula e2

infixa :: Eq a => Exp a -> String
infixa (Const x)= show x 
infixa (Simetrico e)= "-" ++ infixa e 
infixa (Mais e1 e2)= "(" ++ infixa e1 ++ "+" ++ infixa e2 ++ ")"
infixa (Menos e1 e2)= "(" ++ infixa e1 ++ "-" ++ infixa e2 ++ ")"
infixa (Mult e1 e2)= "(" ++ infixa e1 ++ "*" ++ infixa e2 ++ ")"

instance Show (Exp a) where
    show e = infixa e

instance (Eq a,Num a) => Eq (Exp a) where
    (==) e1 e2 = (calcula e1) == (calcula e2)

instance Num a => Num (Exp a) where
    (+) e1 e2 = Const (calcula e1 + calcula e2)
    (-) e1 e2 = Const (calcula e1 - calcula e2)
    (*) e1 e2 = Const (calcula e1 * calcula e2)
    negate e = Const (negate (calcula e))
    abs e = Const (abs (calcula e))
    signum e = Const (signum (calcula e))
    fromInteger x = Const (fromInteger x)

--3
data Movimento = Credito Float | Debito Float
data Data = D Int Int Int
data Extracto = Ext Float [(Data, String, Movimento)]


instance Eq Data where
    (==) (D d1 m1 a1) (D d2 m2 a2) = d1==d2 && m1==m2 && a1==a2

instance Ord Data where
    compare (D d1 m1 a1) (D d2 m2 a2) 
        | (a1,m1,d1) < (a2,m2,d2) = LT
        | (a1,m1,d1) == (a2,m2,d2) = EQ
        | (a1,m1,d1) > (a2,m2,d2) = GT

instance Show Data where
    show (D d m a) = show a ++ "/" ++ show m ++ "/" ++ show d

ordena :: Extracto -> Extracto
ordena (Ext si lm) =
    Ext si (sort0n (\(d,s,m)-> d) lm)


saldo :: Extracto -> Extracto
saldo (Ext si lm) = let (sc,sd) = somaCredDeb lm 
                    in sc+si-sd
        where somaCredDeb :: [(Data,String,Movimento)] -> (Float,Float)
              somaCredDeb [] = (0,0)
              somaCredDeb ((_,_,m):lm) =
                let (sc,sd) = somaCredDeb lm
                in case m of
                    Credito x -> (sc+x,sd)
                    Debito x -> (sc,sd+x)

col :: String -> Int -> String
col s n = take n (s++ replicate n ' ')

instance Show Extracto where 
    show (Ext si lm) =
        "Saldo anterior: " ++ show si ++ "\n" ++
        replicate (4*12) '-' ++ "\n" ++
        col "Data" 12 ++ col "Descricao" 12 ++ col "Credito" 12 ++ col "Debito" 12 ++ "\n" ++
        replicate (4*12) '-' ++ "\n" ++
        listaMovimentos lm ++ "\n" ++
        replicate (4*12) '-' ++ "\n" ++
        "Saldo actual:" ++ show (saldo (Ext si lm))

listaMovimentos :: [(Data,String,Movimento)]-> String
listaMovimentos [] = ""
listaMovimentos ((d,desc,Credito x):lm)= 
   col (show d) 12 ++ col desc 12 ++ col (show x) 12 ++ "\n" ++ 
   listaMovimentos lm
listaMovimentos ((d,desc,Debito x):lm)= 
   col (show d) 12 ++ col desc 12 ++ replicate 12 " " ++col (show x) 12 ++ "\n" ++ 
   listaMovimentos lm