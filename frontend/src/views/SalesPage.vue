<template>
	<div class="container">
    <label class="mt-sm">Busque pelo Tipo de Produto</label>
    <div class="input-container mt-sm">
      <select v-model="productTypeId" class="input-field" @change="search">
        <option value="">Selecione...</option>
        <option :value="item.id" v-for="item in types" :key="item.id">{{item.description}}</option>
      </select>
    </div>

    <div v-if="loading">
      <img src="@/assets/loading.gif" width="24" height="24" style="vertical-align: middle;"> Carregando...
    </div>

		<div style="overflow-x:auto;" v-else-if="itens.length > 0">
      <table>
        <thead>
          <tr>
            <th>Tipo de Produto</th>
            <th>Descrição</th>
            <th class="text-center">Quantidade Vendida</th>
            <th class="text-center">Preço Fornecedor</th>
            <th class="text-center">Total Venda</th>
            <th class="text-center">Lucro</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in itens" :key="item.id">
            <td>{{item.productType}}</td>
            <td>{{item.description}}</td>
            <td class="text-center">{{item.quantitySold}}</td>
            <td class="text-right">{{item.price ? formatMoney(item.price) : ''}}</td>
            <td class="text-right">{{item.totalSold ? formatMoney(item.totalSold) : ''}}</td>
            <td class="text-right color-green">{{item.totalProfit ? formatMoney(item.totalProfit) : ''}}</td>
          </tr>
        </tbody>
      </table>

      <div class="text-right sales-positive">
        Total Lucro: <span class="color-green">{{salesTotal ? formatMoney(salesTotal) : ''}}</span>
      </div>
    </div>

    <div v-else>
      Sem registros encontrados
    </div>
	</div>
</template>

<script>
import { onMounted, ref } from 'vue'
import { api } from '../services/api'
import { formatMoney } from '../utils/formatUtil'

export default {
  name: 'MovementsPage',
  setup() {
    const loading = ref(false)

    const itens = ref([])

    const types = ref([])

    const productTypeId = ref('')

    const salesTotal = ref(0)

    const search = async () => {
      loading.value = true
      const response = await api.get(`/products/report/movements?type=${productTypeId.value}`)
      itens.value = response.data

      salesTotal.value = itens.value.reduce((acumulador, item) => {
        return acumulador += item.totalProfit;
      }, 0)
      loading.value = false
    }

    const loadProductTypes = async () => {
      try {
        const response = await api.get(`/types`)
        types.value = response.data;
      } catch (error) {
        console.log(error)
      }
    }

    onMounted(async () => {
      await search()
      await loadProductTypes()
    })

    return {
      loading,
      itens,
      productTypeId,
      types,
      salesTotal,
      formatMoney,
      search,
    }
  }
}
</script>

<style scoped>
  .mt-sm {
    margin-top: 10px;
  }

  .m-b-sm {
    margin-bottom: 5px;
  }

  .m-r-sm {
    margin-right: 5px;
  }

  .color-green {
    color: #04AA6D;
    font-weight: bold;
  }

  div.sales-positive {
    margin-top: 10px;
    font-weight: bold;
    font-size: 18px;
  }
</style>