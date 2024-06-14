import ply.lex as lex

states = (
    ('open', 'exclusive'),
    ('closed', 'exclusive'),
)


tokens = (
    'ID',
    'NUMBER',
    'COMMA',
    'LBRACE',
    'RBRACE',
    'LPAREN',
    'RPAREN',
    'ASPAS',
    'TOWPOINT',
    'LETRAACENTO',
    'LRETO',
    'RRETO',
    'DOT',
)

t_ID = r'[a-zA-Z]+'
t_NUMBER = r'\d+'
t_COMMA = r','
t_LBRACE = r'\{'
t_RBRACE = r'\}'
t_LPAREN = r'\('
t_RPAREN = r'\)'
t_ASPAS = r'\"'
t_TOWPOINT = r'\:'
t_LETRAACENTO = r'\[àáâãäèéêëìíîïòóôõöùúûüçÀÁÂÃÄÈÉÊËÌÍÎÏÒÓÔÕÖÙÚÛÜÇ]'
t_LRETO = r'\['
t_RRETO = r'\]'
t_DOT = r'\.'

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
{
  "name": "John Doe",
  "age": 21,
  "gender": "male",
  "height": 1.68,
  "address": {
    "street": "123 Main Street",
    "city": "New York",
    "country": "USA",
    "zip": "10001"
  },
  "married": false,
  "hobbies": [
    {
      "name": "reading",
      "books": [
        {
          "title": "Heartstopper: Volume 1",
          "author": "Alice Oseman",
          "genres": ["Graphic Novels", "Romance", "Queer"]
        },
        {
          "title": "1984",
          "author": "George Orwell",
          "genres": ["Science Fiction", "Dystopia", "Politics"]
        }
      ]
    },
    {
      "name": "gaming",
      "games": [
        {
          "title": "Portal 2",
          "platform": ["PC", "PlayStation 3", "Xbox 360"]
        },
        {
          "title": "Synth Riders",
          "platform": ["PSVR", "PSVR2", "PCVR", "Oculus Quest"]
        }
      ]
    }
  ]
}
"""

lexer.input(v)
for tok in lexer:
    print(tok)
