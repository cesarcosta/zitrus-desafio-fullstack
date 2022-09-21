<template>
  <div class="container">
    <h3 class="title">Tipos de Produto</h3>
 
    <div class="input-container">
      <input class="input-field" type="text" placeholder="Pesquise pela descrição" v-model="filter" @keyup.enter="search">
      <button class="btn default" @click="search">Consultar</button>
      <button class="btn info" @click="openModal">Novo</button>
    </div>

    <div v-if="loading">
      <img src="@/assets/loading.gif" width="24" height="24" style="vertical-align: middle;"> Carregando...
    </div>

    <div style="overflow-x:auto;" v-else-if="types.length > 0">
      <table>
        <thead>
          <tr>
            <th>Descrição</th>
            <th width="10%" class="text-center">Opções</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in types" :key="item.id">
            <td>{{item.description}}</td>
            <td class="text-center">
              <a style="margin-right: 10px;" @click="openModal(item)" class="icon-option tooltip" data-text="Editar">
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
    
    <div class="text-center" :class="types.length > 0 ? '' : 'no-result'" style="margin-top: 20px;">
      <span v-if="types.length > 0">
        {{types.length}} registros encontrados
      </span>
      <span v-else>Nenhum registro encontrado</span>
    </div>
   </div>

  <div class="modal" v-if="showModal">
    <div class="modal-content">
      <div class="modal-header" >
        {{productType.id ? 'Alterar Tipo de Produto' : 'Novo Tipo de Produto'}}
        <span class="close" @click="closeModal">&times;</span>
      </div>

      <div class="modal-body">
        <app-message :message="messageSuccess" v-if="messageSuccess"></app-message>
        <app-message :message="messageError" :error="true" v-if="messageError"></app-message>

        <form @submit.prevent="submitForm">
          <div class="form-group">
            <label>Descrição</label>
            <div class="input-container">
              <input class="input-field" type="text" placeholder="Descrição" v-model="productType.description" />
            </div>
          </div>

          <div class="modal-footer text-right">
            <button type="button" class="btn default" @click="closeModal">Fechar</button>
            <button class="btn info" type="submit" :disabled="!productType.description">Salvar</button>
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
        <button class="btn info padding-right" @click="deleteProductType">Excluir</button>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue'
import { api } from '../services/api'
import AppMessage from '@/components/AppMessage.vue'

export default {
  name: 'ProductTypesPage',
  components: {
    AppMessage
  },
  setup() {
    const loading = ref(false)

    const showModal = ref(false)

    const showModalDelete = ref(false)

    const filter = ref('')

    const types = ref([])

    const messageSuccess = ref('');

    const messageError = ref('')

    const productTypeId = ref('')

    const productType = ref({
      id: '',
      description: ''
    })

    const search = async () => {
      try {
        loading.value = true
        const response = await api.get(`/types?description=${filter.value}`)
        types.value = response.data
        loading.value = false
      } catch (error) {
        loading.value = false
      }
    }

    const submitForm = async () => {
     try {
        clearMessages();

        if (productType.value.id) {
          await api.put(`/types/${productType.value.id}`, productType.value)
        } else {
          await api.post(`/types`, productType.value)
        }

        messageSuccess.value = 'Tipo de Produto cadastrado com sucesso!'
        productType.value = {
          id: '',
          description: ''
        }
      } catch (error) {
        messageError.value = error?.response?.data?.titulo
      }
    }

    const deleteProductType = async () => {
      if (!productTypeId.value) {
        return;
      }

      try {
        clearMessages();
        await api.delete(`/types/${productTypeId.value}`, productType.value)
        messageSuccess.value = 'Tipo de Produto excluído com sucesso!'
        productTypeId.value = ''

        setTimeout(() => {
          closeModalDelete()
        }, 1000)

      } catch (error) {
        messageError.value = error?.response?.data?.titulo
      }
    }

    const openModal = (productTypeEdit) => {
      showModal.value = true
      clearMessages();

      if (productTypeEdit) {
        productType.value = Object.assign({}, productTypeEdit)
      }
    }

    const closeModal = async () => {
      showModal.value = false;
      if (messageSuccess.value) {
        await search()
      }
    }

    const openModalDelete = (typeId) => {
      clearMessages()
      showModalDelete.value = true
      productTypeId.value = typeId;
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
      showModal,
      showModalDelete,
      filter,
      types,
      productType,
      messageSuccess, 
      messageError,
      productTypeId, 
      loading, 
      openModal,
      closeModal, 
      search,
      submitForm,
      openModalDelete,
      closeModalDelete,
      deleteProductType
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