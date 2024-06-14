import './assets/main.css';

import { createApp } from 'vue';
import { createPinia } from 'pinia';

import router from './router';
import App from './App.vue';
import Tabuleiro from './components/Tabuleiro.vue';
import Button from './components/ui/Button.vue';

const app = createApp(App);

app.component('Button', Button);
app.component('Tabuleiro', Tabuleiro);

app.use(router);
app.use(createPinia());

app.mount('#app');