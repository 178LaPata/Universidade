<script>
import { Jogo } from "../models/jogo.js";
import { ResultadoJogo } from "@/models/resultadoJogo.js";

export default {
  props: ['id'],
  data() {
    return {
      game: new Jogo(),
      date: new Date()
    };
  },
  methods: {
    getGame() {
      fetch('http://localhost:3000/games/' + this.id)
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            this.$router.push("/notFound");
          }
        })
        .then(data => {
          this.date = new Date(data.date);
          this.game.tabuleiro = data.game.tabuleiro;
          this.game.jogadorAJogar = data.game.jogadorAJogar;
          this.game.vencedor = data.game.vencedor;
          this.game.terminado = data.game.terminado;
        })
        .catch(error => console.log(error));
    }
  },
  computed: {
    vencedor() {
      if (this.game.vencedor === ResultadoJogo.AMARELO) {
        return 'Vencedor: Amarelo';
      }

      if (this.game.vencedor === ResultadoJogo.VERMELHO) {
        return 'Vencedor: Vermelho';
      }

      return 'Empate';
    },
    gameDate() {
      return 'Data: ' + this.date.toLocaleDateString() + " " + this.date.toLocaleTimeString();
    }
  },
  created() {
    this.getGame();
  }
}
</script>

<template>
  <div class="date">{{ gameDate }}</div>
  <div class="status">{{ vencedor }}</div>
  <Tabuleiro :jogo="game" />
</template>

<style scoped>
.status {
  font-size: 1.2rem;
  font-weight: 600;
  text-align: center;
  margin: 0 0 40px 0;
}

.date {
  margin: 40px 0 10px 0;
  text-align: center;
}
</style>