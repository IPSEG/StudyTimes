import { createApp } from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import router from './router'
import { VueQueryPlugin } from 'vue-query'

loadFonts()

createApp(App)
  .use(vuetify)
  .use(router)
  .use(VueQueryPlugin)
  .mount('#app')
