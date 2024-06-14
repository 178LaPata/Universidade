import ply.lex as lex

tokens = (
    'VARIABLE',
    'EQUALS',
    'NUMBER',
    'PLUS',
    'MINUS',
    'TIMES',
)

t_VARIABLE = r'[a-zA-Z_]+'
t_EQUALS = r'='
t_NUMBER = r'\d+'
t_PLUS = r'\+'
t_MINUS = r'-'
t_TIMES = r'\*'

t_ignore = ' \t'

def t_newline(t):
    r'\n+'
    t.lexer.lineno += len(t.value)
    print("\n")

def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)

lexer = lex.lex()

var = """
x=a+1
b=2
a = b-1
a*=2
"""

lexer.input(var)
for tok in lexer:
    print(tok)

#while True:
#    tok = lexer.token()
#    if not tok:
#        break
#    if tok.type == 'VARIABLE':
#        print(f"variavel {tok.value}\n")
#    elif tok.type in ['PLUS', 'MINUS', 'TIMES', 'EQUALS']:
#        print("operador encontrado")
