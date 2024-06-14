<script>
import { Jogo } from "../models/jogo";

export default {
  data() {
    return {
      jogo: new Jogo(),
      running: false,
      simulation: {}
    }
  },
  methods: {
    jogar(coluna) {
      this.jogo.jogar(coluna);
    },
    simulate() {
      /* Efetue aqui o código para executar a simulação.
         A simulação só deve ser efetuada se a variável
         simulation não for um objeto vazio e se houver
         jogadas a mostrar */
    },
    getSimulation() {
      /* Insira aqui o código para obter a 
         simulação do json-server */
    }
  },
  created() {
    this.getSimulation();
  }
}
</script>

<template>
<div class="title">Simular Último Jogo</div>
  <Tabuleiro :jogo="jogo" />
  <div class="button-container">
    <!-- Complete com as diretivas necessárias -->
    <Button>Simular</Button>
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