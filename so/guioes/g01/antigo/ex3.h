#include <sys/types.h>
#include <unistd.h> /* chamadas ao sistema: defs e decls essenciais */
#include <fcntl.h> /* O_RDONLY, O_WRONLY, O_CREAT, O_* */

ssize_t myreadln(int fd, char *line, ssize_t size);

int myreadln1(int fd, char *line, size_t size);

