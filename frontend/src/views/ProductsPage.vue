<template>
  <div class="container">
    <h3 class="title">Produtos</h3>
 
    <div class="input-container">
      <input class="input-field" type="text" placeholder="Pesquise pela descrição" v-model="filter" @keyup.enter="search">
      <button class="btn default" @click="search">Consultar</button>
      <button class="btn info" @click="openModalForm">Novo</button>
    </div>

    <div style="overflow-x:auto;" v-if="products.length > 0">
      <table>
        <thead>
          <tr>
            <th>Tipo de Produto</th>
            <th>Descrição</th>
            <th class="text-center">Quantidade</th>
            <th>Preço</th>
            <th width="10%" class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in products" :key="item.id">
            <td>{{item.productTypeDescription}}</td>
            <td>{{item.description}}</td>
            <td class="text-center">{{item.quantity}}</td>
            <td class="text-right">{{item.price ? formatMoney(item.price) : ''}}</td>
            <td class="text-center">
              <a style="margin-right: 10px;" @click="openModalForm(item)" class="icon-option tooltip" data-text="Editar">
                <font-awesome-icon icon="fa-solid fa-pencil" />
              </a>
              
              <a @click="openModalDelete(item.id)" class="icon-option tooltip" data-text="Excluir">
                <font-awesome-icon icon="fa-solid fa-trash" />
              </a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    
    <div class="text-center" :class="products.length > 0 ? '' : 'no-result'" style="margin-top: 20px;">
      <span v-if="products.length > 0">
        {{products.length}} registros encontrados
      </span>
      <span v-else>Nenhum registro encontrado</span>
    </div>
   </div>

  <div class="modal" v-if="showModalForm">
    <div class="modal-content">
      <div class="modal-header" >
        {{product.id ? 'Alterar Produto' : 'Novo Produto'}}
        <span class="close" @click="closeModalForm">&times;</span>
      </div>

      <div class="modal-body">
        <app-message :message="messageSuccess" v-if="messageSuccess"></app-message>
        <app-message :message="messageError" :error="true" v-if="messageError"></app-message>

        <form @submit.prevent="submitForm">
          <div class="form-group">
            <label>Tipo</label>
            <div class="input-container">
              <select v-model="product.productTypeId" class="input-field">
                <option value="">Selecione...</option>
                <option :value="item.id" v-for="item in types" :key="item.id">{{item.description}}</option>
              </select>
            </div>
          </div>
          
          <div class="form-group">
            <label>Código</label>
            <div class="input-container">
              <input class="input-field" type="text" placeholder="Código" v-model="product.code" />
            </div>
          </div>
          
          <div class="form-group">
            <label>Descrição</label>
            <div class="input-container">
              <input class="input-field" type="text" placeholder="Descrição" v-model="product.description" />
            </div>
          </div>

          <div class="form-group">
            <label>Quantidade</label>
            <div class="input-container">
              <input class="input-field" type="number" placeholder="Quantidade" v-model="product.quantity" />
            </div>
          </div>

          <div class="form-group">
            <label>Preço</label>
            <div class="input-container">
              <CurrencyInput class="input-field" v-model="product.price" :options="{ currency: 'BRL' }" />
            </div>
          </div>

          <div class="modal-footer text-right">
            <button type="button" class="btn default" @click="closeModalForm">Fechar</button>
            <button class="btn info" type="submit">Salvar</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <div class="modal" v-if="showModalDelete">
    <div class="modal-content">
      <div class="modal-header" >
        Confirma operação
        <span class="close" @click="closeModalDelete">&times;</span>
      </div>
      
      <div class="modal-body">
        <app-message :message="messageSuccess" v-if="messageSuccess"></app-message>
        <app-message :message="messageError" :error="true" v-if="messageError"></app-message>
        
        Deseja excluir o item?
      </div>

      <div class="modal-footer text-right">
        <button type="button" class="btn default" @click="closeModalDelete">Fechar</button>
        <button class="btn info padding-right" @click="deleteProduct">Excluir</button>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue'
import { api } from '../services/api'
import { formatMoney } from '../utils/formatUtil'
import AppMessage from '@/components/AppMessage.vue'
import CurrencyInput from '@/components/CurrencyInput.vue'

export default {
  name: 'ProductTypesPage',
  components: {
    AppMessage,
    CurrencyInput
  },
  setup() {
    const showModalForm = ref(false)

    const showModalDelete = ref(false)

    const filter = ref('')

    const products = ref([])

    const messageSuccess = ref('');

    const messageError = ref('')

    const productId = ref('')

    const product = ref({
      id: '',
      productTypeId: '',
      code: '',
      description: '',
      quantity: null,
      price: 0
    })

   

    const types = ref([])

    const search = async () => {
      const response = await api.get(`/products?description=${filter.value}`)
      products.value = response.data
    }

    const loadProductTypes = async () => {
      try {
        const response = await api.get(`/types`)
        types.value = response.data;
      } catch (error) {
        messageError.value = error?.response?.data?.titulo
      }

    }

    const submitForm = async () => {
      try {
        clearMessages();

        if (product.value.id) {
          await api.put(`/products/${product.value.id}`, product.value)
        } else {
          await api.post(`/products`, product.value)
        }

        messageSuccess.value = 'Produto cadastrado com sucesso!'
        product.value = {
          id: '',
          productTypeId: '',
          code: '',
          description: '',
          quantity: null,
          price: 0
        }
      } catch (error) {
        messageError.value = error?.response?.data?.titulo
      }
    }

    const deleteProduct = async () => {
      if (!productId.value) {
        return;
      }

      try {
        clearMessages();
        await api.delete(`/products/${productId.value}`, product.value)
        messageSuccess.value = 'Produto excluído com sucesso!'
        productId.value = ''

        setTimeout(() => {
          closeModalDelete()
        }, 1000)

      } catch (error) {
        messageError.value = error?.response?.data?.titulo
      }
    }

     const openModalForm = (productEdit) => {
      showModalForm.value = true
       clearMessages()
       loadProductTypes()

      if (productEdit) {
        product.value = Object.assign({}, productEdit)
      }
    }

    const closeModalForm = async () => {
      showModalForm.value = false;
      if (messageSuccess.value) {
        await search()
      }
    }

    const openModalDelete = (typeId) => {
      clearMessages()
      showModalDelete.value = true
      productId.value = typeId;
    }

    const closeModalDelete = async () => {
      showModalDelete.value = false;
      if (messageSuccess.value) {
        await search()
      }
    } 

    const clearMessages = () => {
      messageSuccess.value = ''
      messageError.value = ''
    }

    onMounted(async () => {
     await search();
    })

    return {
      showModalForm,
      showModalDelete,
      filter,
      products,
      product,
      messageSuccess, 
      messageError,
      productId, 
      types,
      openModalForm,
      closeModalForm,
      search,
      formatMoney,
       submitForm,
      openModalDelete,
      closeModalDelete,
      deleteProduct
    }
  }
}
</script>

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

  .no-result {
    border: 1px solid #e7e7e7;
    width: 100%;
    padding: 5px;
    border-radius: 5px;
  }

  div.form-button {
    text-align: right; 
    margin-top: 20px;
  }

  .padding-right {
    margin-right: 10px;
  }

  div.modal-footer {
    padding-bottom: 20px;
  }
</style>