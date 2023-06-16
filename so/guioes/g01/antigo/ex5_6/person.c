#include "person.h"

int new_person(char *name, int age){
    Person p;
    p.age = age;
    if (!strncpy(p.name, name, MAX_NAME))
        return -1;

    int fd = open (FILENAME, O_CREAT | O_APPEND | O_WRONLY, 0640);
    if (fd < 0)
    {
        write(1, strerror(errno), errno);
        perror("Erro a abrir o ficheiro");
        return -1;
    }

    write(fd, &p, sizeof(Person));

    int xd;
    xd = lseek(fd, -sizeof(Person), SEEK_CUR);

    if(xd < 0)
    {
        perror("Error lseek");
        return -1;
    }

    int h;
    h = xd/sizeof(Person);

    char registo[20];
    snprintf(registo, 20, "Registo %d", h);

    write(1, registo, strlen(registo));
    
    return h;
}

int person_change_age(char *name, int age2){
    int fd = open (FILENAME, O_RDWR, 0640);
    
    if (fd < 0)
    {
        write(1, strerror(errno), errno);
        perror("Erro a abrir o ficheiro");
        return -1;
    }
    
    Person p2;
    //int person_index = -1;
    int person_index = 0;

    while (person_index = read(fd, &p2, sizeof(Person)) > 0)
    {
        //person_index++;
        if (strcmp(p2.name, name) == 0)
        {
            p2.age = age2;
            lseek(fd, -sizeof(Person), SEEK_CUR);
            write(fd, &p2, sizeof(Person));
        }
    }
    close(fd);
    return 0;
}   

int person_change_age_v2(long pos, int age3){
    int fd = open (FILENAME, O_RDWR, 0640);
    
    if (fd < 0)
    {
        write(1, strerror(errno), errno);
        perror("Erro a abrir o ficheiro");
        return -1;
    }
    
    Person p3;
    int person_index = 0;
    
    lseek(fd, pos*sizeof(Person), SEEK_SET);
    if (person_index = read(fd, &p3, sizeof(Person)) > 0)
    {
        p3.age = age3;
        lseek(fd, -sizeof(Person), SEEK_CUR);
        write(fd, &p3, sizeof(Person));
    }
    close(fd);
    return 0;
}