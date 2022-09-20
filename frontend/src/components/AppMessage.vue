<template>
  <div class="alert" :class="error ? 'alert-error' : 'alert-success'" v-if="message">
    {{message}}
  </div>
</template>

<script>
export default {
  name: 'AppMessage',
  props: {
    message: {
      type: String, required: true
    },
    error: {
      type: Boolean,
      default: false
    }
  }
}
</script>