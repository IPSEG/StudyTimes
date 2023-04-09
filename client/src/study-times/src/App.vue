<template>
  <v-app id="app" class="layout">
    <div>
        <tool-bar></tool-bar>
        <router-view></router-view>
      </div>
  </v-app>
</template>

<script>
import ToolBar  from './components/ToolBar.vue'

export default {
  name: 'App',
  components: {
    ToolBar,
  },
}
</script>

<style scoped>
.layout {
  text-align: center;
  background-color: white;
}
</style>
