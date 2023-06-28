module Ficha1 where

import Data.Char

-- 1

-- a
perimetro :: Double -> Double
perimetro r = 2 * pi * r

-- b
dist :: (Double,Double) -> (Double,Double) -> Double
dist (x1,y1) (x2,y2) = sqrt ((x2-x1)^2 + (y2-y1)^2) 

-- c
primUlt :: [Int] -> (Int,Int) 
primUlt l = (head l, last l) 

-- d
multiplo :: Int -> Int -> Bool
multiplo x y = if (mod x y) == 0 
               then True 
               else False 

-- e
truncaImpar :: [a] -> [a]
truncaImpar l = if (mod (length l) 2 == 0)
                then l
                else tail l

-- f
max2 :: Int -> Int -> Int
max2 x y = if (x > y) 
           then x 
           else y

-- g
max3 :: Int -> Int -> Int -> Int
max3 x y z = max2 (max2 x y) z

-- 2 

-- a
nRaizes2 :: Float -> Float -> Float -> Int 
nRaizes2 a b c
    | b^2 - 4*a*c == 0    = 1
    | b^2 - 4*a*c <  0    = 0
    | otherwise           = 2

-- b
raizes :: Float -> Float -> Float -> [Float]
raizes a b c = if (nRaizes a b c) == 2
               then [(-b) + sqrt (b^2 - 4*a*c)/(2*a), (-b) - sqrt (b^2 - 4*a*c)/(2*a)]
               else if (nRaizes a b c) == 1
                    then [(-b) / (2*a)]
                    else []

-- 3

type Hora = (Int,Int) 

-- a
valida :: Hora -> Bool
valida (h,m) = h <= 23 && m < 60 && h >= 0 && m >= 0

-- b
depois :: Hora -> Hora -> Bool
depois (h1,m1) (h2,m2) = if h1 > h2
                         then (h1,m1)
                         else if (h1 == h2)
                              then if (m1 > m2)
                                   then (h2,m2)
                              else (h2,m2)

-- c
chm :: Hora -> Int
chm (x,y) = x * 60 + y

-- d
horas :: Int -> Hora
horas (h,m) = (div h 60, mod x 60)

-- e 
diferencaHoras :: Hora -> Hora -> Int
dh (x,y) (x2,y2) = abs((x * 60 + y) - (x2 * 60 + y2))

--f
addMin :: Hora -> Int -> Hora
addMin (h,m) m1 = minToHora (horaToMin (h,m) + m1)

-- 4
data Hora = H Int Int 
    deriving (Show,Eq)

-- a
horaValida2 :: Hora1 -> Bool
horaValida2 (H h m) = h<=24 && h>=0 && m<60 && m>=0

--b
horaPosterior2 :: Hora1 -> Hora1 -> Bool
horaPosterior2 (H h1 m1) (H h2 m2) = if h1 == h2 then h1 > h2 else h1 > h2

--c
horaToMin2 :: Hora1 -> Int
horaToMin2 (H h m) = h*60 + m

--d
minToHora2 :: Int -> Hora1
minToHora2 m = (H (div m 60) (mod m 60))

--e
difHoras2 :: Hora1 -> Hora1 -> Int
difHoras2 h1 h2
 | f < 0 = (-f)
 | otherwise = f
 where f = (horaToMin2 h1) - (horaToMin2 h2)

--f
addMin2 :: Hora1 -> Int -> Hora1
addMin2 h m = minToHora2 (horaToMin2 h + m)

{- 
MINHA TENTATIVA
-- a
valida2 :: Hora -> Bool
valida2 (H h m) = h == 0 && h<24 && m==0 && m<60

-- b
depois2 :: Hora -> Hora -> Bool
depois2 x y = minutos2 x > minutos2 y

-- c
minutos2 :: Hora -> Int
minutos2 (H h m) = 60 * h * m

-- d
converter :: Int -> Hora
converter x = let h = mod (div x 60) 24;
                  m = mod x 60;
              in (H h m)
-- 
edepois :: Hora -> Hora -> Bool
edepois (H h1 m1) (H h2 m2) =
        minutos (H h1 m1) > minutos (H h2 m2)

-- f
adiantar :: Int -> Hora -> Hora
adiantar x a = converter ((minutos a) + x)
-}

-- 5
data Semaforo = Verde | Amarelo | Vermelho 
    deriving (Show,Eq)

--a
next :: Semaforo -> Semaforo
next Verde = Amarelo
next Amarelo = Vermelho
nex2 Vermelho = Verde

--b
stop :: Semaforo -> Bool
stop Verde = False
stop Amarelo = False
stop Vermelho = True

--c
safe :: Semaforo -> Semaforo -> Bool
safe x y = (stop x) || (stop y) 

-- 6
data Ponto = Cartesiano Double Double 
           | Polar Double Double
    deriving (Show,Eq)

-- a
posx :: Ponto -> Double
posx (Cartesiano x y) = x
posx (Polar r a) = r * cos a 

-- b
posy :: Ponto -> Double
posy (Cartesiano x y) = y
posy (Polar r a) = r * sin a 

-- c
raio :: Ponto -> Double
raio  (Cartesiano x y) = sqrt (x^2 + y^2)
raio (Polar r a) = r

-- d
angulo :: Ponto -> Double
angulo (Polar _ a) = a
angulo (Cartesiano x y) 
            | x == 0 && y>0 = pi/2
            | x == 0 && y<0 = (3*pi)/2
            | x>0 && y>=0 = atan(y/x)
            | x<0 && y>=0 = atan(y/x)
            | x<0 && y<0 = pi + atan(y/x)
            | x>0 && y<0 = pi + atan(y/x)

-- e
distpontos :: Ponto -> Ponto -> Double 
distpontos p1 p2 = sqrt((posx p2 - posx p1)^2 + (posy p2 - posy p1)^2)

{-
distf :: Ponto -> Ponto -> Double
distf (Cartesiano x y ) (Cartesiano x2 y2 ) = sqrt( (x-x2)^2 + (y-y2)^2 )
distf (Cartesiano x y ) (Polar d a ) = sqrt( (x- (cos a) * d )^2 + (y- (sin a) * d)^2 )
distf (Polar d a ) (Cartesiano x y ) = distf (Cartesiano x y ) (Polar d a )
distf (Polar d a ) (Polar d2 a2 ) = sqrt( ((cos a) * d - (cos a2) * d2 )^2 + ((sin a) * d- (sin a2) * d2)^2 )
-}

-- 7
data Figura = Circulo Ponto Double
            | Rectangulo Ponto Ponto
            | Triangulo Ponto Ponto Ponto
              deriving (Show,Eq)

-- a
poligono :: Figura -> Bool
poligono (Circulo p r) = False
poligono _ = True

-- b
vertices :: Figura -> [Ponto]
vertices (Circulo _ _) = []
vertices (Triangulo a b c) = [a b c]
vertices (Rectangulo p1 p2) = [p1, p2, p3, p4]
    where p3 = Cartesiano (posx p2) (posy p1)
          p4 = Cartesiano (posx p1) (posy p2)

-- c
area :: Figura -> Double
area (Triangulo p1 p2 p3) =
 let a = distpontos p1 p2
     b = distpontos p2 p3
     c = distpontos p3 p1
     s = (a+b+c) / 2 -- semi-perimetro
 in  sqrt (s*(s-a)*(s-b)*(s-c))
area (Circulo p r) = pi*(r^2)
area (Retangulo p1 p2) = (abs ((posx p2) - (posx p1))) * (abs ((posy p2) - (posy p1)))

-- d
perimetro :: Figura -> Double
perimetro (Circulo p r) = 2*pi*r
perimetro (Retangulo p1 p2) = 2*(abs ((posx p2) - (posx p1))) + 2*(abs ((posy p2) - (posy p1)))
perimetro (Triangulo p1 p2 p3) = (distpontos p1 p2) + (distpontos p2 p3) + (distpontos p1 p3)

-- 8

--a
myIsLower :: Char -> Bool
myIsLower c = if ord c <= 122 && ord c >= 97 then True else False

--b
myIsDigit :: Char -> Bool
myIsDigit c = if ord c <= 57 && ord c >= 48 then True else False

--c
myIsAlpha :: Char -> Bool
myIsAlpha c
 | ord c <= 122 && ord c >= 97 = True
 | ord c <= 90 && ord c >= 65 = True
 | otherwise = False

--d
myToUpper :: Char -> Char
myToUpper c
 | myIsLower c = chr ((ord c) - 32)
 | otherwise = c

--e
myIntToDigit :: Int -> Char
myIntToDigit i
 | i <= 9 = chr (i + 48)
 | otherwise = error (show i ++ " is not a Digit")

--f
myDigitToInt :: Char -> Int
myDigitToInt c 
 | myIsDigit c = (ord c) - 48
 | otherwise = error (show c ++ " is not a Digit")