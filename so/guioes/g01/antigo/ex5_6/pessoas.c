#include "person.h"

int main (int argc, char *argv[]){

    if (!strcmp(argv[1], "-i"))
        new_person(argv[2], atoi(argv[3]));

    if (!strcmp(argv[1], "-u"))
        person_change_age(argv[2], atoi(argv[3]));

    if (!strcmp(argv[1], "-o"))
        person_change_age_v2(atoi(argv[2]), atoi(argv[3]));
    return 0;
}