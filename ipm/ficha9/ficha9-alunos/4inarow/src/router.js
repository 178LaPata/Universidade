import { createRouter, createWebHistory } from 'vue-router';

import OJogo from './pages/OJogo.vue';
import Statistics from './pages/Statistics.vue';
import Simulation from './pages/Simulation.vue';
import NotFound from './pages/NotFound.vue';
import GameResult from './pages/GameResult.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/game'
    },
    {
      path: '/game',
      component: OJogo
    },
    {
      path: '/game/:id',
      component: GameResult,
      props: true
    },
    {
      path: '/statistics',
      component: Statistics
    },
    {
      path: '/simulation',
      component: Simulation
    },
    {
      path: '/:notFound(.*)',
      component: NotFound
    }
  ]
});

export default router;