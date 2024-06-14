<script>
import { Jogo } from "../models/jogo";
import { useSimulationStore } from "@/stores/simulationStore";

export default {
  data() {
    return {
      jogo: new Jogo(),
      running: false
    }
  },
  methods: {
    jogar(coluna) {
      this.jogo.jogar(coluna);
    },
    simulate() {
      const simulationStore = useSimulationStore();
      /* Mostre uma mensagem de erro apropriada aqui, caso não haja nada a simular */    

      let i = 0;
      this.running = true;
      this.jogo.reset();

      /* Atualize estas duas variáveis de acordo com a simulação */
      /*const plays = ...;
      this.jogo.jogadorAJogar = ...*/

      const interval = setInterval(() => {
        this.jogar(plays[i]);
        i++;

        if (i === plays.length) {
          clearInterval(interval);
          this.running = false;
        }
      }, 1000);
    }
  }
}
</script>

<template>
<div class="title">Simular Último Jogo</div>
  <Tabuleiro :jogo="jogo" />
  <div class="button-container">
    <Button @click="simulate" :disabled="running">Simular</Button>
  </div>
</template>

<style scoped>
.title {
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