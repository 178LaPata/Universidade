<script>
import { Jogo } from "../models/jogo";

export default {
  data() {
    return {
      // TODO: adicionar propriedades
      jogo: new Jogo()
      
    }
  },
  methods: {
    jogar(coluna) {
      //TODO: definir o método jogar para reagir ao evento de click numa coluna
      const linha = this.jogo.jogar(coluna);
    },
    reset() {
      //TODO: definir o método reset para reagir ao evento de click no botão de reset
      this.jogo.reset();
    },
    aplicarVermelho(coluna, linha) {
      //TODO: definir o método aplicarVermelho que diz se a célula na coluna e linha indicadas deve ser vermelha
      return this.jogo.getColuna(coluna, linha) ===  true;
    },
    aplicarAmarelo(coluna, linha) {
      //TODO: definir o método aplicarAmarelo que diz se a célula na coluna e linha indicadas deve ser amarela
      return this.jogo.getColuna(coluna, linha) ===  false;
    }
  }
}
</script>


<template>
  <div>
    <div class="tabuleiro">
      <div v-for="c in jogo.constructor.NUMERO_COLUNAS" :key="`col-${c}`" class="coluna">
        <div v-for="l in jogo.constructor.NUMERO_LINHAS" :key="`col-${c}-linha-${l}`" class="celula" @click="jogar(c - 1)">
          <div class="celula_interior" :class="{ red: aplicarVermelho(c - 1, jogo.constructor.NUMERO_LINHAS - l), yellow: aplicarAmarelo(c - 1, jogo.constructor.NUMERO_LINHAS - l) }">
          </div>
        </div>
      </div>
    </div>
    <div style="text-align: center;">
      <button @click="reset">Reset</button>
    </div>
  </div>
</template>

<style scoped>
.tabuleiro {
  margin-top: 5% !important;
  display: flex;
  width: 45%;
  margin: auto;
}

.coluna {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.celula {
  background-color: blue;
  aspect-ratio: 1;
}

.celula_interior {
  border-radius: 100%;
  background-color: white;
  height: 100%;
}

.yellow {
  background-color: yellow;
}

.red {
  background-color: red;
}

button {
  margin-top: 10px;
  border: 1px solid black;
  border-radius: 5px;
  text-align: center;
  padding: 10px;
  font-weight: 600;
}
</style>