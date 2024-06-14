<script>
import Card from '../components/ui/Card.vue';
import { ResultadoJogo } from '../models/resultadoJogo';

export default {
  components: {
    Card
  },
  data() {
    return {
      yellow: 0,
      red: 0,
      draw: 0,
      games: []
    }
  },
  methods: {
    getStatistics() {
      fetch('http://localhost:3000/statistics/1')
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Something went wrong');
          }
        })
        .then(data => {
          this.red = data.red;
          this.yellow = data.yellow;
          this.draw = data.draw;
        })
        .catch(error => console.log(error));
    },
    getGames() {
      /* Codifique o pedido para obter a lista de jogos
        salvaguardados no json-server */
    },
    showDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString() 
        + " " + date.toLocaleTimeString();
    },
    showResult(vencedor) {
      if (vencedor === ResultadoJogo.AMARELO) {
        return 'Vencedor: Amarelo';
      }

      if (vencedor === ResultadoJogo.VERMELHO) {
        return 'Vencedor: Vermelho';
      }

      return 'Empate';
    }
  },
  created() {
    this.getStatistics();
    this.getGames();
  }
}
</script>

<template>
  <div class="container">
    <Card class="card yellow">
      <p>Vitórias Amarelo</p>
      <div class="score">{{ yellow }}</div>
    </Card>
    <Card class="card red">
      <p>Vitórias Vermelho</p>
      <div class="score">{{ red }}</div>
    </Card>
    <Card class="card draw">
      <p>Empates</p>
      <div class="score">{{ draw }}</div>
    </Card>
    <Card class="card history">
      <p>Histórico</p>
      <!-- Adicione o link sempre que se clica num Card de um jogo
           do histórico que redireciona para a página desse jogo -->
      <Card 
        v-for="g in games" 
        :key="g.id"
        :class="['history-card', {
          red: g.game.vencedor === 0,
          yellow: g.game.vencedor === 1,
          draw: g.game.vencedor === 2
        }]">
        <p class="date">{{ showDate(g.date) }}</p>
        <div class="score">{{ showResult(g.game.vencedor) }}</div>
      </Card>
    </Card>
  </div>
</template>

<style scoped>
.container {
  margin: 40px 20px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-areas: 
    "y r e"
    ". h .";
  gap: 20px;
}

.card {
  text-align: center;
  font-weight: 600;
}

.score {
  font-weight: 900;
  font-size: 24px;
}

.red {
  background-color: #F7E3E6;
}

.yellow {
  background-color: #FBF4E0;
}

.draw {
  background-color: #E6FBE9;
}

.history {
  grid-area: h;
  background-color: var(--color-accent);
}

.history > p {
  color: var(--color-light);
}

.history-card {
  cursor: pointer;
  margin-top: 20px;
  transition: all 0.3s ease-in;
}

.history-card:hover {
  opacity: 0.7;
}

@media screen and (max-width: 700px) {
  .container {
    grid-template-areas: 
      "y"
      "r"
      "e"
      "h";
    grid-template-columns: 1fr;
  }
}
</style>