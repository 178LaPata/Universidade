<!-- Este documento apresenta uma solução em construção. É fornecido como 
     material de estudo. Recomenda-se que o revejam e melhorem conforme   
     forem adquirindo novos conhecimentos. -->

<script>
import Navbar from './components/Navbar.vue';

export default {
  components: {
    Navbar
  },
  data() {
    return {
      jogadorAJogar: false,
      vencedor: null
    }
  },
  methods: { 
    alterarJogador(jogador) {
      this.jogadorAJogar = jogador;
    },
    alterarVencedor(vencedor) {
      this.vencedor = vencedor;
    },
    reset(jogadorAJogar) {
      this.jogadorAJogar = jogadorAJogar;
      this.vencedor = null;
    }
  }
}
</script>

<template>
  <Navbar />
  <router-view></router-view>
</template>
