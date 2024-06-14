<script>
import { Jogo } from "../models/jogo";
import { ResultadoJogada } from "../models/resultadoJogada";
import { ResultadoJogo } from "../models/resultadoJogo";
import { useSimulationStore } from "../stores/simulationStore.js";

export default {
  data() {
    return {
      jogo: new Jogo(),
      statistics: {}
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
          this.statistics = data;
        })
        .catch(error => console.log(error));
    },
    updateStatistics() {
      const vencedor = this.jogo.vencedor;

      if (vencedor === ResultadoJogo.AMARELO) {
        this.statistics.yellow += 1;
      } else if (vencedor === ResultadoJogo.VERMELHO) {
        this.statistics.red += 1;
      } else {
        this.statistics.draw += 1;
      }

      fetch('http://localhost:3000/statistics/1', {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.statistics)
      });
    },
    saveGame() {
      fetch('http://localhost:3000/games', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          date: new Date().toISOString(),
          game: this.jogo
        })
      });
    },
    jogar(coluna) {
      const simulationStore = useSimulationStore();
      /* Atualize o estado da simulação aqui */   


      const res = this.jogo.jogar(coluna);      

      if (res === ResultadoJogada.ERRO_COLUNACHEIA) {
        alert('A coluna já está cheia!');
      } else if (res === ResultadoJogada.ERRO_JOGOTERMINADO) {
        alert('O jogo já terminou. Faça um novo jogo.')
      } else if (res === ResultadoJogada.TERMINOU) {
        this.updateStatistics();
        this.saveGame();
      }
    },
    reset() {
      const simulationStore = useSimulationStore();
      this.jogo.reset();
      /* Limpe o estado da simulação aqui */
    }
  },
  computed: {
    statusJogo() {
      const vencedor = this.jogo.vencedor;

      if (vencedor === ResultadoJogo.AMARELO) {
        return 'Vencedor: Amarelo';
      }

      if (vencedor === ResultadoJogo.VERMELHO) {
        return 'Vencedor: Vermelho';
      }

      if (vencedor === ResultadoJogo.EMPATE) {
        return 'Empate'
      }

      return this.jogo.jogadorAJogar
        ? 'Jogador: Vermelho'
        : 'Jogador: Amarelo'
    }
  },
  created() {
    this.getStatistics();
  }
}
</script>

<template>
  <div class="status">{{ statusJogo }}</div>
  <Tabuleiro :jogo="jogo" @jogar="jogar" />
  <div class="button-container">
    <Button @click="reset">Novo Jogo</Button>
  </div>
</template>

<style scoped>
.status {
  font-size: 1.2rem;
  font-weight: 600;
  text-align: center;
  margin: 40px 0;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 40px;
}
</style>