<template>
  <app-navbar></app-navbar>
  <router-view/>
</template>

<script>
import AppNavbar from './components/AppNavbar.vue';

export default {
  name: 'App',
  components: {
    AppNavbar
  }
}

</script>