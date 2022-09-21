<template>
  <div class="container" style="margin-bottom: -30px;">
    <img src="@/assets/logo.png" alt="" width="150" height="36" @click="goToHome" style="cursor: pointer;">
  </div>
  <div class="topnav" :class="collapse ? 'responsive' : ''">
    <router-link to="/" @click="collapse = !collapse">Home</router-link>
    <router-link to="/products" @click="collapse = !collapse">Produtos</router-link>
    <router-link to="/types" @click="collapse = !collapse">Tipos de Produto</router-link>
    <router-link to="/movements" @click="collapse = !collapse">Movimentações</router-link>
    <router-link to="/sales" @click="collapse = !collapse">Meus Lucros</router-link>
    <a class="icon icon-option" @click="collapse = !collapse">
      <font-awesome-icon icon="fa-solid fa-bars" />
    </a>
  </div> 
</template>

<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
  name: 'AppNavbar',
  setup() {
    const collapse = ref(false)

    const router = useRouter()

    const goToHome = () => {
      router.push('/')
    }

    return {
      collapse,
      goToHome
    }
  }
}
</script>

<style scoped>
  .topnav {
  background-color: #333;
  overflow: hidden;
  margin: 5px;
  border-radius: 5px;
  margin: auto;
  width: 80%;
  margin-top: 30px !important;
  margin-bottom: 20px !important;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.router-link-exact-active {
  background-color: #04AA6D;
  color: white;
}

</style>