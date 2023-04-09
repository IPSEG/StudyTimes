<template>
  <v-container>
    <v-row dense>
      <v-col>
        <v-chip size="x-large">{{ chip }}</v-chip> {{ data?.email }}
      </v-col>
    </v-row>
    <v-row dense>
      <v-divider />
    </v-row>
    <v-row dense>
      <v-col>
        <v-list density="compact" subtitle="hi" :items="settings" />
      </v-col>
    </v-row>
    <v-row dense>
    <v-col>
        <v-list density="compact">
          <v-list-subheader>외부 로그인 연결 상태</v-list-subheader>
          <v-list-item 
            v-for="(item, i) in oauth"
            :key="i"
            :value="item.value"
            :title="item.title"
            @click="item.click"
          >
            <template v-slot:prepend>
              <v-icon :icon="item.icon"></v-icon>
            </template>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
    <v-row dense>
      <v-divider />
    </v-row>
    <v-row dense>
      <v-col>
        총 측정 횟수 {{ data.timers.use }}회
      </v-col>
    </v-row>
    <v-row justify="end">
      <v-btn variant="outlined" @click="refetch">탈퇴</v-btn>
    </v-row>
  </v-container>
</template>

<script setup>
import { computed } from 'vue'
import { fetchUserInformation } from '@/apis/user' 
import { useQuery } from 'vue-query'
import useOauth from './compositions/use-oauth'

const { data, refetch } = useQuery('user', fetchUserInformation)
const chip = computed(() => {
  const email = data?.value?.email
  if (!email) {
    return ''
  }
  return email[0]
})

const { menu: oauth } = useOauth(computed(() => data.value?.oauth))

const settings = [
  { title: '닉네임 변경', value: 'change-name' },
  { title: '이메일 주소 변경', value: 'change-email' }
]

</script>