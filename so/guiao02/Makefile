SRCS = $(wildcard *.c)

PROGS = $(patsubst %.c,%,$(SRCS))

CC = gcc
CFLAGS = -Wall -g

all: ex1 ex2 ex2aula

ex1: ex1.c

ex2: ex2.c

ex2aula: ex2aula.c

clean:
	rm -rf $(PROGS)
	rm -rf *.o
	