import ply.lex as lex

tokens = (
    'ARROBA',
    'ID',
    'YEAR',
    'IGUAL',
    'COMMA',
    'LBRACE',
    'RBRACE',
    'LPAREN',
    'RPAREN',
    'ASPAS',
    'IFANE',
    'FSLASH',
    'DOT',
    'TOWPOINT',
)

t_ARROBA = r'\@'
t_ID = r'[a-zA-Z]+'
t_IGUAL = r'='
t_COMMA = r','
t_LBRACE = r'\{'
t_RBRACE = r'\}'
t_LPAREN = r'\('
t_RPAREN = r'\)'
t_ASPAS = r'\"'
t_IFANE = r'\-'
t_FSLASH = r'\/'
t_DOT = r'\.'
t_TOWPOINT = r'\:'


year_dic = {}

def t_YEAR(t):
    r'year=\d+'
    year = t.value.split('=')[1]
    year_dic[year] = year_dic.get(year, 0) + 1
    return t

t_ignore = ' \t'

def t_newline(t):
    r'\n+'
    t.lexer.lineno += len(t.value)

def t_error(t):
    print(f"Invalid character '{t.value[0]}' at position {t.lexpos}")
    t.lexer.skip(1)
    return

lexer = lex.lex()

v = """
@techreport{Camila,
  author ={{projecto Camila}},
  editor ={L.S. Barbosa and J.J. Almeida and J.N. Oliveira and Luís Neves},
  title = "\textsc{Camila} - A Platform for Software Mathematical Development",
  url="http://camila.di.uminho.pt",
  type="(Páginas do projecto)",
  institution = umdi,
  year=1998,
  year=1998,
  year=1998,
  keyword = "FS",
}
"""

lexer.input(v)
for tok in lexer:
    pass

print(year_dic)