<template>
   <div class="topnav">
    <router-link to="/">Home</router-link>
    <router-link to="/products">Produtos</router-link>
    <router-link to="/types">Tipos de Produto</router-link>
  </div> 
</template>

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