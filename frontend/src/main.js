import { createApp } from 'vue'
import VueMask from '@devindex/vue-mask';
import App from './App.vue'
import router from './router'

import './assets/style.css';

import { library } from '@fortawesome/fontawesome-svg-core'


import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

import { faTrash } from '@fortawesome/free-solid-svg-icons'
import { faPencil } from '@fortawesome/free-solid-svg-icons'
import { faMoneyBillTrendUp } from '@fortawesome/free-solid-svg-icons'


library.add(faTrash, faPencil, faMoneyBillTrendUp)

createApp(App).use(router).use(VueMask).component('font-awesome-icon', FontAwesomeIcon).mount('#app')
