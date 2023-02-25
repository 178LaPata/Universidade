SRCS = $(wildcard *.c)

PROGS = $(patsubst %.c,%,$(SRCS))

CC = gcc
CFLAGS = -Wall -g

all: ex1 ex2 ex4

ex1: ex1.c

ex2: ex2.c

ex4: ex4.o ex3.o

ex4.o: ex4.c ex3.h

ex3.o: ex3.c ex3.h

clean:
	rm -rf $(PROGS)
	rm -rf *.o
	