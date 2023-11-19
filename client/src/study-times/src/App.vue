<template>
  <v-app>
    <v-main>
      <router-view></router-view>
    </v-main>
    <!-- https://vuetifyjs.com/en/components/bottom-navigation/#color -->
    <v-bottom-navigation grow>
      <v-btn :value="route.path" :to="route.path" v-for="route in routes.filter(row => !row.hide)" :key="route.name">
        <v-icon>{{ route.icon }}</v-icon>
        <span>{{ route.displayName }}</span>
      </v-btn>
    </v-bottom-navigation>
  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import { routes } from './router'

</script>
