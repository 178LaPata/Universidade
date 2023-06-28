module Ficha3 where

import Ficha1

-- 1
data Hora = H Int Int
    deriving Show
type Etapa = (Hora,Hora)
type Viagem = [Etapa]

v1 :: Viagem
v1 = [(H 9 30, H 10 25), (H 11 20, H 12 45), (H 13 30, H 14 45)]

-- a
etapaValida :: Etapa -> Bool
etapaValida (hp,hc) = valida hp && valida hc && hc `edepois` hp

-- b
viagemValida :: Viagem -> Bool
viagemValida [] = False
viagemValida (a:b:t) = etapaValida a && 
                       (fst b) `depois` (snd a) &&
                       viagemValida (b:t)
viagemValida [e] = etapaValida e

-- c
partidaChegada :: Viagem -> (Hora,Hora)
partidaChegada v = (fst (head v), snd (last v))

-- d
tempoEfetivo :: Viagem -> Hora
tempoEfetivo v = converter (tempEf v)


tempEf :: Viagem -> Int
tempEf ((p,c):t) = (minutos c - minutos p) + tempEf t
tempEf [] = 0

-- e
tempoEspera :: Viagem -> Hora1
tempoEspera v = tempoEsperaAux v 0
                where tempoEsperaAux ((h1,h2):(h3,h4):rv) ac = tempoEsperaAux ((h3,h4):rv) (ac + (difHoras2 h3 h2))
                      tempoEsperaAux (e:rv) ac = minToHora2 ac

--f
tempoTotalViagem :: Viagem -> Hora1
tempoTotalViagem v = (minToHora2.sum) (map (horaToMin2) [tempoViagem v, tempoEspera v])

--2
type Poligonal = [Ponto]

--a 
comprimento :: Poligonal -> Double
comprimento (p1:p2:lp) = distpontos p1 p2 + comprimento (p2:lp)
comprimento _ = 0

--b
fechada :: Poligonal -> Bool
fechada [] = True
fechada l 
 | head l == last l && length l > 1 = True
 | otherwise = False 

--c
triangula :: Poligonal -> [Figura]
triangula lp = parte2 (init lp)
 where parte2 (p1:p2:p3:t) = (Triangulo p1 p2 p3): parte2 (p1:p3:t)
       parte2 _ = []

--d
areaLinha :: Poligonal -> Double
areaLinha [] = 0
areaLinha l 
 | fechada l = sum (map (area) (triangula l))
 | otherwise = error "Nao e uma linha poligonal fechada e convexa"

--e
mover :: Poligonal -> Ponto -> Poligonal
mover l p 
 | fechada l == False || length l < 4 = error "Impossivel"
 | otherwise = map (moveponto (coefMov (head l) p)) l
 where coefMov :: Ponto -> Ponto -> (Double,Double)
       coefMov p1 p2 = ((posx p2)-(posx p1), (posy p2) - (posy p1))
       moveponto :: (Double,Double) -> Ponto -> Ponto
       moveponto (x,y) p = (Cartesiano ((posx p)+ x) ((posy p) + y))

-- 3
data Contacto = Casa Integer
              | Trab Integer
              | Tlm Integer
              | Email String
              deriving Show

type Nome = String
type Agenda = [(Nome, [Contacto])]

ag1 :: Agenda
ag1 = [("Joao", [Tlm 911333444, Casa 255333444]), ("Ana", [Trab 223456789])]

-- a
acrescEmail :: Nome -> String -> Agenda -> Agenda
acrescEmail n s [] = [(n,[Email s])]
acrescEmail n s ((x,l):t)
        | n == x = (x, (Email s):l) : t
        | otherwise = (x,l) : acrescEmail n s t

-- b
verEmails :: Nome -> Agenda -> Maybe [String]
verEmails n [] = Nothing
verEmails n ((x,l):t)
        | n == x = Just (emails l)
        | otherwise = verEmails n t

emails :: [Contacto] -> [String]
emails [] = []
emails ((Email s):t) = s: emails t
emails (_:t) = emails t

-- c
consTelefs :: [Contacto] -> [Integer]
consTelefs [] = []
consTelefs ((Casa n):t) = n : consTelefs t
consTelefs ((Trab n):t) = n : consTelefs t
consTelefs ((Tlm n):t) = n : consTelefs t
consTelefs (_:t) = consTelefs t

-- d
casa :: Nome -> Agenda -> Maybe Integer
casa n [] = Nothing
casa n ((x,l):t)
        | n == x = daCasa l
        | otherwise = casa n t

daCasa :: [Contacto] -> Maybe Integer
daCasa [] = Nothing 
daCasa ((Casa n):_ ) = Just n
daCasa (_:t) = daCasa t 

-- 5

data Movimento = Credito Float | Debito Float
               deriving Show
data Data = D Int Int Int
          deriving Show
data Extracto = Ext Float [(Data, String, Movimento)]
              deriving Show

ex1 :: Extracto
ex1 = Ext 100 [(D 2020 1 30, "Compra", Debito 15),
               (D 2020 2 14, "Salario", Credito 850)
               (D 2020 2 30, "Compra", Debito 15),
               (D 2020 3 14, "Salario", Credito 850)]

-- a
extValor :: Extracto -> Float -> [Movimento]
extValor (Ext s l) x = aux x l

aux :: Float -> [(Data, String, Movimento)] -> [Movimento]
aux x [] = []
aux x ((_,_,Debito n):t) 
            | n>x = Debito n : aux x t
            | n<=x = aux x t 
aux x ((_,_,Credito n):t) 
            | n>x = Credito n : aux x t
            | n<=x = aux x t 
-- b
filtro :: Extracto -> [String] -> [(Data,Movimento)]
filtro (Ext s l) nomes = aux1 l nomes

aux1 :: [(Data, String, Movimento)] -> [String] -> [(Data, Movimento)]
aux1 [] _ = []
aux1 ((a,b,c):t) nomes 
              | elem b nomes = (a,c) : aux1 t nomes
              | otherwise = aux1 t nomes

-- c
creDeb :: Extracto -> (Float,Float)
creDeb (Ext s l) = aux2 l


aux2 :: [(Data, String, Movimento)] -> (Float,Float)
aux2 [] = (0,0)
aux2 ((_,_,Debito n):t) = ( fst (aux2 t), n + snd (aux2 t))
aux2 ((_,_,Credito n):t) = (n+x, y)
    where (x,y) = aux2 t

-- d
saldo :: Extracto -> Float
saldo (Ext s l) = let (c,d) = creDeb (Ext s l)
                  in s + c - d

