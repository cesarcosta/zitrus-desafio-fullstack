<template>
	<div class="container">

    <div class="form-group">
      <label class="mt-sm">Busque pelo Tipo de Produto</label>
      <div class="input-container mt-sm">
        <select v-model="productTypeId" class="input-field" @change="search">
          <option value="">Selecione...</option>
          <option :value="item.id" v-for="item in types" :key="item.id">{{item.description}}</option>
        </select>
      </div>
    </div>

		<div style="overflow-x:auto;" v-if="products.length > 0">
      <table>
        <thead>
          <tr>
            <th>Tipo de Produto</th>
            <th>Descrição</th>
            <th class="text-center">Quantidade Disponível</th>
            <th class="text-center">Quantidade Vendida</th>
            <th class="text-center">Preço</th>
            <th width="10%" class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in products" :key="item.id">
            <td>{{item.productType}}</td>
            <td>{{item.description}}</td>
            <td class="text-center">{{item.quantityAvailable}}</td>
            <td class="text-center">{{item.quantitySold}}</td>
            <td class="text-right">{{item.price ? formatMoney(item.price) : ''}}</td>
            <td class="text-center">
              <a style="margin-right: 10px;" @click="openModalMovementForm(item)" class="icon-option tooltip" data-text="Movimentar">
                <font-awesome-icon icon="fa-solid fa-money-bill-trend-up" />
              </a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
	</div>

  <div class="modal" v-if="showMovementForm">
    <div class="modal-content">
      <div class="modal-header" >
        Movimentar Estoque do Produto
        <span class="close" @click="closeModalMovementForm">&times;</span>
      </div>

      <div class="modal-body">
        <app-message :message="messageSuccess" v-if="messageSuccess"></app-message>
        <app-message :message="messageError" :error="true" v-if="messageError"></app-message>

        <form @submit.prevent="submitForm">
          
          <div class="form-group m-b-sm">
            <label>Produto:</label>
            {{productInfo.description}}
          </div>

          <div class="form-group m-b-sm">
            <label>Quantidade em Estoque:</label>
            {{productInfo.quantityAvailable}}
          </div>
          
          <div class="form-group m-b-sm">
            <label>Preço:</label>
            {{productInfo.price}}
          </div>

          <hr class="m-b-sm" />

          <div class="form-group mt-sm">
            <label style="display: block;">Tipo de Movimentação</label>
            <input type="radio" name="type" v-model="movement.type" value="ENTRADA">ENTRADA
            <input type="radio" name="type" v-model="movement.type" value="SAIDA">SAIDA
            
            <div class="form-group mt-sm">
              <label>Data da Movimentação</label>
              <div class="input-container">
                <input class="input-field" type="text" placeholder="Data" v-model="movement.date" v-mask-date.br />
              </div>
            </div>

            <div class="form-group">
              <label>Quantidade</label>
              <div class="input-container">
                <input class="input-field" type="number" placeholder="Quantidade" v-model="movement.quantity" />
              </div>
            </div>

            <div class="form-group">
              <label>Preço</label>
              <div class="input-container">
                <CurrencyInput class="input-field" v-model="movement.total" :options="{ currency: 'BRL' }" />
              </div>
            </div>
          </div>
          
          <div class="modal-footer text-right">
            <button type="button" class="btn default m-r-sm" @click="closeModalMovementForm">Fechar</button>
            <button class="btn info" type="submit">Salvar</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue'
import { api } from '../services/api'
import { formatMoney } from '../utils/formatUtil'
import CurrencyInput from '@/components/CurrencyInput.vue'
import AppMessage from '@/components/AppMessage.vue'

export default {
  name: 'MovementsPage',
  components: {
    CurrencyInput,
    AppMessage
  },
  setup() {
    const products = ref([])

    const types = ref([])

    const movement = ref({
      productId: '',
      date: '',
      type: 'ENTRADA',
      quantity: null,
      total: null
    })

    const productInfo = ref({
      productId: '',
      description: '',
      quantity: null,
      price: null,
      productType: ''
    })

    const productTypeId = ref('')

    const showMovementForm = ref(false)

    const messageSuccess = ref('');

    const messageError = ref('')

    const search = async () => {
      const response = await api.get(`/products/report/stock?type=${productTypeId.value}`)
      products.value = response.data
    }

    const submitForm = async () => {
      try {
        clearMessages();
        
        await api.post(`/stock/movement`, movement.value)

        messageSuccess.value = 'Movimentação criada com sucesso!'
        movement.value = {
          productId: '',
          date: '',
          type: 'ENTRADA',
          quantity: null,
          total: null
        }
      } catch (error) {
        messageError.value = error?.response?.data?.titulo
      }
    }

    const loadProductTypes = async () => {
      try {
        const response = await api.get(`/types`)
        types.value = response.data;
      } catch (error) {
        console.log(error)
      }
    }

    const clearMessages = () => {
      messageSuccess.value = ''
      messageError.value = ''
    }

    const openModalMovementForm = (product) => {
      showMovementForm.value = true

      productInfo.value = Object.assign({}, product);

      movement.value.productId = product.productId
    }

    const closeModalMovementForm = async () => {
      showMovementForm.value = false;
      if (messageSuccess.value) {
        await search()
      }
    }

    onMounted(async () => {
      await search()
      await loadProductTypes()
      clearMessages();
    })

    return {
      products,
      productTypeId,
      types,
      showMovementForm,
      messageSuccess,
      messageError,
      movement,
      productInfo,
      formatMoney,
      search,
      submitForm,
      openModalMovementForm,
      closeModalMovementForm
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
</style>