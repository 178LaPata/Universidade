<script>
import Tabuleiro from '../components/Tabuleiro.vue';
import Card from '../components/ui/Card.vue';
import { ResultadoJogo } from '../models/resultadoJogo';
import { Jogo } from '../models/jogo';

export default {
  components: {
    Card,
    Tabuleiro
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
      fetch('http://localhost:3000/games')
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('Something went wrong');
          }
        })
        .then(data => {
          this.games = [];
          data.forEach(element => {
            const jogo = new Jogo();

            jogo.tabuleiro = element.game.tabuleiro;
            jogo.jogadorAJogar = element.game.jogadorAJogar;
            jogo.terminado = element.game.terminado;
            jogo.vencedor = element.game.vencedor;

            this.games.push({
              id: element.id,
              date: element.date,
              game: jogo
            })
          });
        })
        .catch(error => console.log(error));
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
      <Card 
        v-for="g in games" 
        :key="g.id"
        @click="$router.push('/game/' + g.id)"
        :class="['history-card', {
          red: g.game.vencedor === 0,
          yellow: g.game.vencedor === 1,
          draw: g.game.vencedor === 2
        }]">
        <div class="info-container">
          <p class="date">{{ showDate(g.date) }}</p>
          <div class="score">{{ showResult(g.game.vencedor) }}</div>
        </div>
        <Tabuleiro :jogo="g.game" class="tabuleiro" />
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
    "h h h";
  gap: 20px;
}

.card {
  color: var(--color-light);
  background-color: var(--color-primary-dark);
  text-align: center;
  font-weight: 600;
}

.score {
  font-weight: 900;
  font-size: 20px;
}

.history {
  grid-area: h;
  background-color: var(--color-primary);
}

.history-card {
  display: flex;
  cursor: pointer;
  margin-top: 20px;
  transition: all 0.3s ease-in;
  background-color: var(--color-primary-dark);
}

.history-card:hover {
  opacity: 0.6;
}

.info-container {
  flex-grow: 1;
  text-align: left;
}

.tabuleiro {
  margin: 0;
  max-width: 150px;
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

@media screen and (max-width: 500px) {
  .history-card {
    display: block;
  }

  .info-container {
    text-align: center;
  }

  .tabuleiro {
    margin: 20px auto auto;
  }
}
</style>