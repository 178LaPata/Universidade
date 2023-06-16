#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>

#define FILENAME "file_pessoas"
#define MAX_NAME 200

typedef struct person {
    char name[MAX_NAME];
    int age;
} Person;

//API
int new_person(char *name, int age);
int person_change_age(char *name, int age2);
int person_change_age_v2(long pos, int age3);