<script>
import { Jogo } from '../models/jogo';

export default {
  props: ['jogo'],
  emits: ['jogar'],
  data() {
    return {
      numColunas: Jogo.NUMERO_COLUNAS,
      numLinhas: Jogo.NUMERO_LINHAS
    }
  },
  methods: {
    aplicarVermelho(coluna, linha) {
      return this.jogo.getCelula(coluna, linha) === true;
    },
    aplicarAmarelo(coluna, linha) {
      return this.jogo.getCelula(coluna, linha) === false;
    }
  }
}
</script>

<template>
  <div class="tabuleiro">
    <div v-for="c in numColunas" class="coluna" :key="c"
      @click="$emit('jogar', c - 1)">
      <div v-for="l in numLinhas" :key="l"
        :class="['celula_interior', {
          yellow: aplicarAmarelo(c - 1, l - 1),
          red: aplicarVermelho(c - 1, l - 1)
        }]">
      </div>
    </div>
  </div>
</template>

<style scoped>
.tabuleiro {
  display: flex;
  width: 40%;
  margin: 20px auto auto;
  background-color: #4357A0;
  padding: 10px;
  border-radius: 10px;
}

.coluna {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.celula_interior {
  border-radius: 100%;
  background-color: #001773;
  max-height: 100%;
  max-width: 100%;
  aspect-ratio: 1;
  margin: 4px;
}

.yellow {
  background-color: var(--color-yellow);
}

.red {
  background-color: var(--color-red);
}
</style>
