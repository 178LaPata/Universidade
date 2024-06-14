<script>
import { Jogo } from "../models/jogo.js";

export default {
  props: ['id'],
  data() {
    return {
      game: new Jogo()
    };
  },
  methods: {
    getGame() {
      /* Codifique o método para obter o jogo salvaguardado a 
        partir do id recebido nas props */
    }
  },
  created() {
    this.getGame();
  }
}
</script>

<template>
  <Tabuleiro :jogo="game" />
</template>