<template>
  <div class="container">
    <h3 class="title">Tipos de Produto</h3>
 
    <div class="input-container">
      <input class="input-field" type="text" placeholder="Pesquise pela descrição" name="usrnm">
      <button class="btn default">Consultar</button>
      <button class="btn info">Novo</button>
    </div>

    <div style="overflow-x:auto;">
      <table>
        <thead>
          <tr>
            <th>Descrição</th>
            <th width="10%" class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Tipo 01</td>
            <td></td>
          </tr>
          <tr>
            <td>Tipo 02</td>
            <td></td>
          </tr>
          <tr>
            <td>Tipo 03</td>
            <td>

              <div class="tooltip">Hover over me
  <span class="tooltiptext">Tooltip text</span>
</div> 
            </td>
          </tr> 
        </tbody>
      </table>
    </div>

    <div class="text-center" style="margin-top: 20px;">3 registros encontrados</div>

  </div>
</template>

<style scoped>

.title {
  display: inline;
}

div.input-container {
  margin-top: 15px;
}

button.default {
  margin-right: 10px; 
  margin-left: 10px;
}


</style>