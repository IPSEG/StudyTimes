<template>
  <v-container>
    <v-form>
      <v-row class="mb-5">
        <v-col class="text-h3">
          공보기 회원가입
        </v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <v-text-field 
            v-model="name.data.value"
            name="encName"
            type="text"
            variant="outlined"
            label="이름"
            :error="name.data.error"
            :error-messages="name.data.errorMessages" />
        </v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <v-text-field
            v-model="id.data.value"
            name="encId"
            type="text"
            variant="outlined"
            label="아이디"
            :error="id.data.error"
            :error-messages="id.data.errorMessages" />
        </v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <v-text-field
            v-model="pass.data.value"
            name="encPass"
            type="password"
            variant="outlined"
            label="패스워드"
            :error="pass.data.error"
            :error-messages="pass.data.errorMessages" />
        </v-col>
      </v-row>
      <v-row dense>
        <v-col>
          <v-text-field
            v-model="passCheck.data.value"
            name="encPassCheck"
            type="password"
            variant="outlined"
            label="패스워드 확인"
            :error="passCheck.data.error"
            :error-messages="passCheck.data.errorMessages" />
        </v-col>
      </v-row>
      <v-row   dense>
        <v-col>
          <v-btn block type="submit" :disabled="invalid" variant="outlined" color="primary">회원가입</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<script setup>
import { computed } from '@vue/reactivity';
import { reactive, ref, watch } from 'vue'

const useTextFieldValidation = (...validations) => {
  const data = reactive({
    value: '',
    errorMessages: '',
    error: false
  })
  watch(() => data.value, (v) => {
    const invalid = validations.filter(r => !r[0](v))
    data.error = invalid.length > 0
    if (invalid.length > 0) {
      data.errorMessages = invalid[0][1]
    } else {
      data.errorMessages = ''
    }
  })

  return {
    data,
  }
}

const name = useTextFieldValidation([(v) => !!v, '필수 입력값입니다.'])
const id = useTextFieldValidation([(v) => !!v, '필수 입력값입니다.'])
const pass = useTextFieldValidation([(v) => !!v, '필수 입력값입니다.'])
const passCheck = useTextFieldValidation([(v) => !!v, '필수 입력값입니다.'])

watch([() => pass.data.value, () => passCheck.data.value], (a) => {
  const [v1, v2] = a
  if (v1 === v2) {
    passCheck.data.error = false
    passCheck.data.errorMessages = ''
  } else {
    passCheck.data.error = true
    passCheck.data.errorMessages = '패스워드와 패스워드 확인이 일치하지 않습니다.'
  }
})


const invalid = computed(() => name.data.error || id.data.error || pass.data.error || passCheck.data.error || !name.data.value || !id.data.value || !pass.data.value || !passCheck.data.value)
</script>